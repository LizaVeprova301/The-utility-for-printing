import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class GeneratePdf {
    private final String srcFolder; // Путь к папке с изображениями
    private String destPdf; // Путь к создаваемому PDF файлу
    private final File folder;
    public static final float width = 380; // ширина изображения
    public static final float height = 560; // высота изображения
    public PdfWriter writer;
    public PdfDocument pdf;
    public Document document;
    private static final Paragraph spacer = new Paragraph().setMarginTop(56.7f);

    private final File[] imageFiles;

    private Image image;

    public GeneratePdf() {
        srcFolder = "src/main/resources/images";
        destPdf = "src/main/resources/pdf/output.pdf";
        folder = new File(srcFolder);
        imageFiles = folder.listFiles();
    }


    public void createImage(int count) throws MalformedURLException {
        ImageData imageData = ImageDataFactory.create(imageFiles[count].getAbsolutePath());
        this.image = new Image(imageData).setWidth(width).setHeight(height); // Установка размеров изображения;
        this.image.setRotationAngle(Math.PI / 2);
    }

    public void createPdf() throws MalformedURLException, FileNotFoundException {
        writer = new PdfWriter(destPdf);
        pdf = new PdfDocument(writer);
        document = new Document(pdf);
        document.setMargins(10, 10, 10, 10);
        int count = 1;
        if (imageFiles != null) {
            while (count <= imageFiles.length) {
                try {
                    switch (count % 4) {
                        case 0:
                            createImage(count - 1);
                            document.add(image);
                            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                            count -= 2;
                            break;
                        case 1:
                            createImage(count - 1);
                            document.add(image);
                            document.add(spacer);
                            count += 3;
                            break;
                        case 2:
                            createImage(count - 1);
                            document.add(image);
                            document.add(spacer);
                            count++;
                            break;
                        case 3:
                            createImage(count - 1);
                            document.add(image);
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

    }

    public String getDestPdf() {
        return destPdf;
    }

    public File[] getImageFiles() {
        return imageFiles;
    }

    public Image getImage() {
        return image;
    }


}
