package alex.falendish.servlets.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingResponseFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)

            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
}