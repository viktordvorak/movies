package cz.dvorakv.components;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RequestResponseLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // Log request details
        logRequest(httpServletRequest);

        // Continue with the filter chain
        chain.doFilter(request, response);

        // Log response details
        logResponse(httpServletResponse);
    }

    private void logRequest(HttpServletRequest request) {
        logger.info("Incoming Request: Method: {}\n URI: {}\n Headers: {}\n",
                request.getMethod() + "\n",
                request.getRequestURI()+ "\n",
                Collections.list(request.getHeaderNames()).stream()
                        .collect(Collectors.toMap(h -> h, request::getHeader)));
    }

    private void logResponse(HttpServletResponse response) {
        logger.info("Outgoing Response: Status: {} \n Headers: {} \n",
                response.getStatus() + "\n",
                response.getHeaderNames().stream()
                        .collect(Collectors.toMap(h -> h, response::getHeader)));
    }

    @Override
    public void destroy() {

    }
}
