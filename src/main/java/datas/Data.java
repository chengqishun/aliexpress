package datas;

import com.alibaba.fastjson.JSONObject;
import common.Excel;
import filter.FilterBysuffix;

import java.io.File;
import java.util.*;

public class Data {

    Excel excel;
    int row;

    String title;
    String category;
    String productPictureFolder;
    LinkedHashMap details;
    String measurementUnit;
    String soldIn;
    String packNum;
    LinkedHashMap<String, String> colors;
    List<String> giftBoxSizes = new ArrayList<>();
    String giftBagSize;
    List<String> batchFillPrices;
    LinkedHashMap<String,Object> prices = new LinkedHashMap();
    String maxPrice;
    LinkedHashMap<String,String> wholesalePrice = new LinkedHashMap<>();
    String description;
    String shippingPreparationTime;
    String packageWeight;
    LinkedHashMap packageSize = new LinkedHashMap();
    String shippingTemplate;

    File[] descImgPaths;


    public Data(Excel excel, int row) {
        this.excel = excel;
        this.row = row;
        this.setTitle();
        this.setGiftBoxSize();
        setCategory();
        setProductPictureFolder();
        setDetails();
        setMeasurementUnit();
        setColors();
        setBatchFillPrices();
        setPrices();
        setWholesalePrice();//批发价
        setDescription();
        setShippingPreparationTime();
        setPackageWeight();
        setPackageSize();
        setShippingTemplate();
        setMaxPrice();
        setDescImgPaths();
    }

    public File[] getDescImgPaths() {
        return descImgPaths;
    }

    public void setDescImgPaths() {
        String imgFolderPath = excel.getFolderPath() + "\\" + excel.read(row,2) + "\\" + "C";
        //获取图片路径
        File file = new File(imgFolderPath);
        if (file.exists()){
            System.out.println("路径存在");
        }
        FilterBysuffix filter = new FilterBysuffix();
        File imgFileList[] = file.listFiles(filter);

        this.descImgPaths = imgFileList;
    }

    public void setTitle() {
        this.title = excel.read(row,3);
    }

    public void setCategory() {
        this.category = excel.read(row,13);
    }

    public void setProductPictureFolder() {
        this.productPictureFolder = excel.getFolderPath() + "\\" + excel.read(row,2);
    }

    public void setDetails() {
        String data = excel.read(row,7);
        String[] arr = data.split("###");
        LinkedHashMap map = new LinkedHashMap();
        for (String a : arr) {
            String[] b = a.split(":");
            map.put(b[0].trim(),b[1].trim());
        }
        this.details = map;
    }

    public void setMeasurementUnit() {
        int pieceLot = Integer.parseInt(excel.read(row,14));

        if (pieceLot>1){
            this.measurementUnit = "pack";
            this.soldIn = "打包出售";
            //包数
            packNum = String.valueOf(pieceLot);
        }else{
            this.measurementUnit = "piece";
        }

    }

    public void setColors() {

        String str = excel.read(row,5);
        String[] colorName = str.split(",");
        LinkedHashMap<String,String> map = new LinkedHashMap();
        for (String imgName : colorName){
            String path = excel.getFolderPath()+ "\\" + excel.read(row,2) + "\\" + "A\\" + imgName + ".jpg";
            map.put(imgName,path);
        }
        colors = map;
    }

    //礼品包装盒尺寸
    public void setGiftBoxSize() {
        String str = excel.read(row,8);

        if (str.contains("{")){
            JSONObject jsonObject = JSONObject.parseObject(str);
            Map<String,Object> map = (Map<String,Object>)jsonObject;//    //json对象转Map

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                mapKey = (mapKey.substring(0, mapKey.lastIndexOf("_")));
                giftBoxSizes.add(mapKey);
            }
        }else{
        }
    }

    //礼品包装袋尺寸
    public void setGiftBagSize() {
        this.giftBagSize = "";
    }

    public void setBatchFillPrices() {
        List<String> batchFills = new ArrayList<>();
        batchFills.add("20");
        batchFills.add("20");
        batchFills.add("15");

        this.batchFillPrices = batchFills;
    }

    public void setPrices() {

        String str = excel.read(row,8);
        if (str.contains("{")){
            JSONObject jsonObject = JSONObject.parseObject(str);
            Map<String,Object> map = (Map<String,Object>)jsonObject;//    //json对象转Map

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Object value = entry.getValue();
                mapKey = (mapKey.substring(0, mapKey.lastIndexOf("_")));
                prices.put(mapKey,value);
            }

            for (Map.Entry<String, Object> entry : prices.entrySet()) {
                String mapKey = entry.getKey();
                Object value = entry.getValue();
                System.out.println(mapKey);
                System.out.println(value);
            }
        }else{
            prices.put("default",str);
        }

    }

    public void setMaxPrice() {
        this.maxPrice = excel.read(row,21);
    }

    public void setWholesalePrice() {
        wholesalePrice = null;
    }

    public void setDescription() {
        this.description = excel.read(row,6);
    }

    //发货期
    public void setShippingPreparationTime() {
        this.shippingPreparationTime = "4";
    }

    public void setPackageWeight() {
        this.packageWeight = "0.4";
    }

    public void setPackageSize() {
        packageSize.put("long","20");
        packageSize.put("width","20");
        packageSize.put("height","15");
    }

    public void setShippingTemplate() {
        this.shippingTemplate = "小包";
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getProductPictureFolder() {
        return productPictureFolder;
    }

    public LinkedHashMap getDetails() {
        return details;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public String getSoldIn() {
        return soldIn;
    }

    public String getPackNum() {
        return packNum;
    }

    public LinkedHashMap<String, String> getColors() {
        return colors;
    }

    public List<String> getGiftBoxSizes() {
        return giftBoxSizes;
    }

    public String getGiftBagSize() {
        return giftBagSize;
    }

    public List<String> getBatchFillPrices() {
        return batchFillPrices;
    }

    public LinkedHashMap<String, Object> getPrices() {
        return prices;
    }

    public LinkedHashMap<String, String> getWholesalePrice() {
        return wholesalePrice;
    }

    public String getDescription() {
        return description;
    }

    public String getShippingPreparationTime() {
        return shippingPreparationTime;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public LinkedHashMap getPackageSize() {
        return packageSize;
    }

    public String getShippingTemplate() {
        return shippingTemplate;
    }

    public String getMaxPrice() {
        return maxPrice;
    }
}
