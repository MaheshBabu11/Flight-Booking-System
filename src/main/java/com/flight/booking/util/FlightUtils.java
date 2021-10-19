package com.flight.booking.util;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.flight.booking.model.TicketDetails;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FlightUtils {

	public static String generatePnrNumber() {
		int leftLimit = 65;
		int rightLimit = 90;
		int targetStringLength = 6;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

	public static String generateTicketNumber() {
		int leftLimit = 49;
		int rightLimit = 57;
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

	public static byte[] createTicketPdf(final List<TicketDetails> ticketDetails) throws JRException {
		final InputStream stream = FlightUtils.class.getResourceAsStream("/ticketDetails.jrxml");
		final JasperReport report = JasperCompileManager.compileReport(stream);
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(ticketDetails);
		final Map<String, Object> parameters = new HashMap<>();
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
		return JasperExportManager.exportReportToPdf(print);
	}

	public static String getDayOfDate(String date, String inputFormat) {
		String day = null;
		try {
			day = date != null ? new SimpleDateFormat("EEEE").format(new SimpleDateFormat(inputFormat).parse(date))
					: null;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("unparsable date " + date);
		}
		return day;
	}

	public static Date getFormattedDate(String date, String inputFormat) {
		Date formattedDate = null;
		try {
			formattedDate = date != null ? new SimpleDateFormat(inputFormat).parse(date) : null;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("unparsable date " + date);
		}
		return formattedDate;
	}

	public static String getFormattedDate(Date date, String inputFormat) {
		String formattedDate = date != null ? new SimpleDateFormat(inputFormat).format(date) : null;
		return formattedDate;
	}

}
