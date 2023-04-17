package pages;

import common.Chrome;
import filter.FilterBysuffix;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ProductPage {
    Chrome driver;

    public ProductPage(Chrome driver){
        this.driver = driver;
    }

    public void init(){
        driver.findCssSelector(".next-row.sell-float-bottom");
        try {
            driver.importJquery();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.executeScript("$('.next-row.sell-float-bottom').hide()");
        driver.executeScript("$('.next-menu').hide()");
        driver.executeScript("$('.header-menu').hide()");

        driver.executeScript("$('#struct-sku .ver-scroll-wrap').attr('style',\"\");");
    }

    public void goEnglish() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement pngElement = driver.findElement(By.cssSelector(".header-menu-item.profile-section")); //获取元素
        action.moveToElement(pngElement).perform();
        driver.importJquery();
        Thread.sleep(700);
        driver.executeScript("$(\".ae-layout-next-overlay-wrapper .select-content span:contains('Language')\").click()");
        Thread.sleep(700);
        driver.executeScript("$('.language-radio-group label:contains(\"English\")').click()");
        Thread.sleep(700);
        driver.executeScript("$('.ae-layout-next-dialog-footer button:contains(\"确定\")').click()");
        Thread.sleep(700);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }catch (Exception e){

        }

    }

    //商品标题
    public void title(String title){
        WebElement eTitle = driver.findCssSelector("#title");
        eTitle.sendKeys(title);
    }

    public void productImg(String imgFolderPath) throws InterruptedException {
        imgFolderPath = imgFolderPath + "\\" + "B";
        //获取图片路径
        File file = new File(imgFolderPath);
        if (file.exists()){
            System.out.println("路径存在");
        }
        FilterBysuffix filter = new FilterBysuffix();
        File imgFileList[] = file.listFiles(filter);
        int imgsize = imgFileList.length;
        System.out.println(imgsize);
        //商品图片上传
        List<WebElement> webElements = driver.findElements(By.cssSelector("#struct-mainImage .sell-o-addon-content .info-content .sell-o-images .image-upload-wrap .image-uploader input"));
        for(int i=0;i<6 && i<imgFileList.length;i++){
            //图片地址
            System.out.println(imgFileList[i].getAbsolutePath());
            webElements.get(i).sendKeys(imgFileList[i].getAbsolutePath());
            Thread.sleep(1000);
        }

    }

    public void productAtt1() throws InterruptedException {
        List<WebElement> productAtts = driver.findElements(By.cssSelector("#struct-catProperty .ae-catProp-addon"));

        String js4 = "arguments[0].scrollIntoView();"; //滑动
        driver.executeScript(js4, driver.findElement(By.id("struct-imageVideo")));

        Thread.sleep(1000);
        for (int i=0;i<productAtts.size();i++){
            //let attTitle=$('#struct-catProperty .ae-catProp-addon .label').eq(i).text();
            String attTitle = productAtts.get(i).findElement(By.cssSelector("#struct-catProperty .ae-catProp-addon .label")).getText();
            System.out.println(attTitle);
            driver.executeScript("$('#struct-catProperty .ae-catProp-addon .content >').eq("+ i +").click()");
            if (attTitle.equals("适用节日")){
                Thread.sleep(500);
                driver.executeScript(" $('.options-item[title*=愚人节]').click()");
                driver.executeScript(" $('.options-item[title*=大型演出]').click()");
            }
            if (attTitle.equals("件数")){
                Thread.sleep(500);
                productAtts.get(i).findElement(By.cssSelector("input")).sendKeys("10pcs");
            }
            if (attTitle.equals("材质")){
                Thread.sleep(500);
                productAtts.get(i).findElement(By.cssSelector("input")).sendKeys("aaa");
            }
            if (attTitle.equals("型号")){
                Thread.sleep(500);
                productAtts.get(i).findElement(By.cssSelector("input")).sendKeys("bbb");
            }
            if (attTitle.equals("添加自定义属性")){
                Thread.sleep(500);
                productAtts.get(i).findElement(By.cssSelector("button")).click();
                productAtts.get(i).findElement(By.cssSelector("button")).click();
                productAtts.get(i).findElement(By.cssSelector("input[placeholder^=属性名]")).sendKeys("ccc");
                productAtts.get(i).findElement(By.cssSelector("input[placeholder^=属性值]")).sendKeys("ddd");
                productAtts.get(i).findElement(By.cssSelector("input[placeholder^=属性名]")).click();

            }
        }

        driver.executeScript(" $('.options-item[title*=NONE]').click()");
        driver.executeScript(" $('.options-item[title*=CN]').click()");
        driver.executeScript(" $('.options-item[title*=是]').click()");
        driver.executeScript(" $('.options-item[title*=乳胶]').click()");
    }

    public void productAtt() throws InterruptedException {

        String data = "Brand Name: PABCK###Origin: CN(Origin)###Thickness: 8 wire###Applicable Space: living room###Type: Storage Bags###Feature: Eco-Friendly###Material: Plastic###Plastic Type: PP###Form: Bag Compression Type###Pattern: Flat Type###Capacity: 100ml###Shape: SQUARE###Use: electronic products###Specification: 18*26cm###Product: storage bag###Model Number: 18*26cm";
        String[] arr = data.split("###");
        HashMap map = new HashMap();
        for (String a : arr) {
            System.out.println(a);
            String[] b = a.split(":");
            map.put(b[0].trim(),b[1].trim());
        }

        List<WebElement> productAtts = driver.findElements(By.cssSelector("#struct-catProperty .ae-catProp-addon"));
        for (int i=0;i<productAtts.size();i++){
            String attTitle = productAtts.get(i).findElement(By.cssSelector("#struct-catProperty .ae-catProp-addon .label")).getText();
            System.out.println(attTitle);
            String value = (String) map.get(attTitle);
            System.out.println(value);
        }
    }

    public void productSimpleAtt() throws InterruptedException {
        driver.scrollIntoView("#struct-catProperty");
        List<WebElement> productAtts = driver.findElements(By.cssSelector("#struct-catProperty .ae-catProp-addon"));
        for (int i=0;i<productAtts.size();i++){
            String attTitle = productAtts.get(i).findElement(By.cssSelector("#struct-catProperty .ae-catProp-addon .label")).getText();
            System.out.println(attTitle);
          /*  driver.executeScript("$('#struct-catProperty .ae-catProp-addon .content >').eq("+ i +").click()");*/
            if (attTitle.equals("品牌")){
                WebElement webElement = productAtts.get(i).findElement(By.cssSelector(".content input"));
                webElement.click();
              /*  driver.findElement(By.cssSelector(".options-item[title*=NONE]")).click();*/
                new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.cssSelector
                        (".options-item[title*=NONE]"))).click();
                /*driver.executeScript("$('.options-item[title*=NONE]').click()");*/
            }
            if (attTitle.equals("产地")){
                WebElement webElement = productAtts.get(i).findElement(By.cssSelector(".content input"));
                webElement.click();
                new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.cssSelector
                        (".options-item[title*=CN]"))).click();
               /* driver.findElement(By.cssSelector(".options-item[title*=CN(Origin)]")).click();*/
                /*driver.executeScript("$('.options-item[title*=CN]').click()");*/
            }
            Thread.sleep(500);
        }
    }

    public void setMeasurement(String measurement) throws InterruptedException {
        //最小计量单位 包
        driver.scrollIntoViewTrue("#struct-productUnit");
        //$('#struct-productUnit .sell-o-addon-content .next-input').click()
        driver.findCssSelector("#struct-productUnit .sell-o-addon-content .next-input").click();

        //$('.opened .next-input input')
        WebElement MeasurementUnitInput = driver.findCssSelector(".opened .next-input input");
        MeasurementUnitInput.sendKeys(measurement);
        driver.findCssSelector(".opened .options-item[title*="+ measurement +"]").click();
    }

    public void setSaleModel(String saleModel){
        driver.findCssSelector("#struct-saleModel").click();
        driver.findCssSelector("#struct-saleModel .next-input").click();
        driver.findCssSelector(".opened .options-item[title*="+ saleModel +"]").click();
    }

    public void setPackNum(String num){
        WebElement lotNumber = driver.findCssSelector("#struct-lotNumber .next-input input");
        lotNumber.sendKeys(num);
    }

    public void addColor(String name,String imgPath){
        //主色系 $('#struct-saleProp .posting-field-list .item .next-select').click()
        //.opened ul li:first
        driver.findCssSelector("#struct-saleProp .posting-field-list .item .next-select");
        List<WebElement> colors = driver.findElementsByCssSelector("#struct-saleProp .posting-field-list .item .next-select");
        colors.get(colors.size()-1).click();
        //都选第一个颜色
        driver.findCssSelector(".opened ul li");
        driver.executeScript("$('.opened ul li:first').click();");
        //自义定名称
        //$('#struct-saleProp .posting-field-list .item .sell-o-input input').val(99)
        driver.findCssSelector("#struct-saleProp .posting-field-list .item .sell-o-input input");
        List<WebElement> SelfDefineNames = driver.findElementsByCssSelector("#struct-saleProp .posting-field-list .item .sell-o-input input");
        SelfDefineNames.get(SelfDefineNames.size()-2).sendKeys(name);
        //上传图片
        //$('#struct-saleProp .posting-field-list .item .posting-feild-color-item .color-upload-wrap input[type=file]')
        List<WebElement> inputFiles = driver.findElementsByCssSelector("#struct-saleProp .posting-field-list .item .posting-feild-color-item .color-upload-wrap input[type=file]");
        if (imgPath != null){
            inputFiles.get(inputFiles.size()-1).sendKeys(imgPath);
        }
    }


    public void batchFill(String USD,String stock,String code) throws InterruptedException {
        //批量填充价格
        driver.scrollIntoViewTrue("#struct-sku");
        Thread.sleep(2000);
        //$('#struct-sku .common-values-wrap .common-btns button').click()
        WebElement piliang = driver.findCssSelector("#struct-sku .common-values-wrap .common-btns button");
        driver.scrollIntoViewTrue(piliang);
        piliang.click();
        Thread.sleep(500);
        //零售价
        //$('.sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice')
        WebElement skuPrice = driver.findCssSelector(".sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice input[placeholder]");
        skuPrice.sendKeys(USD);

        //库存数量
        WebElement skuStock = driver.findCssSelector(".sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuStock input[placeholder]");
        skuStock.sendKeys(stock);

        //商品编码
        WebElement skuOuterId = driver.findCssSelector(".sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuOuterId input[placeholder]");
        skuOuterId.sendKeys(code);

        //填充 Batch fill
        WebElement primary = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-primary");
        WebElement normal = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-normal");

        primary.click();
        normal.click();
        Thread.sleep(500);
    }

    public void alterSizePrice(String sizeName,String price) throws InterruptedException {
        driver.scrollIntoViewTrue("#struct-sku");
            //.col-p-200323261 .next-select-trigger-search input[placeholder]
        WebElement piliang = driver.findCssSelector("#struct-sku .common-values-wrap .common-btns button");
        piliang.click();
        Thread.sleep(500);

        //礼品包装盒尺寸
        driver.findCssSelector(".col-p-200323261 .next-select-trigger-search input[placeholder]").click();
        driver.findCssSelector(".opened .options-search input").sendKeys(sizeName);
        driver.findCssSelector(".opened .options-item[title*=\""+ sizeName +"\"]").click();

        //零售价
        //$('.sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice')
        WebElement skuPrice = driver.findCssSelector(".sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice input[placeholder]");
        skuPrice.sendKeys(price);

        //填充 Batch fill
        WebElement primary = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-primary");
        WebElement normal = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-normal");

        primary.click();
        normal.click();
        Thread.sleep(500);
    }


    public void alterColorPrice(String cusName,String price) throws InterruptedException {
        driver.scrollIntoViewTrue("#struct-sku");
        //.col-p-200323261 .next-select-trigger-search input[placeholder]
        WebElement piliang = driver.findCssSelector("#struct-sku .common-values-wrap .common-btns button");
        piliang.click();
        Thread.sleep(500);

        //通过名字找到颜色名称
        //#struct-saleProp .item
        LinkedHashMap<String,String> map = new LinkedHashMap();
        List<WebElement> webElements = driver.findElementsByCssSelector("#struct-saleProp .item");
        for(int i=0;i<webElements.size()-1;i++){
           String name = webElements.get(i).findElement(By.cssSelector(".sell-o-error-hoc input")).getAttribute("value");
            System.out.println(name);
            String color = webElements.get(i).findElement(By.cssSelector(".color-value")).getText();
            System.out.println(color);
           map.put(name,color);
        }
        System.out.println(map.get("3_10"));
        String colorName = map.get(cusName);
        //颜色
        driver.findCssSelector(".col-p-14 .next-select-trigger-search input[placeholder]").click();
        driver.findCssSelector(".opened .options-search input").sendKeys(colorName);
        driver.findCssSelector(".opened .options-item[title*=\""+ colorName +"\"]").click();

        //零售价
        //$('.sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice')
        WebElement skuPrice = driver.findCssSelector(".sell-sku-thead-wrap .sell-sku-thead.sell-sku-div-head .col-skuPrice input[placeholder]");
        skuPrice.sendKeys(price);

        //填充 Batch fill
        WebElement primary = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-primary");
        WebElement normal = driver.findElementByCssSelector("#struct-sku .common-values-wrap .common-btns .next-btn-normal");

        primary.click();
        normal.click();
        Thread.sleep(500);
    }


    public void alterPrice(String name,String price) throws InterruptedException {
        driver.findCssSelector("#struct-sku .ver-scroll-wrap tbody tr");

        List<WebElement> skus = driver.findElementsByCssSelector("#struct-sku .ver-scroll-wrap tbody tr");

        for (int i=0;i<skus.size();i++){
            String title = skus.get(i).findElement(By.cssSelector(".sell-sku-cell-text p")).getText();
            System.out.println(title);
            if (title.equals(name)){
                driver.scrollIntoViewTrue(skus.get(i));
                WebElement ePrice = skus.get(i).findElement(By.cssSelector(".sell-sku-cell-money input"));
                ePrice.sendKeys(Keys.CONTROL,"a");
                ePrice.sendKeys(Keys.DELETE);
                ePrice.sendKeys(price);
                return;
            }
        }

    }

    public void setTradePrice(String batch,String price) throws InterruptedException {
        //$('#struct-bulkPrice input[type=checkbox]')
        driver.scrollIntoViewTrue("#struct-bulkPrice input[type=checkbox]");
        driver.findElementByCssSelector("#struct-bulkPrice input[type=checkbox]").click();
        Thread.sleep(1000);
        //$('#struct-bulkPrice .price-calc-text input')
        driver.findCssSelector("#struct-bulkPrice .price-calc-text input");
        WebElement ebatch = driver.findElementsByCssSelector("#struct-bulkPrice .price-calc-text input").get(0);
        WebElement eprice = driver.findElementsByCssSelector("#struct-bulkPrice .price-calc-text input").get(1);

        ebatch.sendKeys(batch);
        eprice.sendKeys(price);
    }

    public void setPreparationTime(String time) throws InterruptedException {
        driver.scrollIntoViewTrue("#deliveryPeriod");
        driver.findCssSelector("#deliveryPeriod").sendKeys(time);
    }

    public void setPackageWeight(String weight) throws InterruptedException {
        driver.scrollIntoViewTrue("#package-card #struct-logisticsWeight .fusion-input input");
        driver.findCssSelector("#package-card #struct-logisticsWeight .fusion-input input").sendKeys(weight);
    }

    public void setPackageSize(String length,String width,String high) throws InterruptedException {
        driver.scrollIntoViewTrue("#package-card #struct-logisticsSize input");
        driver.findCssSelector("#package-card #struct-logisticsSize input");
        driver.findElements(By.cssSelector("#package-card #struct-logisticsSize input")).get(0).sendKeys(length); //长
        driver.findElements(By.cssSelector("#package-card #struct-logisticsSize input")).get(1).sendKeys(width); //长
        driver.findElements(By.cssSelector("#package-card #struct-logisticsSize input")).get(2).sendKeys(high); //长
        driver.findElements(By.cssSelector("#package-card #struct-logisticsSize input")).get(0).sendKeys(""); //长
    }

    public void setShippingTemplate(String template) throws InterruptedException {
        driver.scrollIntoViewTrue("#struct-freightTemplate");
        //$('#struct-freightTemplate input')
        driver.findCssSelector("#struct-freightTemplate input");
        WebElement freightTemplate = driver.findCssSelector("#package-card #struct-freightTemplate .ae-ship-fee-template > span input");
        freightTemplate.click();
        driver.findCssSelector(".options-item[title*="+template+"]").click();
    }

    public void desc(File[] imgPaths, String txt) throws InterruptedException {

        //详细描述 #desc-card
        driver.scrollIntoViewTrue("#desc-card");
        driver.executeScript("$('#desc-card .next-radio-group label').eq(1).click()");
        Thread.sleep(2000);
        WebElement iframe =  driver.findElement(By.cssSelector("#struct-standardDesc .rich-editor-iframe"));
        driver.switchTo().frame(iframe);


        driver.findCssSelector(".kse-button-image").click();
        Thread.sleep(500);
        WebElement webElement = driver.findElementByCssSelector(".kse-upload-sandbox .ksu-html5 input");

        for(int i=0;i<8 && i<imgPaths.length;i++){
            //图片地址
            System.out.println(imgPaths[i].getAbsolutePath());
            webElement.sendKeys(imgPaths[i].getAbsolutePath());
        }

        WebElement iframe2 =  driver.findElement(By.cssSelector("#kse-editor5 iframe"));
        //kse-dialog-buttons
        /*WebElement okBtn = driver.findElementsByCssSelector(".kse-dialog-buttons");*/

        WebElement okBtn = driver.findElementsByCssSelector("button").get(0);
        System.out.println(okBtn.getAttribute("disabled"));
        while(true){
            System.out.println(okBtn.getAttribute("disabled"));
            try {
                okBtn.getAttribute("disabled");
                if (  okBtn.getAttribute("disabled") == null){
                    break;
                }
            }catch (Exception e){
                break;
            }
            Thread.sleep(300);
        }
        okBtn.click();
        driver.switchTo().frame(iframe2);
        driver.executeScript("pd = document.querySelector('body')");
        driver.executeScript("pd.innerHTML = \"" + txt + "\" + pd.innerHTML;");

        driver.switchTo().defaultContent();

    }

    //礼品包装盒尺寸
    public void setGiftBoxSizes(List<String> giftBoxSizes) throws InterruptedException {

        driver.scrollIntoViewTrue("#struct-p-200323261");
        List<WebElement> checkboxs = driver.findElementsByCssSelector("#struct-p-200323261 .option-wrap input[type=checkbox]");
        List<WebElement> texts = driver.findElementsByCssSelector("#struct-p-200323261 .option-wrap .next-input input");
       for (int i=0;i<giftBoxSizes.size();i++){
           checkboxs.get(i).click();
           texts.get(i).sendKeys(Keys.CONTROL,"a");
           texts.get(i).sendKeys(Keys.DELETE);
           texts.get(i).sendKeys(giftBoxSizes.get(i));
       }

    }

    public void submit(){
        driver.executeScript("$('#struct-buttons button:contains(\"提交\")').click()");
    }

}
