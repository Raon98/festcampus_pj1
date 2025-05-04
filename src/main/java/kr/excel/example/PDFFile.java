package kr.excel.example;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class PDFFile {
    public static void main(String[] args) throws IOException {
        String desk = "book_table.pdf";
        new PDFFile().createPdf(desk);
    }

    public void createPdf(String dest) throws MalformedURLException, IOException {
        List<Map<String, String>> books = List.of(
            Map.of(
                "title", "Effective Java",
                "authors", "Joshua Bloch",
                "publisher", "Addison-Wesley",
                "publishedDate", "2018-01-06",
                "thumbnail", "effect_java_thumbnail.png"
            ),
            Map.of(
                "title", "Clean Code",
                "authors", "Robert C. Martin",
                "publisher", "Prentice Hall",
                "publishedDate", "2008-08-11",
                "thumbnail", "clean_code_thumbnail.png"
            ),
            Map.of(
                "title", "Refactoring",
                "authors", "Martin Fowler",
                "publisher", "Addison-Wesley",
                "publishedDate", "2018-11-19",
                "thumbnail", "refactoring_thumbnail.png"
            )
        );

        // Initialize PDF writer and PDF document
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize fonts
        PdfFont headerFont = null;
        Document bodyFont = null;
//        try {
//            headerFont = PdfFontFactory.createFont("NanumGothicLight.ttf", "Identity-H", true);
//            bodyFont = PdfFontFactory.createFont("NanumGothicLight.ttf", "Identity-H", true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Initialize table
        float[] columnWidths = {1, 2, 2, 2, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));
        // Initialize table header cells
        Cell headerCell1 = new Cell().add(new Paragraph("순번"));
        Cell headerCell2 = new Cell().add(new Paragraph("제목"));
        Cell headerCell3 = new Cell().add(new Paragraph("저자"));
        Cell headerCell4 = new Cell().add(new Paragraph("출판사"));
        Cell headerCell5 = new Cell().add(new Paragraph("출판일"));
        Cell headerCell6 = new Cell().add(new Paragraph("이미지"));
        table.addHeaderCell(headerCell1);
        table.addHeaderCell(headerCell2);
        table.addHeaderCell(headerCell3);
        table.addHeaderCell(headerCell4);
        table.addHeaderCell(headerCell5);
        table.addHeaderCell(headerCell6);
        // Add table body cells
        int rowNum = 1;
        for (Map<String, String> book : books) {
            String title = book.get("title");
            String authors = book.get("authors");
            String publisher = book.get("publisher");
            String publishedDate = book.get("publishedDate");
            String thumbnail= book.get("thumbnail");
            Cell rowNumCell = new Cell().add(new Paragraph(String.valueOf(rowNum)));
            table.addCell(rowNumCell);
            Cell titleCell = new Cell().add(new Paragraph(title));
            table.addCell(titleCell);
            Cell authorsCell = new Cell().add(new Paragraph(authors));
            table.addCell(authorsCell);
            Cell publisherCell = new Cell().add(new Paragraph(publisher));
            table.addCell(publisherCell);
            Cell publishedDateCell = new Cell().add(new Paragraph(publishedDate));
            table.addCell(publishedDateCell);
            ImageData imageData = ImageDataFactory.create(new File(thumbnail).toURI().toURL());
            Image img = new Image(imageData);
            Cell imageCell = new Cell().add(img.setAutoScale(true));
            table.addCell(imageCell);
            rowNum++;
        }
        document.add(table);
        document.close();
    }
}
