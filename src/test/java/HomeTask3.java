import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomeTask3 {
    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new EventHandler());

        eventDriver.register(new EventHandler());
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String url = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=" +
                "AdminLogin&token=d251f363cceb5a849cb7330938c64dea";
        eventDriver.get(url);
        By loginInput = By.id("email");
        By passwordInput = By.id("passwd");
        By submitLoginButton = By.name("submitLogin");
        eventDriver.findElement(loginInput).sendKeys("webinar.test@gmail.com");
        eventDriver.findElement(passwordInput).sendKeys("Xcg7299bnSmMuRLp9ITw");
        eventDriver.findElement(submitLoginButton).submit();
        Actions actions = new Actions(eventDriver);
        actions.moveToElement(eventDriver.findElement(By.id("subtab-AdminCatalog"))).perform();
        eventDriver.findElement(By.xpath("//ul[@class='submenu']//li[@data-submenu='11']")).click();
        eventDriver.findElement(By.xpath("//a[@id='page-header-desc-category-new_category']/i")).click();
        final String s = getRandomString(10);
        eventDriver.findElement(By.id("name_1")).sendKeys(s);
        eventDriver.findElement(By.id("category_form_submit_btn")).click();

        eventDriver.findElement(By.xpath("//span[@class='title_box']//i")).click();
        List<WebElement> webElements = eventDriver.findElements(By.xpath("//td[@class='pointer']"));
        final List<String> text = new LinkedList<>();
        webElements.forEach(webElement -> text.add(webElement.getText()));
        Assert.assertTrue(text.contains(s));

        eventDriver.quit();

    }

    public static String getRandomString(int length) {
        String randomChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder rand = new StringBuilder();
        Random rnd = new Random();
        while (rand.length() < length) {
            int index = (int) (rnd.nextFloat() * randomChars.length());
            rand.append(randomChars.charAt(index));
        }
        String randomStr = rand.toString();
        return randomStr;
    }
}
