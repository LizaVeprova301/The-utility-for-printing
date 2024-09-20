import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;

import java.io.File;
import java.io.IOException;

public class Main {
    public static GeneratePdf generatePdf = new GeneratePdf();
    private static int count = 1;
    private static final Paragraph spacer = new Paragraph().setMarginTop(56.7f);

    public static void main(String[] args) throws IOException {
        generatePdf.createPdf();
        File[] imageFiles = generatePdf.getImageFiles();

        // Создаем PDF документ
        PdfWriter writer = new PdfWriter(generatePdf.getDestPdf());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(10, 10, 10, 10);


        if (imageFiles != null) {
            while (count <= imageFiles.length) {
                try {
                    switch (count % 4) {
                        case 0:
                            generatePdf.createImage(count - 1);
                            document.add(generatePdf.getImage());
                            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                            count -= 2;
                            break;
                        case 1:
                            generatePdf.createImage(count - 1);
                            document.add(generatePdf.getImage());
                            document.add(spacer);
                            count += 3;
                            break;
                        case 2:
                            generatePdf.createImage(count - 1);
                            document.add(generatePdf.getImage());
                            document.add(spacer);
                            count++;
                            break;
                        case 3:
                            System.out.println("До count = " + count);
                            generatePdf.createImage(count - 1);
                            document.add(generatePdf.getImage());
                            PdfPage pageToMirror = pdf.getPage((count + 1) / 2);
                            pageToMirror.setRotation(180); // Установить поворот страницы на 180 градусов
                            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                            count += 2;
                            break;
                        default:
                            System.out.println("Что-то пошло не так");
                    }
                } catch (Exception e) {
                    if (count <= imageFiles.length) {
                        continue;
                    }
                    break;
                }

            }
        }

        document.close();
        System.out.println("PDF создан: " + generatePdf.getDestPdf());
    }
}