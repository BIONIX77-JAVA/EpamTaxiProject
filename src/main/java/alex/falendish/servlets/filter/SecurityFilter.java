//package alex.falendish.servlets.filter;
//import alex.falendish.utils.Role;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Checks urls and determines whether or not user is allowed to see the page.
// * @author Artem Malchenko
// */
//public class SecurityFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        Role role = (Role) httpServletRequest.getSession().getAttribute("role");
//        String requestUrl = httpServletRequest.getRequestURL().toString();
//
//        if(requestUrl.contains("admin-dashboard")) {
//            if(role != null && role.name().equals("Admin")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                HttpServletResponse response = (HttpServletResponse) servletResponse;
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }
//        } else if(requestUrl.contains("dispatcher-dashboard")) {
//            if(role.name().equals("Dispatcher")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                HttpServletResponse response = (HttpServletResponse) servletResponse;
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }
//        } else if(requestUrl.contains("driver-dashboard")) {
//            if(role.name().equals("Driver")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                HttpServletResponse response = (HttpServletResponse) servletResponse;
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
