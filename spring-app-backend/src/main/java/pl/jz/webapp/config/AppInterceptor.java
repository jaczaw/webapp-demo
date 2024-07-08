package pl.jz.webapp.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.Iterator;

@Slf4j
@Component
public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        //Invoked before sending the request to the controller
        Enumeration<String> headerNamesReq = request.getHeaderNames();
        while (headerNamesReq.hasMoreElements()) {
            String headerName = headerNamesReq.nextElement();
            String headerValue = request.getHeader(headerName);

            log.info("request.getHeaderNames(): headerName: {}, headerValue: {}",headerName,headerValue);
        }
        Iterator<String> headerNamesRes = response.getHeaderNames().iterator();
        while (headerNamesRes.hasNext()) {
            String headerName = headerNamesRes.next();
            String headerValue = response.getHeader(headerName);

            log.info("response.getHeaderNames(): headerName: {}, headerValue: {}",headerName,headerValue);
        }

        response.getHeaderNames().forEach(p->log.info("NameHeader: {}",response.getHeader(p)));

        log.info("preHandle method called");
        log.info("request.getServletPath(): {}",request.getServletPath());
        log.info("request.getHttpServletMapping().getServletName(): {}",request.getHttpServletMapping().getServletName());
        log.info("request.getSession().getId(): {}",request.getSession().getId());
        log.info("request.getPathInfo(): {}",request.getPathInfo());
        log.info("request.getHeaderNames(): {}",request.getHeaderNames());
        log.info("response.getHeaderNames(): {}",response.getHeaderNames());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView){
        //Invoked after processing the request and before the view is rendered.
        log.info("postHandle method called");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        //Invoked after view is rendered
        //Performs cleanup operation
        log.info("afterCompletion method called");
    }

}