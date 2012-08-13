package cl.tival.check.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import cl.tival.check.model.Factura;

public class FacturaTableModel implements TableModel {

	private LinkedList<Factura> data = new LinkedList<Factura>();

	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

	private String[] columnNames = new String[] { "Nº FACTURA", "FECHA", "MONTO", "ESTADO", ""};

	private Class[] columnClass = new Class[] { Long.class, Date.class, Long.class, String.class, Boolean.class };

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Factura object = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return object.getNumero();
		case 1:
			return object.getFecha();
		case 2:
			return object.getMonto();
		case 3:
			return object.getEstado();
		case 4:
			return object.getSeleccion();
		default:
			return null;
		}
	}

	public Factura getObject(int rowIndex) {
		return data.get(rowIndex);
	}

	public void removeObject(int rowIndex) {
		data.remove(rowIndex);
		TableModelEvent event = new TableModelEvent(this, rowIndex, rowIndex,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		avisaSuscriptores(event);
	}

	public void addObject(Factura object) {
		data.add(object);
		TableModelEvent event = new TableModelEvent(this, this.getRowCount() - 1, 
				this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		avisaSuscriptores(event);
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int columnIndex) {
		if (columnIndex < this.getColumnCount()) {
			return columnClass[columnIndex];
		}
		return Object.class;
	}

	public String getColumnName(int columnIndex) {
		if (columnIndex < this.getColumnCount()) {
			return columnNames[columnIndex];
		}
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex==4)
			return true;
		return false;
	}

	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Factura object = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			object.setSeleccion((Boolean)aValue);
			break;
		default:
			break;
		}
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
				columnIndex);
		avisaSuscriptores(evento);
	}

	private void avisaSuscriptores(TableModelEvent event) {
		for (int i = 0; i < listeners.size(); i++)
			((TableModelListener) listeners.get(i)).tableChanged(event);
	}

	public Object[] getSelectedIndexRow() {
		List<Integer> list = new ArrayList<Integer>();
		int size = data.size();
		for (int i=0; i<size; i++) {
			if(((Factura)data.get(i)).getSeleccion()){
				list.add(i);
			}
		}
		return list.toArray();
	}
	
}