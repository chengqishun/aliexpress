package common;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    Workbook workbook;
    Sheet sheetAt;
    FileInputStream FileInputStream = null;
    int rowcount;

    public String PATH;

    public String getFolderPath() {

        return this.getPATH().substring(0,this.getPATH().lastIndexOf("\\"));
    }
    public String getPATH() {
        return PATH;

    }


    public Excel(String path) throws IOException {
        this.PATH = path;

        this.FileInputStream = new FileInputStream(PATH);
        String suffix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
        if (suffix.equals("xlsx")){
            this.workbook = new XSSFWorkbook(FileInputStream);
        }else{
            this.workbook = new HSSFWorkbook(FileInputStream);
        }
        this.sheetAt = workbook.getSheetAt(0);
        rowcount = sheetAt.getLastRowNum();
    }

    public String read(int row, int cell){
        row-=1;
        cell-=1;
        Row excelRow = this.sheetAt.getRow(row);

        try {
            excelRow.getCell(cell);

        }catch(Exception e){
            return "";
        }

        Cell excelCell = excelRow.getCell(cell);
        /*       excelCell.setCellType(HSSFCell.CELL_TYPE_STRING);*/
        if (excelCell != null){
            excelCell.setCellType(CellType.STRING);
            return excelCell.getStringCellValue();
        }else{
            return "";
        }
    }

    public void write(int row,int cell,String content){
        row-=1;
        cell-=1;
        Row excelRow = this.sheetAt.getRow(row);
        Cell excelCell = excelRow.getCell(cell);

        if (excelCell!=null){
            excelCell.setCellValue(content);
        }else{
            excelRow.createCell(cell);
            excelCell = excelRow.getCell(cell);
            excelCell.setCellType(CellType.STRING);
            excelCell.setCellValue(content);
        }
        try {
            saveExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int length(){
        return this.rowcount + 1;
    }

    public void saveExcel() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(PATH);
        workbook.write(fileOut);
    }

    public void closeExcel(){
        try {
            FileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
