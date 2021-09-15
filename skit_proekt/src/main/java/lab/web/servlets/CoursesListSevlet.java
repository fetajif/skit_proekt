package lab.web.servlets;

import lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="course-list-servlet", urlPatterns = "/listCourses")
public class CoursesListSevlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    private final CourseService courseService;

    public CoursesListSevlet(SpringTemplateEngine springTemplateEngine, CourseService courseService){
        this.springTemplateEngine=springTemplateEngine;
        this.courseService=courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", this.courseService.listAll());
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("listCourses.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = (String) req.getParameter("courseId");
        req.getSession().setAttribute("courseId", s);
        resp.sendRedirect("/addStudent");
    }
}
