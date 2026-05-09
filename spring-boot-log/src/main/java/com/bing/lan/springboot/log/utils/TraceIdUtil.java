package com.bing.lan.springboot.log.utils;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import java.util.UUID;


public class TraceIdUtil {

    private static final Logger log = LoggerFactory.getLogger(TraceIdUtil.class);
    private static final String TRACE_ID = "traceId";

    public TraceIdUtil() {
    }

    public static void config(HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            config();
        } else {
            config(httpServletRequest.getHeader("traceId"));
        }
    }

    public static void config(String traceId) {
        if (StringUtils.isEmpty(traceId)) {
            traceId = shortLogUUID();
        }

        MDC.put("traceId", traceId);
        log.trace("new mdc traceId: {}", traceId);
    }

    public static void config() {
        String traceId = shortLogUUID();
        MDC.put("traceId", traceId);
        log.trace("new mdc traceId: {}", traceId);
    }

    public static void clear() {
        MDC.remove("traceId");
        log.trace("remove traceId");
    }


    public static String shortLogUUID() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }

        return String.format("%010d", hashCodeV);
    }

}
