package com.example.carmanager;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class PDFInvoiceGenerator {
    private Document document;
    private String pdfName;
    private Reservation reservation;
    private Font titleFont;
    private Font boldFont;
    private Font normalFont;

    public PDFInvoiceGenerator(String pdfName, Reservation reservation) {
        this.pdfName = pdfName;
        this.reservation = reservation;
        titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
        boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
        normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
    }

    public void createDocument() throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
        document.open();
    }

    public void addContent() throws DocumentException {
        // Add title
        Chunk titleChunk = new Chunk("INVOICE", titleFont);
        document.add(titleChunk);

        // Adding some space before the company details
        document.add(new Paragraph("\n\n\n\n"));

        // Create a table for company details and invoice date
        PdfPTable companyTable = new PdfPTable(new float[]{2, 1});
        companyTable.setWidthPercentage(100);

        // Company details
        PdfPCell companyDetailsCell = new PdfPCell();
        companyDetailsCell.setBorder(PdfPCell.NO_BORDER);
        Paragraph companyDetails = new Paragraph(
                "Company Name\n" +
                        "1234 Random Street\n" +
                        "City, Postcode\n" +
                        "Phone: 123-456-7890\n" +
                        "Email: company@example.com", normalFont);
        companyDetailsCell.addElement(companyDetails);
        companyTable.addCell(companyDetailsCell);

        // Invoice date
        PdfPCell invoiceDateCell = new PdfPCell();
        invoiceDateCell.setBorder(PdfPCell.NO_BORDER);
        invoiceDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        LocalDate invoiceDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Paragraph invoiceDateParagraph = new Paragraph("Invoice Date: " + invoiceDate.format(formatter), normalFont);
        invoiceDateCell.addElement(invoiceDateParagraph);
        companyTable.addCell(invoiceDateCell);

        document.add(companyTable);

        // Add a line separator
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());

        // Add some space before the "Ship To" section
        document.add(new Paragraph("\n"));

        // Add "Ship To" section
        Paragraph shipToParagraph = new Paragraph("Ship To", boldFont);
        document.add(shipToParagraph);

        Customer customer = reservation.getCustomer();
        Paragraph customerDetails = new Paragraph(
                customer.getName() + " " + customer.getSurname() + "\n" +
                        customer.getEmail() + "\n" +
                        customer.getPhone(), normalFont);
        document.add(customerDetails);

        // Adding some space before the table
        document.add(new Paragraph("\n\n"));

        PdfPTable table = new PdfPTable(3);  // Changed to 3 columns
        table.setWidthPercentage(100);
        addTableHeader(table);
        addRows(table);
        document.add(table);

        // Add total price below the table
        double pricePerDay = reservation.getVehicle().getPrice();
        long numberOfDays = calculateNumberOfDays(reservation.getStartDate(), reservation.getEndDate());
        double totalSum = pricePerDay * numberOfDays;
        Paragraph totalParagraph = new Paragraph("Total Price: $" + totalSum, boldFont);
        totalParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalParagraph);

        // Adding payment methods
        document.add(new Paragraph("\n\n"));
        Paragraph paymentMethodParagraph = new Paragraph("Payment Method:\n\n", boldFont);
        document.add(paymentMethodParagraph);

        PdfPTable paymentMethodTable = new PdfPTable(new float[]{1, 1, 1, 1});
        paymentMethodTable.setWidthPercentage(100);

        addCheckbox(paymentMethodTable, "Cash");
        addCheckbox(paymentMethodTable, "Credit Card");
        addCheckbox(paymentMethodTable, "Bank Transfer");
        addCheckbox(paymentMethodTable, "Online Payment");

        document.add(paymentMethodTable);

        // Adding space after payment methods
        document.add(new Paragraph("\n\n"));

        // Add signature line at the bottom right
        PdfPTable signatureTable = new PdfPTable(1);
        signatureTable.setWidthPercentage(100);
        signatureTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

        PdfPCell signatureCell = new PdfPCell();
        signatureCell.setBorder(PdfPCell.NO_BORDER);
        signatureCell.setFixedHeight(50);
        signatureCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Paragraph signatureLine = new Paragraph("________________________", normalFont);
        Paragraph signatureName = new Paragraph(customer.getName() + " " + customer.getSurname(), normalFont);
        signatureCell.addElement(signatureLine);
        signatureCell.addElement(signatureName);
        signatureTable.addCell(signatureCell);



        // Move signature section to the bottom of the page
        document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n"));
        document.add(signatureTable);
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Vehicle", "Number of Days", "Price per Day")  // Changed column headers
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(new BaseColor(173, 216, 230));  // Light blue background
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK)));  // Black font color
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        Vehicle vehicle = reservation.getVehicle();
        double pricePerDay = vehicle.getPrice();
        long numberOfDays = calculateNumberOfDays(reservation.getStartDate(), reservation.getEndDate());

        table.addCell(vehicle.getModel());
        table.addCell(String.valueOf(numberOfDays));
        table.addCell("$" + pricePerDay);
    }

    private void addCheckbox(PdfPTable table, String label) {
        PdfPCell checkboxCell = new PdfPCell();
        checkboxCell.setBorder(PdfPCell.NO_BORDER);
        PdfPCell labelCell = new PdfPCell(new Phrase(label));
        labelCell.setBorder(PdfPCell.NO_BORDER);

        PdfPTable innerTable = new PdfPTable(new float[]{1, 4});
        innerTable.setWidthPercentage(100);

        PdfPCell cbCell = new PdfPCell();
        cbCell.setBorder(PdfPCell.NO_BORDER);
        cbCell.addElement(new Chunk("\u25A1", FontFactory.getFont(FontFactory.HELVETICA, 12)));  // Unicode for empty checkbox

        innerTable.addCell(cbCell);
        innerTable.addCell(labelCell);

        checkboxCell.addElement(innerTable);
        table.addCell(checkboxCell);
    }

    public void closeDocument() {
        document.close();
    }

    private long calculateNumberOfDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return java.time.temporal.ChronoUnit.DAYS.between(start, end);
    }

}
