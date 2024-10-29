import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PathInputApp {
    private final JTextField srcFolderField;
    private final JTextField destPdfField;

    public PathInputApp() {
        JFrame frame = new JFrame("Path Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel srcFolderLabel = new JLabel("Source Folder:");
        srcFolderField = new JTextField();
        JLabel destPdfLabel = new JLabel("Destination PDF:");
        destPdfField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        frame.add(srcFolderLabel);
        frame.add(srcFolderField);
        frame.add(destPdfLabel);
        frame.add(destPdfField);
        frame.add(submitButton);

        frame.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String srcFolder = srcFolderField.getText();
            String destPdf = destPdfField.getText();

            // Здесь вы можете использовать введенные пути
            System.out.println("Source Folder: " + srcFolder);
            System.out.println("Destination PDF: " + destPdf);
            try {
                System.out.println("PDF создан: " + GenerateEndPdf.generateEndPdf(srcFolder,destPdf));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            // Ваша логика обработки файлов
            // Например, вызов метода для создания PDF
        }
    }
}