package view.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.Gestor;
import model.LibroModel;
import view.View;
import view.GUI.tabla.LibroTableModel;

public class GUIListaLibrosView extends JFrame implements View {

	private static final long serialVersionUID = -7813678266896518237L;

	private Component parentComponent;
	private Rectangle defaultBounds;
	private JPanel contentPane;
	private JTable tabla;
	
	private Gestor gestor;
	
	public GUIListaLibrosView(Component parentComponent) {
		super();
		setTitle("Lista de Libros");
		this.parentComponent = parentComponent;
		this.defaultBounds = this.parentComponent.getBounds();
		setBounds(this.defaultBounds);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parentComponent.setVisible(true);
			}
		});
		
		this.contentPane = new JPanel();
		this.contentPane.setLayout(null);
		setContentPane(this.contentPane);
		
		LibroTableModel modeloTabla = new LibroTableModel();
		this.tabla = new JTable(modeloTabla);
		JScrollPane tablaScrolleable = new JScrollPane(this.tabla);
        tablaScrolleable.setPreferredSize(new Dimension(450, 230));
        tablaScrolleable.setBounds(10, 10, 450, 230);
	    this.contentPane.add(tablaScrolleable, BorderLayout.CENTER);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(360, 250, 100, 25);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		this.contentPane.add(btnVolver);
	}

	private void volver() {
		this.dispose();
		this.parentComponent.setVisible(true);
	}

	@Override
	public void mostrarView() {
		Vector<LibroModel> libros = this.gestor.getBibliotecaModel().getLibros();
		LibroTableModel modeloTabla = new LibroTableModel(libros);
		this.tabla.setModel(modeloTabla);
		
		this.setVisible(true);
		this.parentComponent.setVisible(false);
	}

	@Override
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
}
