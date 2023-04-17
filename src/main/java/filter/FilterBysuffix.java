package filter;


import java.io.File;
import java.io.FilenameFilter;

public class FilterBysuffix implements FilenameFilter {
/*    private String suffix;*/
    public FilterBysuffix(){
        /*this.suffix=suffix;*/
    }

    public boolean accept(File dir, String name) {
        if (name.endsWith(".jpg")||name.endsWith(".png")){
            return true;
        }else{
            return false;
        }
    }
}