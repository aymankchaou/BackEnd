package tn.esps.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.esps.entities.Presence;

public class ExportExcelService {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Presence> listPresence;

	public ExportExcelService(List<Presence> listPresence) {
		this.listPresence = listPresence;
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof LocalDate) {
			cell.setCellValue((LocalDate) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else {
			cell.setCellValue((Date) value);
		}
		cell.setCellStyle(style);
	}

	private void createHeaderRow() {
		sheet = workbook.createSheet("Presence Information");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(31);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row, 0, "Presence Information", style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		font.setFontHeightInPoints((short) 10);

		row = sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "Days", style);
		createCell(row, 1, "Number of hours", style);

	}

	private void writePresenceData() {
		int rowCount = 2;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Presence presence : listPresence) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, presence.getDay(), style);
			createCell(row, columnCount++, presence.getNbrheures(), style);
		}
	}

	public void exportDataToExcel(Long id, HttpServletResponse response) throws IOException {
		createHeaderRow();
		writePresenceData();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

}
