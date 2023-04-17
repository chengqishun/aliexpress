import common.Chrome;

import common.Excel;
import datas.Data;
import pages.CategoryPage;
import pages.LoginPage;
import pages.ProductPage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", Chrome.ChromedriverPath);

        Excel excel = new Excel("Z:\\allimages\\dhgate_ali_multi\\测试数据\\Envelopes\\Mailers Envelopes\\Mailers Envelopes_dhmulti20201113115335.xlsx");
        Data data = new Data(excel,6);

        Chrome chrome = new Chrome();

        LoginPage loginPage = new LoginPage(chrome);
        loginPage.goPage();
        // 账号密码
        loginPage.login("","");

        CategoryPage categoryPage = new CategoryPage(chrome);
        categoryPage.goPage();
        chrome.importJquery();
        categoryPage.setCategory(data.getCategory());
        categoryPage.submit();

        ProductPage productPage = new ProductPage(chrome);
        productPage.init();
     /*   productPage.goEnglish();*/
        productPage.title(data.getTitle());
        productPage.productImg(data.getProductPictureFolder());
        productPage.productSimpleAtt();

        //价格和库存 sale-card
        productPage.setMeasurement(data.getMeasurementUnit());

        //销售方式 struct-saleModel
        productPage.setSaleModel(data.getSoldIn());

        //包数
        productPage.setPackNum("10");

        //颜色
        productPage.addColor("Mix Color", null); //添加 Mix Color
        LinkedHashMap<String,String> colors = data.getColors();
        for (Map.Entry<String, String> entry : colors.entrySet()) {
            String name = entry.getKey();
            String path = entry.getValue();
            System.out.println(path);
            productPage.addColor(name, path);
        }
        productPage.addColor("10x14cm OPP Bags", "C:\\Users\\vktea\\Desktop\\13947879121_1191457928.400x400.jpg"); //添加 10x14cm OPP Bags

        //礼品包装盒尺寸
        productPage.setGiftBoxSizes(data.getGiftBoxSizes());

        //更新价格table
        productPage.batchFill("","","");

        //批量填充价格
        System.out.println(data.getMaxPrice());
        productPage.batchFill(data.getMaxPrice(),"99999","");

        //修改特定尺寸价格
        productPage.alterSizePrice("4","10");

        //通过名称修改价格
        productPage.alterColorPrice("10x14cm OPP Bags","5");

        //价格
        //document.getElementsByClassName("ver-scroll-wrap")[0].scrollTop=10000
        productPage.alterPrice("as pic 1","1");

        //批发价
    /*    productPage.setTradePrice("10","10%");*/
        //详细描述
        productPage.desc(data.getDescImgPaths(),data.getDescription());

        productPage.setPreparationTime("4");
        productPage.setPackageWeight("0.4");
        productPage.setPackageSize("20","20","15");
        productPage.setShippingTemplate(data.getShippingTemplate());
      /*  productPage.submit();*/

    }
}