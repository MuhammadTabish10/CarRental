package org.carrental.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import org.carrental.service.ReportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommissionReport extends BaseUI
{
    private final String[] columnNames = {"Vehicle Owner Id", "Vehicle Owner Name", "Commission"};
    private String[][] data;
    private DefaultTableModel dtm;
    private JTable table;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private ReportService reportService = new ReportService();

    public CommissionReport()
    {
        JFrame frame = new JFrame("Monthly Report");
        frame.getContentPane().setBackground(new Color(52, 73, 94));
        frame.setLayout(new BorderLayout());

        startDateChooser = new JDateChooser();
        endDateChooser = new JDateChooser();

        startDateChooser.setDateFormatString("yyyy-MM-dd");
        endDateChooser.setDateFormatString("yyyy-MM-dd");

        startDateChooser.setDate(new Date());
        endDateChooser.setDate(new Date());

        JButton pdfBtn = createStyledButton("Generate Pdf");
        JButton backBtn = createStyledButton("Back");

        // Create the panel
        JPanel datePanel = new JPanel();
        JPanel downPanel = new JPanel();

        dtm = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable.
            }
        };
        table = new JTable(dtm);
        table.setEnabled(true);
        JScrollPane scrollTablePane = new JScrollPane(table);
        JViewport viewport = scrollTablePane.getViewport();
        viewport.setBackground(new Color(52, 73, 94));

        startDateChooser.addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                updateTableData();
            }
        });

        endDateChooser.addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                updateTableData();
            }
        });

        pdfBtn.addActionListener(e -> generatePdf());

        backBtn.addActionListener(e -> {
            frame.dispose();
            new ReportUI();
        });

        datePanel.add(new JLabel("Start Date: "));
        datePanel.add(startDateChooser);
        datePanel.add(new JLabel("End Date: "));
        datePanel.add(endDateChooser);

        downPanel.add(backBtn);
        downPanel.add(pdfBtn);

        frame.add(datePanel, BorderLayout.NORTH);
        frame.add(scrollTablePane, BorderLayout.CENTER);
        frame.add(downPanel, BorderLayout.SOUTH);

        frame.setSize(1100, 650);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateTableData() {
        Date startDate = startDateChooser.getDate();
        Date endDate = endDateChooser.getDate();
        data = reportService.getAllVehicleOwnersWithCommission(
                new java.sql.Date(startDate.getTime()),
                new java.sql.Date(endDate.getTime())
        );
        dtm.setDataVector(data, columnNames);
    }

    private void generatePdf() {
        try {
            Document document = new Document();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = startDateChooser.getDate();
            Date endDate = endDateChooser.getDate();
            String filename = "MonthlyReport_" + dateFormat.format(startDate) + "_to_" + dateFormat.format(endDate) + ".pdf";
            String filePath = "C:\\Users\\muham\\OneDrive\\Desktop\\JAVA WORK\\projects\\CarRental\\Reports";
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(filePath + File.separator + filename)));
            document.open();

            // Add the heading "Monthly Report"
            com.itextpdf.text.Font headingFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            Phrase heading = new Phrase("Commission Report", headingFont);
            Paragraph headingParagraph = new Paragraph(heading);
            headingParagraph.setAlignment(Element.ALIGN_CENTER);
            headingParagraph.setSpacingAfter(10);
            document.add(headingParagraph);

            // Add the start date and end date
            String dateString = "Start Date: " + dateFormat.format(startDate) + "\nEnd Date: " + dateFormat.format(endDate);
            com.itextpdf.text.Font dateFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12);
            Phrase datePhrase = new Phrase(dateString, dateFont);
            Paragraph dateParagraph = new Paragraph(datePhrase);
            dateParagraph.setAlignment(Element.ALIGN_CENTER);
            dateParagraph.setSpacingAfter(20);
            document.add(dateParagraph);

            // Create the PDF table
            PdfPTable pdfTable = new PdfPTable(columnNames.length);
            pdfTable.setWidthPercentage(100);

            // Add table headers
            for (String column : columnNames) {
                PdfPCell headerCell = new PdfPCell(new Phrase(column));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(headerCell);
            }

            // Add data rows
            for (String[] rowData : data) {
                for (String cellData : rowData) {
                    PdfPCell cell = new PdfPCell(new Phrase(cellData));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTable.addCell(cell);
                }
            }
            pdfTable.setSpacingBefore(20);

            // Add the table to the document
            document.add(pdfTable);
//
//            // Add the price text
//            price = reportService.getAllPriceForMonthlyReport(
//                    new java.sql.Date(startDate.getTime()),
//                    new java.sql.Date(endDate.getTime())
//            );
//
//            com.itextpdf.text.Font priceFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
//            String priceText = "Total Price of vehicles: " + price;
//            Paragraph priceParagraph = new Paragraph(priceText, priceFont);
//            priceParagraph.setAlignment(Element.ALIGN_LEFT);
//            document.add(priceParagraph);
//
//            // Add the commission text
//            commission = reportService.getAllCommissionForMonthlyReport(
//                    new java.sql.Date(startDate.getTime()),
//                    new java.sql.Date(endDate.getTime())
//            );
//
//            com.itextpdf.text.Font commissionFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
//            String commissionText = "Total Commission earned by vehicle owners: " + commission;
//            Paragraph commissionParagraph = new Paragraph(commissionText, commissionFont);
//            commissionParagraph.setAlignment(Element.ALIGN_LEFT);
//            document.add(commissionParagraph);
//
//            // Add the profit text
//            profit = reportService.getAllProfitForMonthlyReport(
//                    new java.sql.Date(startDate.getTime()),
//                    new java.sql.Date(endDate.getTime())
//            );
//
//            com.itextpdf.text.Font profitFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
//            String profitText = "Total Profit earned: " + profit;
//            Paragraph profitParagraph = new Paragraph(profitText, profitFont);
//            profitParagraph.setAlignment(Element.ALIGN_LEFT);
//            document.add(profitParagraph);

            document.close();

            // Open the saved PDF
            Desktop.getDesktop().open(new File(filePath));
            JOptionPane.showMessageDialog(null, "PDF Report generated successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating PDF report!");
        }
    }
}
