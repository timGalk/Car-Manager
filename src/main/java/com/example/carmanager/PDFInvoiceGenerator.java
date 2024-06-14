package com.example.carmanager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.List;

public class PDFInvoiceGenerator {
    private List<Reservation> reservations;
    private final String InvoiceTitle = "Vehicle Rental";
    private final String SubTitle = "Invoice";

    public PDFInvoiceGenerator(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    // Method to write the invoice to a PDF file
    public void WriteInvoice() {
        PDDocument invc = new PDDocument(); // Create a new PDF document
        PDPage mypage = new PDPage(); // Create a new page for the PDF document
        invc.addPage(mypage);

        try {
            // Create a content stream to write content to the page
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);

            // Write the title of the invoice
            cs.beginText();
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 20);
            cs.newLineAtOffset(140, 750);
            cs.showText(InvoiceTitle);
            cs.endText();

            // Write the subtitle of the invoice
            cs.beginText();
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 18);
            cs.newLineAtOffset(270, 690);
            cs.showText(SubTitle);
            cs.endText();

            float y = 640; // Starting position for the content
            int totalInvoicePrice = 0;

            String customerName = ""; // Variable to hold the customer's name for the filename

            // Loop through each reservation to add its details to the invoice
            for (Reservation reservation : reservations) {
                Customer customer = reservation.getCustomer();
                Vehicle vehicle = reservation.getVehicle();
                double totalPrice = reservation.calculateTotalPrice();
                totalInvoicePrice += totalPrice;

                // Extract the customer's name (first reservation's customer name used for the filename)
                if (customerName.isEmpty()) {
                    customerName = customer.getName() + "_" + customer.getSurname();
                }

                // Write the reservation details to the invoice
                cs.beginText();
                cs.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 14);
                cs.setLeading(20f); // Set the line spacing
                cs.newLineAtOffset(60, y);
                cs.showText("Customer Name: " + customer.getName() + " " + customer.getSurname());
                cs.newLine();
                cs.showText("Phone Number: " + customer.getPhone());
                cs.newLine();
                cs.showText("Vehicle: " + vehicle.getModel());
                cs.newLine();
                cs.showText("Start Date: " + reservation.getStartDate());
                cs.newLine();
                cs.showText("End Date: " + reservation.getEndDate());
                cs.newLine();
                cs.showText("Daily Price: $" + vehicle.getPrice());
                cs.newLine();
                cs.showText("Total Price: $" + totalPrice);
                cs.newLine();
                y -= 140; // Move the starting position down for the next reservation
                cs.endText();
            }

            // Write the total price of the invoice
            cs.beginText();
            cs.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 16);
            cs.newLineAtOffset(60, y);
            cs.showText("Total Invoice Price: $" + totalInvoicePrice);
            cs.endText();

            cs.close();

            // Generate the filename using the customer's name
            String filename = "Invoice_" + customerName.replace(" ", "_") + ".pdf";
            invc.save(filename);
            invc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}