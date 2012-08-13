package cl.tival.check.printer;

import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRSaver;

public class Printer {

	private String PRINTER_NAME;// = "Generic-CUPS-PDF-Printer";

	private String OUTPUT_FILE_NAME= "Check.jrprint";
	//private static String PRINTER_NAME = "Canon-MP280-series";
	public static String NUMERO_DOCUMENTO = "NUMERO_DOCUMENTO";
	public static String MONTO = "MONTO";
	public static String MONTO_TOTAL = "MONTO_TOTAL";
	public static String PROVEEDOR = "PROVEEDOR";
	public static String RUT = "RUT";
	public static String REF_INTERNA = "REF_INTERNA";
	public static String FECHA = "FECHA";
	public static String DIA = "DIA";
	public static String MES = "MES";
	public static String ANNO = "ANNO";
	public static String PAGUESE_A = "PAGUESE_A";
	public static String LA_SUMA_DE_NUMBER = "LA_SUMA_DE_NUMBER";
	public static String LA_SUMA_DE_STRING_L1 = "LA_SUMA_DE_STRING_L1";
	public static String LA_SUMA_DE_STRING_L2 = "LA_SUMA_DE_STRING_L2";

//	public static void main(String[] args) {
//		try {
//			
//			Map<String, Object> data = new HashMap<String, Object>();
//			data.put("RUT", "15.097.399-6");
//			
//			fill("src/main/resources/CheckBancoChile.jrxml", data);
//			print();
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void printCheck(String reportSourceName, Map<String, Object> data) {
		try {
			fill(reportSourceName, data);
			print();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void fill(String reportSourceName, Map<String, Object> data) throws JRException {
		JasperPrint jasperPrint = getJasperPrint(reportSourceName, data);
		JRSaver.saveObject(jasperPrint, OUTPUT_FILE_NAME);
	}

	private void print() throws JRException {

		//Printer setup
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
		//PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		//printServiceAttributeSet.add(new PrinterName(PRINTER_NAME, null));

		//Config exporter with printer
		JRPrintServiceExporter exporter = new JRPrintServiceExporter();
		exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, OUTPUT_FILE_NAME);
		exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
		//exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

		//Export to printer
		exporter.exportReport();
	}

	private JasperPrint getJasperPrint(String reportSourceName, Map<String, Object> data) throws JRException {

		JasperReport jasperReport = JasperCompileManager.compileReport(reportSourceName);
		JasperPrint jasperPrint = (JasperPrint) JasperFillManager.fillReport(jasperReport, data);
		
		
		// exporta la impresion a PDF
//		JRExporter exporter = new JRPdfExporter();
//		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "Check.pdf");
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.exportReport();
		return jasperPrint;
	}

	public void setPrinterName(String printerName) {
		PRINTER_NAME = printerName;
	}

}
