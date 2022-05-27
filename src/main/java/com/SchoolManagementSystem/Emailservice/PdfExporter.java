package com.SchoolManagementSystem.Emailservice;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.SchoolManagementSystem.Entity.Studentmarks;

import com.lowagie.text.*;
import com.lowagie.text.DocumentException;

import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfWriter;

public class PdfExporter {
private List<Studentmarks> listMarks;


public PdfExporter(List<Studentmarks> listMarks) {
	this.listMarks=listMarks;
}
public void writeTableHeader(PdfPTable table) {
	
	PdfPCell cell=new PdfPCell();
	cell.setBackgroundColor(Color.LIGHT_GRAY);
	cell.setPadding(5);
	cell.setPaddingRight(10);
	
	Font font=FontFactory.getFont(FontFactory.HELVETICA);
	font.setColor(Color.WHITE);
	cell.setPhrase(new Phrase("EnrollmentId"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Mathematics"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Html"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Java"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Python"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Total"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Average"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Status"));
	table.addCell(cell);

}
public void writeTableData(PdfPTable table) {
	for(Studentmarks marks:listMarks) {
		table.addCell(String.valueOf(marks.getEnrollmentNo()));
		table.addCell(String.valueOf(marks.getMathematics()));
		table.addCell(String.valueOf(marks.getHtml()));
		table.addCell(String.valueOf(marks.getJava()));
		table.addCell(String.valueOf(marks.getPython()));
		table.addCell(String.valueOf(marks.getTotal()));
		table.addCell(String.valueOf(marks.getAvg()));
		table.addCell(String.valueOf(marks.getStatus()));
		
	//	table.addCell(String.valueOf(marks.getStatus()));
	}
}

public void export(HttpServletResponse response) throws DocumentException, IOException   {
	Document document=new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	
	Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	font.setColor(Color.GREEN);
	font.setSize(20);
	Paragraph title=new Paragraph("List of all Student Results",font);
	title.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(title);
	//document.add(new Paragraph("List of all Students"));
	PdfPTable table=new PdfPTable(8);
	//table.setWidthPercentage(100);
	table.setSpacingBefore(20);
	//table.setHeaderRows(6);
	//table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,1.5f,3.0f,3.0f});
	writeTableHeader(table);
	writeTableData(table);

	document.add(table);
	document.close();
}
}
