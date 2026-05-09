package com.bing.lan.springboot.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 自定义异步Appender，用于提取日志字段以便后续存储到数据库
 * <p>
 * 当前实现：将日志的各个字段提取并打印出来
 * 后续可以修改为将日志插入数据库的不同字段
 */
public class DatabaseAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    protected void append(ILoggingEvent eventObject) {
        // 提取日志的各个字段
        String timestamp = formatTimestamp(eventObject.getTimeStamp());
        String threadName = eventObject.getThreadName();
        String level = eventObject.getLevel().toString();
        String loggerName = eventObject.getLoggerName();
        String message = eventObject.getFormattedMessage();
        
        // 提取MDC中的traceId（如果有的话）
        String traceId = eventObject.getMDCPropertyMap().get("traceId");
        
        // 提取方法名和行号（从调用栈中获取）
        String methodName = "N/A";
        String lineNumber = "N/A";
        StackTraceElement[] callerData = eventObject.getCallerData();
        if (callerData != null && callerData.length > 0) {
            methodName = callerData[0].getMethodName();
            lineNumber = String.valueOf(callerData[0].getLineNumber());
        }

        // 提取完整的异常堆栈信息
        String throwableInfo = null;
        if (eventObject.getThrowableProxy() != null) {
            throwableInfo = buildThrowableInfo(eventObject.getThrowableProxy());
        }

        // 打印提取的字段（模拟后续存入数据库的不同字段）
        System.out.println("========== 日志字段提取[" + Thread.currentThread().getName() + "] ==========");
        System.out.println("【时间戳】: " + timestamp);
        System.out.println("【线程名】: " + threadName);
        System.out.println("【日志级别】: " + level);
        System.out.println("【Logger名称】: " + loggerName);
        System.out.println("【方法名】: " + methodName);
        System.out.println("【行号】: " + lineNumber);
        System.out.println("【TraceId】: " + (traceId != null ? traceId : "N/A"));
        System.out.println("【日志消息】: " + message);
        if (throwableInfo != null) {
            System.out.println("【异常信息】: \n" + throwableInfo);
        }
        System.out.println("======================================================================");

        // TODO: 后续在这里添加数据库插入逻辑
        // 例如：insertIntoDatabase(timestamp, threadName, level, loggerName, methodName, lineNumber, traceId, message, throwableInfo);
    }

    /**
     * 格式化时间戳
     *
     * @param timeStamp 时间戳
     * @return 格式化后的时间字符串
     */
    private String formatTimestamp(long timeStamp) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime.format(DATE_FORMATTER);
    }
    
    /**
     * 构建完整的异常堆栈信息
     *
     * @param throwableProxy 异常代理对象
     * @return 格式化的异常堆栈字符串
     */
    private String buildThrowableInfo(IThrowableProxy throwableProxy) {
        StringBuilder sb = new StringBuilder();
        buildThrowableInfoRecursive(sb, throwableProxy, 0);
        return sb.toString();
    }
    
    /**
     * 递归构建异常堆栈信息（处理嵌套异常）
     *
     * @param sb StringBuilder
     * @param throwableProxy 异常代理对象
     * @param depth 嵌套深度
     */
    private void buildThrowableInfoRecursive(StringBuilder sb, IThrowableProxy throwableProxy, int depth) {
        if (throwableProxy == null) {
            return;
        }
        
        // 添加缩进表示嵌套层级
        String indent = "  ".repeat(depth);
        
        // 异常类名和消息
        sb.append(indent).append(throwableProxy.getClassName());
        if (throwableProxy.getMessage() != null) {
            sb.append(": ").append(throwableProxy.getMessage());
        }
        sb.append("\n");
        
        // 堆栈跟踪
        StackTraceElementProxy[] stackTrace = throwableProxy.getStackTraceElementProxyArray();
        if (stackTrace != null) {
            for (StackTraceElementProxy step : stackTrace) {
                sb.append(indent).append("    at ").append(step.toString()).append("\n");
            }
        }
        
        // 处理引起的异常（Caused by）
        IThrowableProxy cause = throwableProxy.getCause();
        if (cause != null) {
            sb.append(indent).append("Caused by: \n");
            buildThrowableInfoRecursive(sb, cause, depth + 1);
        }
        
        // 处理被抑制的异常（Suppressed）
        IThrowableProxy[] suppressed = throwableProxy.getSuppressed();
        if (suppressed != null) {
            for (IThrowableProxy sup : suppressed) {
                sb.append(indent).append("Suppressed: \n");
                buildThrowableInfoRecursive(sb, sup, depth + 1);
            }
        }
    }
}
