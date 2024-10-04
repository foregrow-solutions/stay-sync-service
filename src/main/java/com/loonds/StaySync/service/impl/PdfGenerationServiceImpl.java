package com.loonds.StaySync.service.impl;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.loonds.StaySync.model.dto.OrderDto;
import com.loonds.StaySync.service.ChannelService;
import com.loonds.StaySync.service.OrderService;
import com.loonds.StaySync.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfGenerationServiceImpl implements PdfGenerationService {
    private final OrderService orderService;
    private final ChannelService channelService;

    @Override
    public byte[] generateBill(String channelId, String guestId) {


        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {
            addHeader(document, "TAX INVOICE");

            // Dummy data
            String invoiceNumber = "2";
            String invoiceDate = "07/05/2023";
            String customerName = "Suresh";
            String customerAddress = "snvks;ovjs;ofm;slmv;sodfslmdf";
            String contactNumber = "90734028903";

            // Add content to the PDF document
            document.add(new Paragraph("GSTIN: 02ADPPA0635F1Z7"));
            document.add(new Paragraph("Invoice No.: " + invoiceNumber));
            document.add(new Paragraph("Date: " + invoiceDate));

            // Bill To
            document.add(new Paragraph("Bill To:"));
            document.add(new Paragraph(customerName));
            document.add(new Paragraph(customerAddress));
            document.add(new Paragraph("Contact No.: " + contactNumber));

            // Table for items
            Table table = new Table(5);
            table.addCell("#");
            table.addCell("Item name");
            table.addCell("Quantity");
            table.addCell("GST");
            table.addCell("Amount");

            List<OrderDto> guestAllOrder = orderService.getGuestAllOrder(guestId);

            int itemCount = 1;
//            for (OrderDto order : guestAllOrder) {
//                table.addCell(order.getName());
//                table.addCell(order.getOrderDate().toString());
//                // Assuming you have a list of OrderItemDto inside the OrderDto
//                for (OrderItemDto orderItem : order.getOrderItems()) {
//                    table.addCell(String.valueOf(itemCount++));
//                    table.addCell(orderItem.getItemName());
//                    // Add other item details here
//                }
//            }

            document.add(table);

            // Total and Amount in Words
            document.add(new Paragraph("Total"));
            document.add(new Paragraph("Invoice Amount In Words\nThree Hundred Twenty Five Rupees only"));

            // Terms and Conditions
            document.add(new Paragraph("Terms And Conditions\nThanks for doing business with us!"));

            // Subtotal, GST, Round off, and Total
            document.add(new Paragraph("Sub Total ₹ 290.00"));
            document.add(new Paragraph("Total ₹ 325.00"));

            // Received and Balance
            document.add(new Paragraph("Received ₹ 0.00"));
            document.add(new Paragraph("Balance ₹ 325.00"));

            // Authorized Signatory
            document.add(new Paragraph("Authorized Signatory"));

            // Close the document
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            // Handle exception
            log.error("Error generating bill for guestId: {}", guestId, e);
            return null;
        }
    }

    void addHeader(Document document, String title) throws MalformedURLException {
        Table headerTable = new Table(new float[]{1, 1}).setWidth(UnitValue.createPercentValue(100));
        Paragraph titleParagraph = new Paragraph(title).setFontSize(38).setBold().setTextAlignment(TextAlignment.LEFT);
        Paragraph companyName = new Paragraph("Gayatri Lodge Kasauli").setBold();
        Paragraph companyAddress = new Paragraph("Village Kimughat P.O. Garkhal, Tehsil Kasauli, Distt-Solan, (H.P.) 173201");
        Cell companyCell = new Cell().add(titleParagraph).add(companyName).add(companyAddress).setBorder(Border.NO_BORDER);
        headerTable.addCell(companyCell);

        Image logoImage = null;

//        try {
//            logoImage = new Image(ImageDataFactory.create(LOGO_URL)).setWidth(150).setHorizontalAlignment(HorizontalAlignment.RIGHT);
//        } catch (MalformedURLException e) {
//            System.out.println("Invalid logo URL: " + LOGO_URL);
//        }
//
//        if (logoImage != null) {
//            Cell logoCell = new Cell().add(logoImage).setBorder(Border.NO_BORDER)
//                    .setVerticalAlignment(VerticalAlignment.TOP)
//                    .setMarginBottom(20);
//            headerTable.addCell(logoCell);
//        }

        document.add(headerTable);
    }

}
