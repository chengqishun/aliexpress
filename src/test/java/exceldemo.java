

import common.Excel;
import datas.Data;

import java.io.IOException;

public class exceldemo {
    public static void main(String[] args) throws IOException {
        Excel excel = new Excel("Z:\\allimages\\dhgate_ali_multi\\测试数据\\Envelopes\\Mailers Envelopes\\Mailers Envelopes_dhmulti20201113115335.xlsx");
        Data data = new Data(excel,6);
        excel.closeExcel();
    }
}
