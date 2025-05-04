package kr.excel.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.time.Year;
import java.util.HashMap;

public class BookInfoToPdf {
    public static void main(String[] args) {
        HashMap<String, String> bookInfo = new HashMap<>();
        bookInfo.put("title","한글 자바");
        bookInfo.put("author" , "홍길동");
        bookInfo.put("year",String.valueOf(Year.now().getValue()));
        bookInfo.put("price" , "10000");
        bookInfo.put("pages","400");
        try {
            PdfWriter writer = new PdfWriter("book.pdf");

            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            for (String key : bookInfo.keySet()) {
                Paragraph paragraph = new Paragraph(key + ": " + bookInfo.get(key));
                document.add(paragraph);
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
