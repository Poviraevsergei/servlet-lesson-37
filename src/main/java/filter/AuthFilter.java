package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import repository.UserRepository;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/todo",
        "/about-me", //TODO: WHY FILTER IS NOT WORKING !?
        "/tasks"
})
public class AuthFilter implements Filter {
    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String username = (String) session.getAttribute("username");
        if (username != null && userRepository.isUsernameContains(username)) {
            chain.doFilter(request, response);
        }
            request.getRequestDispatcher("/login.html").forward(request, response);
    }
}