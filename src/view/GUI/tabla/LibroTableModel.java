package view.GUI.tabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.LibroModel;

public class LibroTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6168854443666812292L;

	private List<LibroModel> libros;
	private String[] nombreColumna = ColumanLibroTabla.getNames();
	private Class<?>[] nombreClasesColumna = LibroModel.CLASES_PROPIEDADES;

	public LibroTableModel() {
		super();
	}
	public LibroTableModel(List<LibroModel> libros) {
		this();
		this.libros = libros;
	}

	@Override
	public int getRowCount() {
		return libros.size();
	}

	@Override
	public int getColumnCount() {
		return LibroModel.CLASES_PROPIEDADES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LibroModel libro = this.libros.get(rowIndex);
		ColumanLibroTabla columna = ColumanLibroTabla.fromIndex(columnIndex);
		switch(columna){
		case ISBN:
			return libro.getISBN();
		case TITULO:
			return libro.getTitulo();
		case AUTOR:
			return libro.getAutor();
		case EDITORIAL:
			return libro.getEditorial();
		case EDICION:
			return libro.getEdicion();
		case ANIO:
			return libro.getAnno_de_publicacion();
		default:
			return null;
		}
	}
	
    public String getColumnName(int col) {
        return nombreColumna[col];
    }
    public Class<?> getColumnClass(int col) {
        return nombreClasesColumna[col];
    }

}
