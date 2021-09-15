package lab.selenium;

import lab.model.Course;
import lab.model.Teacher;
import lab.model.Type;
import lab.service.CourseService;
import lab.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    private HtmlUnitDriver driver;

    private static boolean dataInitialized = false;

    private static Teacher t1;

    private static Teacher t2;

    private void initData() {
        if (!dataInitialized) {
            this.t1 = teacherService.save(5L, "teacher1", "teacher");
            this.t2 = teacherService.save(6L, "teacher2", "teacher");

            Optional<Course> c1=courseService.save("Softversko Testiranje", "SKIT", 12345L, Type.WINTER);
            Optional<Course> c2=courseService.save("Softversko", "skit", 56789L, Type.SUMMER);

            dataInitialized = true;
        }
    }

    @BeforeEach
    public void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }


    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursesPage coursesPage = CoursesPage.to(this.driver);
        coursesPage.assertElements(2, 0, 0, 0);
        LoginPage loginPage = LoginPage.Openlogin(this.driver);

        coursesPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        coursesPage.assertElements(2, 2, 2, 1);

        coursesPage = AddPage.addProduct(this.driver, coursesPage.getAddCourseButton().get(0), "course", "course in semester", t1.getName(), Type.WINTER);
        coursesPage.assertElements(3,3,3,1);

    }

}
