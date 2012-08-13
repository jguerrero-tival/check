package cl.tival.check.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.tival.check.app.CheckConstants;
import cl.tival.check.dao.FacturaDao;
import cl.tival.check.model.Empresa;
import cl.tival.check.model.Factura;
import cl.tival.check.printer.Printer;
import cl.tival.check.service.PrinterService;
import cl.tival.check.util.Fecha;
import cl.tival.check.util.Format;
import cl.tival.check.util.NumberToLetter;

public class PrinterServiceImpl implements PrinterService {

	private Printer printer;
	private FacturaDao facturaDao;
	private NumberToLetter numberToLetter;
	private String reportSourceName;

	@Override
	public void printFactura(List<Long> numerosFactura, Date fecha, Long montoTotalFraccion, String fraccion) {
		List<Factura> facturas = new ArrayList<Factura>();
		for(Long numeroFactura : numerosFactura) {
			Factura factura = facturaDao.getFactura(numeroFactura);
			facturas.add(factura);
		}
		Map<String, Object> data = generateCheckData(facturas, fecha, montoTotalFraccion, fraccion);
		printer.printCheck(reportSourceName, data);
	}
	
	private Map<String, Object> generateCheckData(List<Factura> facturas, Date fecha, Long montoTotalFraccion, String fraccion) {
		Empresa empresa= facturas.get(0).getEmpresa();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Printer.NUMERO_DOCUMENTO, getNumeroDocumento(facturas, fraccion));
		data.put(Printer.MONTO, getMonto(facturas));
		Long montoTotal = getMontoTotal(facturas);
		data.put(Printer.MONTO_TOTAL, Format.stringNumberToLong(montoTotal.toString()));
		data.put(Printer.PROVEEDOR, empresa.getRazonSocial());
		data.put(Printer.RUT, empresa.getRut());
		data.put(Printer.REF_INTERNA, "");
		data.put(Printer.FECHA, Format.dateToString(fecha));
		data.put(Printer.DIA, Fecha.getDay(fecha));
		data.put(Printer.MES, Fecha.mesPalabra(Fecha.getMonth(fecha).intValue()));
		data.put(Printer.ANNO, Fecha.getYear(fecha));
		data.put(Printer.PAGUESE_A, empresa.getRazonSocialRepresentante());
		data.put(Printer.LA_SUMA_DE_NUMBER, Format.stringNumberToLong(montoTotalFraccion.toString()).concat("---------------------------------------------------------------------------------------------------"));
		data.put(Printer.LA_SUMA_DE_STRING_L1, numberToLetter.Convertir(montoTotalFraccion.toString(), true).concat("----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- "));
		data.put(Printer.LA_SUMA_DE_STRING_L2, "".concat("----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- "));
		return data;
	}
	
	@Override
	public void printFactura(List<Long> numerosFactura) {
		List<Factura> facturas = new ArrayList<Factura>();
		for(Long numeroFactura : numerosFactura) {
			Factura factura = facturaDao.getFactura(numeroFactura);
			facturas.add(factura);
		}
		Map<String, Object> data = generateCheckData(facturas);
		printer.printCheck(reportSourceName, data);
	}
	
	private Map<String, Object> generateCheckData(List<Factura> facturas) {
		Empresa empresa= facturas.get(0).getEmpresa();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Printer.NUMERO_DOCUMENTO, getNumeroDocumento(facturas));
		data.put(Printer.MONTO, getMonto(facturas));
		Long montoTotal = getMontoTotal(facturas);
		data.put(Printer.MONTO_TOTAL, montoTotal);
		data.put(Printer.PROVEEDOR, empresa.getRazonSocial());
		data.put(Printer.RUT, empresa.getRut());
		data.put(Printer.REF_INTERNA, "");
		Date fechaHoy = new Date();
		data.put(Printer.FECHA, Format.dateToString(fechaHoy));
		data.put(Printer.DIA, Fecha.getDay(fechaHoy));
		data.put(Printer.MES, Fecha.mesPalabra(Fecha.getMonth(fechaHoy).intValue()));
		data.put(Printer.ANNO, Fecha.getYear(fechaHoy));
		data.put(Printer.PAGUESE_A, empresa.getRazonSocialRepresentante());
		data.put(Printer.LA_SUMA_DE_NUMBER, montoTotal.toString().concat("---------------------------------------------------------------------------------------------------"));
		data.put(Printer.LA_SUMA_DE_STRING_L1, numberToLetter.Convertir(montoTotal.toString(), true).concat("----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- "));
		data.put(Printer.LA_SUMA_DE_STRING_L2, "".concat("----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- "));
		return data;
	}
	
	private String getNumeroDocumento(List<Factura> facturas) {
		String numeroDocumentos = "";
		for(Factura factura : facturas) {
			System.out.println(factura.getNumero() + "---" + factura.getNumero().toString());
			numeroDocumentos = numeroDocumentos + factura.getNumero().toString() + "\n";
		}
		System.out.println(numeroDocumentos);
		return numeroDocumentos;
	}
	
	private String getNumeroDocumento(List<Factura> facturas, String fraccion) {
		String numeroDocumentos = "";
		for(Factura factura : facturas) {
			System.out.println(Format.stringNumberToLong(factura.getNumero().toString()) + "---" + factura.getNumero().toString());
			numeroDocumentos = numeroDocumentos + Format.stringNumberToLong(factura.getNumero().toString()) + "   " + fraccion + "\n";
		}
		System.out.println(numeroDocumentos);
		return numeroDocumentos;
	}
	
	private String getMonto(List<Factura> facturas) {
		String monto = "";
		for(Factura factura : facturas) {
			System.out.println(Format.stringNumberToLong(factura.getMonto().toString()) + "---" + factura.getMonto().toString());
			monto = monto + Format.stringNumberToLong(factura.getMonto().toString()) + "\n";
			//monto.concat("\n").concat(factura.getMonto().toString());
		}
		System.out.println(monto);
		return monto;
	}
	
	private Long getMontoTotal(List<Factura> facturas) {
		Long montoTotal = 0L;
		for(Factura factura : facturas) {
			montoTotal = montoTotal + factura.getMonto();
		}
		return montoTotal;
	}

	@Override
	public void marcarFacturaimpresa(List<Long> numerosFactura) {
		for(Long numeroFactura : numerosFactura) {
			Factura factura = facturaDao.getFactura(numeroFactura);
			factura.setEstado(CheckConstants.ESTADO_FACTURA_IMPRESA);
			facturaDao.saveFactura(factura);			
		}
	}
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public void setFacturaDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}

	public void setReportSourceName(String reportSourceName) {
		this.reportSourceName = reportSourceName;
	}

	public void setNumberToLetter(NumberToLetter numberToLetter) {
		this.numberToLetter = numberToLetter;
	}

}
