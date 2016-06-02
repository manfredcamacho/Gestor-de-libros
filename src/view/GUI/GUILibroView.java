package view.GUI;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controller.Gestor;
import model.LibroModel;
import view.View;

public class GUILibroView extends JFrame implements View {

	private static final long serialVersionUID = 3877840112281408794L;
	
	public enum OperacionLibro {
		Alta, Consulta, Actualizar;
	}
	
	private Component parentComponent;
	private Rectangle defaultBounds;
	private JPanel contentPane;
	
	private Gestor gestor;
	private LibroModel libro;
	
	private JTextArea txtISBN;
	private JTextArea txtTitulo;
	private JTextArea txtAutor;
	private JTextArea txtEditorial;
	private JTextArea txtEdicion;
	private JTextArea txtAnioPublicacion;
	
	private JButton btnGuardar;
	private OperacionLibro operacion;
	
	public GUILibroView(Component parentComponent) {
		super();
		setTitle("Detalle de Libro");
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
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(80, 10, 120, 25);
		lblISBN.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblISBN);
		
		this.txtISBN = new JTextArea();
		this.txtISBN.setBounds(230, 10, 120, 25);
		this.contentPane.add(this.txtISBN);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(80, 50, 120, 25);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblTitulo);
		
		this.txtTitulo = new JTextArea();
		this.txtTitulo.setBounds(230, 50, 120, 25);
		this.contentPane.add(this.txtTitulo);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(80, 90, 120, 25);
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblAutor);
		
		this.txtAutor = new JTextArea();
		this.txtAutor.setBounds(230, 90, 120, 25);
		this.contentPane.add(this.txtAutor);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(80, 130, 120, 25);
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblEditorial);
		
		this.txtEditorial = new JTextArea();
		this.txtEditorial.setBounds(230, 130, 120, 25);
		this.contentPane.add(this.txtEditorial);

		JLabel lblEdicion = new JLabel("Edicion");
		lblEdicion.setBounds(80, 170, 120, 25);
		lblEdicion.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblEdicion);
		
		this.txtEdicion = new JTextArea();
		this.txtEdicion.setBounds(230, 170, 120, 25);
		this.contentPane.add(this.txtEdicion);

		JLabel lblAnioPublicacion = new JLabel("Año Publicacion");
		lblAnioPublicacion.setBounds(80, 210, 120, 25);
		lblAnioPublicacion.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(lblAnioPublicacion);
		
		this.txtAnioPublicacion = new JTextArea();
		this.txtAnioPublicacion.setBounds(230, 210, 120, 25);
		this.contentPane.add(this.txtAnioPublicacion);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(360, 250, 100, 25);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		this.contentPane.add(btnVolver);
		
		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.setBounds(250, 250, 100, 25);
		this.btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarLibro();
			}
		});
		this.contentPane.add(this.btnGuardar);
	}
	
	public void setOperacion( OperacionLibro operacion ){
		this.operacion = operacion;
		Boolean altaOModificacion = !(operacion.equals(OperacionLibro.Consulta));
		txtISBN.setEditable(operacion.equals(OperacionLibro.Alta));
		txtTitulo.setEditable(altaOModificacion);
		txtAutor.setEditable(altaOModificacion);
		txtEditorial.setEditable(altaOModificacion);
		txtEdicion.setEditable(altaOModificacion);
		txtAnioPublicacion.setEditable(altaOModificacion);
		this.btnGuardar.setVisible(altaOModificacion);
	}
	
	public void setLibro(LibroModel libro){
		this.libro = libro;
		if(!( this.operacion.equals(OperacionLibro.Alta) )){
			txtISBN.setText(libro.getISBN());
			txtTitulo.setText(libro.getTitulo());
			txtAutor.setText(libro.getAutor());
			txtEditorial.setText(libro.getEditorial());
			txtEdicion.setText(String.valueOf(libro.getEdicion()));
			txtAnioPublicacion.setText(String.valueOf(libro.getAnno_de_publicacion()));
		}
	}
	
	private void guardarLibro(){
		/*
		 * Valido que los campos del alta del libro estan completados
		 */
		if( this.txtISBN.getText().isEmpty() || this.txtTitulo.getText().isEmpty()
			|| this.txtAutor.getText().isEmpty() || this.txtEditorial.getText().isEmpty()
			|| this.txtEdicion.getText().isEmpty() || this.txtAnioPublicacion.getText().isEmpty() ){
			
			JOptionPane.showMessageDialog( 
					this, "¡Todos los campos son mandatorios!", 
					"Error", JOptionPane.ERROR_MESSAGE
				);
		} else {
			/*
			 * si los datos estan completos se carga el libro
			 */
			this.libro.setISBN(this.txtISBN.getText());
			this.libro.setTitulo(this.txtTitulo.getText());
			this.libro.setAutor(this.txtAutor.getText());
			this.libro.setEditorial(this.txtEditorial.getText());
			try{
				this.libro.setEdicion(Integer.valueOf(this.txtEdicion.getText()));
				this.libro.setAnno_de_publicacion(Integer.valueOf(this.txtAnioPublicacion.getText()));
				
				if(this.operacion.equals(OperacionLibro.Alta)){
					this.gestor.getBibliotecaModel().agregarLibro(this.libro);
				} else {
					this.gestor.getBibliotecaModel().actualizarLibro(this.libro);
				}
				this.volver();
			} catch (NumberFormatException e){
				/*
				 * En caso de no ingresar un entero en edicion o a�o
				 * se lanza un excepcion mostrando un mensaje.
				 */
				JOptionPane.showMessageDialog( 
						this, "Debe ingresar valores numéricos para campos Número de Edición y Año", 
						"Error", JOptionPane.ERROR_MESSAGE
					);
			}
		}
		
	}

	private void volver() {
		/*
		 * Hace desaparecer la ventana actual y hace visble la
		 * ventana anterior.
		 */
		this.dispose();
		this.parentComponent.setVisible(true);
	}

	@Override
	public void mostrarView() {
		this.setVisible(true);
		this.parentComponent.setVisible(false);
	}

	@Override
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	
}
