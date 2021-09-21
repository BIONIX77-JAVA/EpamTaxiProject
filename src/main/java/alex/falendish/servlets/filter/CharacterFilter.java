package alex.falendish.servlets.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Set all requests's character encoding to UTF-8/
 * Filters /*
 */
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}