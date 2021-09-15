package lab.web.servlets;

import lab.model.Course;
import lab.model.Grade;
import lab.model.Student;
import lab.service.CourseService;
import lab.service.GradeService;
import lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "student-enrolment-servlet", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    private final StudentService studentService;

    private final CourseService courseService;

    private final GradeService gradeService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService, GradeService gradeService){
        this.springTemplateEngine=springTemplateEngine;
        this.studentService=studentService;
        this.courseService=courseService;
        this.gradeService=gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
        context.setVariable("students", this.studentService.listAll());
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("studentsInCourse.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = (String) req.getParameter("student");
        req.getSession().setAttribute("student", s);
        String courseId= (String) req.getSession().getAttribute("courseId");
        Long l=Long.parseLong(courseId);
        String grade=req.getParameter("grade");
        Student student = studentService.findByUsername(s);
        courseService.addStudentInCourse(s, l);
        Course course = courseService.findById(l).get();
        gradeService.save(grade.charAt(0), student, course);
        List<Grade> gradeList = gradeService.findAllByCourse(course);
        req.getSession().setAttribute("grades", gradeList);
        req.getSession().setAttribute("course", course);
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
