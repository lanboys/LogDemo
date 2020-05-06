# LogDemo
- [logback节点配置详解](https://www.cnblogs.com/DeepLearing/p/5663178.html)
- [logback的使用和logback.xml详解](https://www.cnblogs.com/warking/p/5710303.html)
- [logback 配置详解](https://www.jianshu.com/p/1ded57f6c4e3)
- [java 日志框架总结](https://www.cnblogs.com/baizhanshi/p/7911123.html)
- [log4j-over-slf4j与slf4j-log4j12共存stack overflow异常分析](https://www.cnblogs.com/pekkle/p/6813458.html)
- [Java之常用日志框架](https://www.jianshu.com/p/190c56429ec4)
- [JAVA日志框架概述](https://www.cnblogs.com/manayi/p/9570411.html)
- [slf4j与log4j、log4j2](https://blog.csdn.net/HarderXin/article/details/80422903)
- [Spring Boot（十）Logback和Log4j2集成与日志发展史](https://www.cnblogs.com/vipstone/p/9878862.html)

##### LoggingApplicationListener.java
```
/**
 * An {@link ApplicationListener} that configures the {@link LoggingSystem}. If the
 * environment contains a {@code logging.config} property it will be used to bootstrap the
 * logging system, otherwise a default configuration is used. Regardless, logging levels
 * will be customized if the environment contains {@code logging.level.*} entries and
 * logging groups can be defined with {@code logging.group}.
 **/
```

##### AbstractLoggingSystem.java
```
	@Override
	public void initialize(LoggingInitializationContext initializationContext, String configLocation, LogFile logFile) {
        // 如果配置了自定义 logging.config, 则使用自定义的配置文件，否则走默认流程
		if (StringUtils.hasLength(configLocation)) {
			initializeWithSpecificConfig(initializationContext, configLocation, logFile);
			return;
		}
		initializeWithConventions(initializationContext, logFile);
	}

	private void initializeWithSpecificConfig(LoggingInitializationContext initializationContext, String configLocation,
			LogFile logFile) {
		configLocation = SystemPropertyUtils.resolvePlaceholders(configLocation);
		loadConfiguration(initializationContext, configLocation, logFile);
	}

	private void initializeWithConventions(LoggingInitializationContext initializationContext, LogFile logFile) {
        // 获取日志本身支持的配置文件名
		String config = getSelfInitializationConfig();
        
		if (config != null && logFile == null) {
			// self initialization has occurred, reinitialize in case of property changes
            // 再次初始化
			reinitialize(initializationContext);
			return;
		}
        // 获取 *-spring.xml配置文件
		if (config == null) {
			config = getSpringInitializationConfig();
		}
		if (config != null) {
			loadConfiguration(initializationContext, config, logFile);
			return;
		}
		loadDefaults(initializationContext, logFile);

        // 优先级: 自定义 logging.config  > 框架本身支持的文件  > *-spring.xml  > springboot.jar 中提供的默认配置文件
        // 配置文件中的 属性 主要就是针对默认配置

        jar:file:/repository/org/springframework/boot/spring-boot/2.2.6.RELEASE/spring-boot-2.2.6.RELEASE.jar!
                 /org/springframework/boot/logging/log4j2/log4j2-file.xml
	}
```