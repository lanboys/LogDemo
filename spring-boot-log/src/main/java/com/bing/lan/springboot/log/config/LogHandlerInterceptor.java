package com.bing.lan.springboot.log.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bing.lan.springboot.log.utils.HttpServletUtil;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Slf4j
public class LogHandlerInterceptor implements HandlerInterceptor {

    private static final String REQUEST_START_TIME_KEY = "request_start_time_key";
    public static final String REQUEST_REQUEST_URI_KEY = "request_request_uri_key";
    public static final String REQUEST_REQUEST_URL_KEY = "request_request_url_key";

    // 不打印的请求头
    private final List<String> headerWhiteList = List.of("Server_Access_Token");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = "[" + request.getMethod() + "]" + request.getRequestURI();
        String requestURL = "[" + request.getMethod() + "]" + request.getRequestURL();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            attributes.setAttribute(REQUEST_START_TIME_KEY, System.currentTimeMillis(), SCOPE_REQUEST);
            attributes.setAttribute(REQUEST_REQUEST_URI_KEY, requestURI, SCOPE_REQUEST);
            attributes.setAttribute(REQUEST_REQUEST_URL_KEY, requestURL, SCOPE_REQUEST);
        }

        try {
            Enumeration<String> enumeration = request.getParameterNames();
            if (request.getParameterMap().isEmpty()) {
                log.info("url:{}, no params", requestURL);
            } else {
                while (enumeration.hasMoreElements()) {
                    String key = enumeration.nextElement();
                    log.info("url:{}, param-| key: [{}] , value: [{}]", requestURL, key, request.getParameter(key));
                }
            }
        } catch (Exception e) {
            log.error("打印参数异常：url:{}, {}", requestURI, e.getMessage(), e);
        }

        MultiValueMap<String, String> requestHeaders = HttpServletUtil.getRequestHeaders();
        for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
            String key = entry.getKey();
            // 白名单
            if (!headerWhiteList.contains(key)) {
                continue;
            }
            List<String> values = entry.getValue();
            for (String value : values) {
                log.info("url:{}, header-| key: [{}] , value: [{}]", requestURL, key, value);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

        if (!(handler instanceof HandlerMethod)) {
            log.info("handler: {} ", handler);
            return;
        }
        HandlerMethod method = (HandlerMethod) handler;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Long start = null;
        String requestURL = null;
        if (attributes != null) {
            start = (Long) attributes.getAttribute(REQUEST_START_TIME_KEY, RequestAttributes.SCOPE_REQUEST);
            requestURL = (String) attributes.getAttribute(REQUEST_REQUEST_URL_KEY, RequestAttributes.SCOPE_REQUEST);
        }

        log.info("url:{}, 耗时: {} ms, method: {}.{} ",
                 requestURL,
                 null == start ? "unknown" : System.currentTimeMillis() - start,
                 method.getMethod().getDeclaringClass().getName(),
                 method.getMethod().getName());

        // log.debug("headers: 【{}】", HttpServletUtil.getRequestHeaders());
    }
}
