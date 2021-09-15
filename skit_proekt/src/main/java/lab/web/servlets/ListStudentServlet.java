package lab.web.servlets;

import lab.service.CourseService;
import lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "list-student-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    private final StudentService studentService;

    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService){
        this.springTemplateEngine=springTemplateEngine;
        this.studentService=studentService;
        this.courseService=courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
        context.setVariable("students", this.studentService.listAll());
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("listStudents.html", context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("username")!=null) {
            String username = (String) req.getParameter("username");
            String password = (String) req.getParameter("password");
            String name = (String) req.getParameter("name");
            String surname = (String) req.getParameter("surname");
            studentService.save(username, password, name, surname);
            //Long l= Long.parseLong((String)req.getSession().getAttribute("courseId"));
            //courseService.addStudentInCourse(username,l);
        }
        else {
            String s = (String) req.getParameter("courseId");
            req.getSession().setAttribute("courseId", s);
        }
        resp.sendRedirect("/addStudent");
    }
}
