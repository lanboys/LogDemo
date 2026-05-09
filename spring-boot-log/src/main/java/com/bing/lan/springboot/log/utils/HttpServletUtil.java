package com.bing.lan.springboot.log.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Enumeration;
import java.util.UUID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;


public class HttpServletUtil {

    public static final String REQUEST_ID = "RID";

    public static String getRequestId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String rid = request.getHeader(REQUEST_ID);
            if (StringUtils.isEmpty(rid)) {
                Object attribute = attributes.getAttribute(REQUEST_ID, SCOPE_REQUEST);
                if (StringUtils.isEmpty(attribute)) {
                    rid = UUID.randomUUID().toString().replace("-", "");
                    attributes.setAttribute(REQUEST_ID, rid, SCOPE_REQUEST);
                } else {
                    rid = (String) attribute;
                }
            }
            return rid;
        }
        return "can't get request";
    }

    public static MultiValueMap<String, String> getRequestHeaders() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MultiValueMap<String, String> headers = new HttpHeaders();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    Enumeration<String> values = request.getHeaders(name);
                    while (values.hasMoreElements()) {
                        String value = values.nextElement();
                        headers.add(name, value);
                    }
                }
            }
            return headers;
        }
        headers.add("exception", "can't get request");
        return headers;
    }
}
