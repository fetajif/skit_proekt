package lab.selenium;

import lab.model.Type;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddPage extends AbstractPage {

    private WebElement name;

    private WebElement description;

    private WebElement teacherId;

    private WebElement type;

    private WebElement submit;


    public AddPage(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage addProduct(WebDriver driver, WebElement editButton, String name, String desc, String teacherId, Type type) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddPage addPage = PageFactory.initElements(driver, AddPage.class);
        addPage.name.sendKeys(name);
        addPage.description.sendKeys(desc);
        addPage.teacherId.click();
        addPage.teacherId.findElement(By.xpath("//option[. = '" + teacherId + "']")).click();
        addPage.type.click();
        addPage.type.findElement(By.xpath("//option[. = '" + type + "']")).click();
        addPage.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }
}
