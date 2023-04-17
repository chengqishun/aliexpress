
import common.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelTest {
    public static void main(String[] args) throws IOException {

        Excel excel = new Excel("C:\\Users\\vktea\\Desktop\\必跑20201102版.xlsx");
        System.out.println(excel.getPATH().lastIndexOf("\\"));
        System.out.println(excel.getPATH().substring(0,22));
        excel.closeExcel();
    }
}
