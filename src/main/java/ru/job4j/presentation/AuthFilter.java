package ru.job4j.presentation;

import ru.job4j.logic.utils.Constants;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("/auth")
                || uri.endsWith(".css")
                || uri.endsWith("login.html")
                || uri.endsWith("registration.html")
        ) {
            chain.doFilter(req, resp);
            return;
        }
        if (req.getSession().getAttribute(Constants.USER) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
