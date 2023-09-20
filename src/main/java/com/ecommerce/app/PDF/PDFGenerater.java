package com.ecommerce.app.PDF;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.app.Entity.Order;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PDFGenerater {
	
	private List<Order> orderList;
	
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}



	public void generate(HttpServletResponse response) throws DocumentException, IOException {
		
		
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("List Of Orders", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(5);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3, 3, 3 });
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PRODUCT_ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("QUANTITY", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ORDER_DATE", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ORDER_STATUS", font));
		table.addCell(cell);
		
		for(Order order:orderList) {
			table.addCell(String.valueOf(order.getId()));
			table.addCell(String.valueOf(order.getProductid()));
			table.addCell(String.valueOf(order.getQuantity()));
			table.addCell(String.valueOf(order.getOrderDate()));
			table.addCell(String.valueOf(order.getOrderStatus()));
		}
		
		
		// Creating a table of 3 columns
				PdfPTable table2 = new PdfPTable(5);

				// Setting width of table, its columns and spacing
				table2.setWidthPercentage(100f);
				table2.setWidths(new int[] { 3, 3, 3, 3, 3 });
				table2.setSpacingBefore(5);

				// Create Table Cells for table header
				PdfPCell cell2 = new PdfPCell();

				// Setting the background color and padding
				cell2.setBackgroundColor(CMYKColor.MAGENTA);
				cell2.setPadding(5);

				// Creating font
				// Setting font style and size
				Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				font2.setColor(CMYKColor.WHITE);

				// Adding headings in the created table cell/ header
				// Adding Cell to table
				cell2.setPhrase(new Phrase("ID", font));
				table2.addCell(cell);
				cell2.setPhrase(new Phrase("PRODUCT_ID", font));
				table2.addCell(cell);
				cell2.setPhrase(new Phrase("QUANTITY", font));
				table2.addCell(cell);
				cell2.setPhrase(new Phrase("ORDER_DATE", font));
				table2.addCell(cell);
				cell2.setPhrase(new Phrase("ORDER_STATUS", font));
				table2.addCell(cell);
				
				for(Order order:orderList) {
					table2.addCell(String.valueOf(order.getId()));
					table2.addCell(String.valueOf(order.getProductid()));
					table2.addCell(String.valueOf(order.getQuantity()));
					table2.addCell(String.valueOf(order.getOrderDate()));
					table2.addCell(String.valueOf(order.getOrderStatus()));
				}
		document.add(table);
		document.add(table2);
		document.close();
	}

}
