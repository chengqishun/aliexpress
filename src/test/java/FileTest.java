import filter.FilterBysuffix;

import java.io.File;


public class FileTest {


    public static void main(String[] args) {
        String PT = "Z:\\allimages\\images\\办公文教 & 工商业\\包装袋\\Edibles Dank Gummies bags 20201005\\att";
        File file = new File(PT);

        if (file.exists()){
            System.out.println("路径存在");
        }

        System.out.println(file.list().length);
        FilterBysuffix filter = new FilterBysuffix();
        File[] ss = file.listFiles(filter);
        System.out.println(file.list(filter).length);
         for(int i=0;i<ss.length;i++){
             System.out.println(ss[i].getAbsolutePath());

         }

    }

}

