package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Chrome extends ChromeDriver{
   public static String ChromedriverPath = "C:\\Users\\vktea\\Desktop\\chromedriver\\chromedriver.exe";

    public Chrome() {
        super(options());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("source", "Object.defineProperty(navigator, 'webdriver', { get: () => undefined })");
        super.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
        super.manage().window().maximize();
        super.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        super.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static ChromeOptions options(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");
        return options;
    }

    public void importJquery() throws InterruptedException {
        Thread.sleep(1000);
        super.executeScript("(function(d,s){d.body.appendChild(s=d.createElement('script')).src='https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js'})(document);");
        Thread.sleep(1000);
    }

    public void scrollIntoView(String cssSelector) throws InterruptedException {
        super.executeScript("arguments[0].scrollIntoView(false);", super.findElement(By.cssSelector(cssSelector)));
        Thread.sleep(2000);
    }

    public void scrollIntoView(WebElement webElement) throws InterruptedException {
        super.executeScript("arguments[0].scrollIntoView(false);", webElement);
        Thread.sleep(2000);
    }

    public void scrollIntoViewTrue(String cssSelector) throws InterruptedException {
        super.executeScript("arguments[0].scrollIntoView(true);", super.findElement(By.cssSelector(cssSelector)));
        Thread.sleep(2000);
    }
    public void scrollIntoViewTrue(WebElement webElement) throws InterruptedException {
        super.executeScript("arguments[0].scrollIntoView(true);",webElement);
        Thread.sleep(2000);
    }

    public WebElement findCssSelector(String cssSelector){
        return new WebDriverWait(this,10).until(ExpectedConditions.elementToBeClickable(By.cssSelector
                (cssSelector)));
    }

}
