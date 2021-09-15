package lab.web.filter;//package mk.ukim.finki.wp.lab.web.filter;

/*
@WebFilter
public class FilterCourse implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();
        String courseId = (String)request.getSession().getAttribute("courseId");
        if (courseId==null && !"/listCourses".equals(path) && !"/courses".equals(path)) {
            response.sendRedirect("/listCourses");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
*/