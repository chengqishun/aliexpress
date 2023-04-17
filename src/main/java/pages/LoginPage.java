package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    ChromeDriver driver;


    public LoginPage(ChromeDriver driver){
      this.driver = driver;
    }

    public void goPage(){
        driver.get("https://login.aliexpress.com/seller.htm?_locale=zh_CN");
    }

    public void login(String username,String password){
        WebElement loginiframe = driver.findElement(By.id("alibaba-login-box"));
        driver.switchTo().frame(loginiframe);
        WebElement loginName = driver.findElement(By.id("fm-login-id"));
        WebElement loginPass = driver.findElement(By.id("fm-login-password"));
        WebElement loginBtn = driver.findElement(By.id("fm-login-submit"));
        loginName.sendKeys(username);
        loginPass.sendKeys(password);
        loginBtn.click();
    }

}
