package kr.excel.example;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExcelExample {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("example.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            for(Row row: sheet){
                for(Cell cell: row){
                    System.out.print(cell.toString()+"\t");
                }
                System.out.println(); //줄바꿈
            }
            file.close();
            System.out.println("엑셀에서 데이터읽어오기");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
