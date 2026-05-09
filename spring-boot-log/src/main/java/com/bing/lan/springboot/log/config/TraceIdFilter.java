package com.bing.lan.springboot.log.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.bing.lan.springboot.log.utils.TraceIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


public class TraceIdFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceIdFilter.class);

    public TraceIdFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        TraceIdUtil.config((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
