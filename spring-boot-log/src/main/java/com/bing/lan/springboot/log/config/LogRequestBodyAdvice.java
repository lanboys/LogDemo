package com.bing.lan.springboot.log.config;

import com.bing.lan.springboot.log.utils.JacksonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import static com.bing.lan.springboot.log.config.LogHandlerInterceptor.REQUEST_REQUEST_URL_KEY;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;


@Slf4j
@ControllerAdvice
public class LogRequestBodyAdvice extends RequestBodyAdviceAdapter implements Ordered {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object requestBody, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            // String requestURI = (String) attributes.getAttribute(REQUEST_REQUEST_URI_KEY, SCOPE_REQUEST);
            String requestURL = (String) attributes.getAttribute(REQUEST_REQUEST_URL_KEY, SCOPE_REQUEST);
            log.info("url:{}, requestBody:【{}】", requestURL, requestBody instanceof String ? requestBody :
                    JacksonUtil.toJson(requestBody));
        }
        return requestBody;
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
