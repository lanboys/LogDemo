package com.bing.lan.springboot.log.config;

import com.bing.lan.springboot.log.utils.JacksonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Slf4j
@ControllerAdvice
public class LogResponseBodyAdvice implements ResponseBodyAdvice<Object>, Ordered {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object responseBody, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            // String requestURI = (String) attributes.getAttribute(REQUEST_REQUEST_URI_KEY, SCOPE_REQUEST);
            String requestURL = (String) attributes.getAttribute(LogHandlerInterceptor.REQUEST_REQUEST_URL_KEY, SCOPE_REQUEST);
            log.info("url:{}, responseBody:【{}】", requestURL, checkLength(responseBody));
        }
        return responseBody;
    }

    @Override
    public int getOrder() {
        return 10;
    }

    private String checkLength(Object responseBody) {
        if (ObjectUtils.isEmpty(responseBody)) {
            return "";
        }

        try {
            String str = responseBody instanceof String ? (String) responseBody :
                    JacksonUtil.toJson(responseBody);
            if (ObjectUtils.isEmpty(str)) {
                return "";
            }
            int maxLength = 800;
            if (str.length() <= maxLength) {
                return str;
            }
            //  截取
            String start = str.substring(0, maxLength / 5);
            String end = str.substring(str.length() - maxLength / 5);

            return start + " ===>> 此处省略n个字符 <<=== " + end;
        } catch (Throwable e) {
            log.error("检查responseBody异常: {}", e.getMessage(), e);
        }
        return responseBody.toString();
    }
}
