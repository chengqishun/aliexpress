package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CategoryPage {
    ChromeDriver driver;

    public CategoryPage(ChromeDriver driver){
        this.driver = driver;
    }

    public void goPage(){
        driver.get("https://gsp.aliexpress.com/apps/product/publish?spm=5261.product_publish.aenewheader.1.6d054edfpkwoJD&page=category");
    }

    public void setCategory(String category){
        WebElement categoryPath = driver.findElement(By.cssSelector("#struct-categoryPath input[role=combobox]"));
        categoryPath.sendKeys(category);
    }

    public void submit (){
        WebElement csclp = driver.findElement(By.cssSelector(".cate-search-categories li span"));
        csclp.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
