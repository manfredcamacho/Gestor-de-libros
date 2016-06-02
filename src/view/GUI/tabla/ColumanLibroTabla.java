package view.GUI.tabla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ColumanLibroTabla {
    
	ISBN(0, "ISBN"), 
	TITULO(1, "T\u00edtulo"), 
	AUTOR(2, "Autor"), 
	EDITORIAL(3, "Editorial"), 
	EDICION(4, "Edici\u00f3n"), 
	ANIO(5, "A\u00f1o");

    private int indice;
    private String nombreColumna;

    private ColumanLibroTabla(int index, String name) {
    	this.indice = index;
    	this.nombreColumna = name;
    }
    
    private static final Map<Integer, ColumanLibroTabla> MAPA_INDICE_COLUMNA = new HashMap<>();
    private static final List<String> NOMBRES = new ArrayList<>();
    
    static {
        for (ColumanLibroTabla c : ColumanLibroTabla.values()) {
            MAPA_INDICE_COLUMNA.put(c.indice, c);
            NOMBRES.add(c.nombreColumna);
        }
    }
 
    public static ColumanLibroTabla fromIndex(int colIndex) {
    	ColumanLibroTabla columnName = MAPA_INDICE_COLUMNA.get(colIndex);
        return (columnName != null) ? columnName : null;
    }
 
    public static String[] getNames() {
        return NOMBRES.toArray(new String[NOMBRES.size()]);
    }

}
