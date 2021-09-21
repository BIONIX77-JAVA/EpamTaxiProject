package alex.falendish.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class I18nFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Locale locale = (Locale) req.getSession().getAttribute("language");

        if(locale == null) {
            setEnLocale(req);
        } else {
            if(req.getParameter("language") != null) {
                if(locale.getLanguage().equals("en")) {
                    Locale ruLocale = new Locale.Builder().setLanguage("ru").build();
                    req.getSession().setAttribute("language", ruLocale);
                } else if(locale.getLanguage().equals("ru")) {
                    setEnLocale(req);
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void setEnLocale(HttpServletRequest req) {
        Locale enLocale = new Locale.Builder().setLanguage("en").build();
        req.getSession().setAttribute("language", enLocale);
    }
}
