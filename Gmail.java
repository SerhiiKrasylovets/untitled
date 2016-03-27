

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gmail {
    private static WebDriver driver;
    private static String baseUrl;

    public static void main(String[] args) throws Exception {

/* Provide vaild gmail username & password */
        String username = "sergey.krasilovets@gmail.com";
        String password = "19KrasylovetsS88";
        ComposeMail(username,password);
    }

    public static void ComposeMail(String username,String password) throws Exception {

        Date dNow = new Date( );
        SimpleDateFormat subjectdate = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        String emailsubject="Current time "+subjectdate.format(dNow).toString();
        String tomailid ="nithin.sasalu@gmail.com";
        String mailbody =password+"\n"+"Greate you sent email :-)"+"\n" + "Regards,"+"\n"+"Nithin";
        driver = new FirefoxDriver();
        baseUrl = "https://gmail.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

/* Navigate to Gmail */
        driver.get(baseUrl+"/intl/en/mail/help/about.html");
        driver.manage().window().maximize();

/* Enter username and password */
        driver.findElement(By.xpath("//a[@id='gmail-sign-in']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).clear();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='Passwd']")).clear();
        driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='signIn']")).click();

/*Verify login */
        String title = "Gmail";
        if (driver.getTitle().contains(title)){
            System.out.println("Logged in sucessfully !!!"+driver.getTitle());
        }
        else {
            System.out.println("Unable to loggin :-( "+driver.getTitle());
        }
/* Compose email */
        driver.findElement(By.xpath("//div[@class='z0']/div")).click();

/* Enter email details */
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td//img[2]")).click();
        driver.findElement(By.className("vO")).sendKeys(tomailid);
        driver.findElement(By.className("aoT")).sendKeys(emailsubject);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@tabindex='1']")));
        WebElement printbody = driver.switchTo().activeElement();
        printbody.sendKeys(mailbody);
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//div[text()='Send']")).click();

/* Go to Sent Items and verify */
        driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();
        if (driver.findElement(By.xpath("//div[@class='y6']//b[text()='"+emailsubject+"']")) != null)
        {
            System.out.println("Wowww.. Email sent sucessfully!!!");
        }
        else
        {
            System.out.println("Failed to send email !!!");
        }
        Thread.sleep(2000);
        driver.quit();
    }
}