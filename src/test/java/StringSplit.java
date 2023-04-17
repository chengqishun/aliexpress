import java.util.HashMap;

public class StringSplit {

    public static void main(String[] args) {
        String data = "Brand Name: PABCK###Origin: CN(Origin)###Thickness: 8 wire###Applicable Space: living room###Type: Storage Bags###Feature: Eco-Friendly###Material: Plastic###Plastic Type: PP###Form: Bag Compression Type###Pattern: Flat Type###Capacity: 100ml###Shape: SQUARE###Use: electronic products###Specification: 18*26cm###Product: storage bag###Model Number: 18*26cm";

        String[] arr = data.split("###");

        HashMap map = new HashMap();
        for (String a : arr) {
            System.out.println(a);
            String[] b = a.split(":");
            map.put(b[0].trim(),b[1].trim());

        }

        System.out.println(map.get("Origin"));

    }

}
