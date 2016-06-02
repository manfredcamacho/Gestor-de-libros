package view.GUI;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Gestor;
import model.UsuarioModel;
import view.View;

public class GUILoginView extends JFrame implements View {

	private static final long serialVersionUID = 3877840112281408794L;
	
	private Rectangle defaultBounds;
	private JPanel contentPane;
	
	private Gestor gestor;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JTextField txtNuevoUsuario;
	private JPasswordField txtNuevaClave;
	private JPasswordField txtConfirmacionNuevaClave;
	private JTextField txtPreguntaSecreta;
	private JTextField txtRespuestaSecreta;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUILoginView() {
		super();
		setTitle("Gestor de Libros");
		this.defaultBounds = new Rectangle(100, 100, 480, 320);
		setBounds(this.defaultBounds);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.contentPane = new JPanel();
		this.contentPane.setLayout(null);
		setContentPane(this.contentPane);
		
		JButton btnIngresar = new JButton("Aceptar");
		btnIngresar.setBounds(40, 180, 120, 25);
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ingresarUsuario();
			}
		});
		contentPane.add(btnIngresar);
		
		JButton btnNuevoUsuario = new JButton("Crear");
		btnNuevoUsuario.setBounds(275, 256, 120, 25);
		btnNuevoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grabarNuevoUsuario();
			}
		});
		contentPane.add(btnNuevoUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(40, 76, 120, 25);
		contentPane.add(txtUsuario);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(40, 128, 120, 25);
		contentPane.add(txtClave);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(40, 59, 70, 15);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(40, 113, 70, 15);
		contentPane.add(lblClave);
		
		JLabel lblOlvidoClave = new JLabel("¿Olvidó su clave?");
		lblOlvidoClave.setBounds(40, 249, 120, 15);
		Font font = lblOlvidoClave.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblOlvidoClave.setFont(font.deriveFont(attributes));
		lblOlvidoClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				olvidoClave();
			}
		});
		contentPane.add(lblOlvidoClave);
		
		JLabel lblIngresar = new JLabel("Ingreso");
		lblIngresar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresar.setBounds(40, 12, 120, 15);
		contentPane.add(lblIngresar);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(275, 39, 120, 15);
		contentPane.add(lblNombreUsuario);
		
		txtNuevoUsuario = new JTextField();
		txtNuevoUsuario.setBounds(275, 56, 120, 25);
		contentPane.add(txtNuevoUsuario);
		
		JLabel lblNuevaClave = new JLabel("Clave");
		lblNuevaClave.setBounds(275, 80, 70, 15);
		contentPane.add(lblNuevaClave);
		
		txtNuevaClave = new JPasswordField();
		txtNuevaClave.setBounds(275, 95, 120, 25);
		contentPane.add(txtNuevaClave);
		
		JLabel lblnuevoUsuario = new JLabel("¿Nuevo usuario?");
		lblnuevoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblnuevoUsuario.setBounds(275, 12, 120, 15);
		contentPane.add(lblnuevoUsuario);
		
		JLabel lblConfirmarClave = new JLabel("Confirmar Clave");
		lblConfirmarClave.setBounds(275, 121, 111, 15);
		contentPane.add(lblConfirmarClave);
		
		txtConfirmacionNuevaClave = new JPasswordField();
		txtConfirmacionNuevaClave.setBounds(275, 135, 120, 25);
		contentPane.add(txtConfirmacionNuevaClave);
		
		JLabel lblPreguntaSecreta = new JLabel("Pregunta secreta");
		lblPreguntaSecreta.setBounds(275, 160, 150, 15);
		contentPane.add(lblPreguntaSecreta);
		
		txtPreguntaSecreta = new JTextField();
		txtPreguntaSecreta.setColumns(10);
		txtPreguntaSecreta.setBounds(275, 175, 120, 25);
		contentPane.add(txtPreguntaSecreta);
		
		JLabel lblRespuestaSecreta = new JLabel("Respuesta");
		lblRespuestaSecreta.setBounds(275, 204, 150, 15);
		contentPane.add(lblRespuestaSecreta);
		
		txtRespuestaSecreta = new JTextField();
		txtRespuestaSecreta.setColumns(10);
		txtRespuestaSecreta.setBounds(275, 219, 120, 25);
		contentPane.add(txtRespuestaSecreta);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(219, 12, 41, 264);
		contentPane.add(separator);
	}
	
	protected void ingresarUsuario() {
		String nombreUsuario = this.txtUsuario.getText().trim();
		String clave = new String(this.txtClave.getPassword()).trim();
		
		if( nombreUsuario.isEmpty() || clave.isEmpty() ){
			JOptionPane.showMessageDialog( 
					this, "Debe ingresar usuario y clave", "Error", JOptionPane.ERROR_MESSAGE
				);
			
		} else {
			UsuarioModel usuario = this.gestor.getUsuariosModel()
					.getUsuario( new UsuarioModel( nombreUsuario ) );
			
			if(usuario!=null && usuario.getClave().equals(clave) ){
				
				this.mostrarMain();
				
			} else {
				this.txtClave.setText("");
				JOptionPane.showMessageDialog( 
						this, "Usuario no encontrado, o clave errónea", "Error", JOptionPane.ERROR_MESSAGE
					);
			}
		}
	}

	private void mostrarMain() {
		GUIMainView pantallaMain = new GUIMainView(this);
		pantallaMain.setGestor(this.gestor);
		pantallaMain.mostrarView();
	}

	protected void olvidoClave() {
		String nombreUsuario = this.txtUsuario.getText().trim();
		if( nombreUsuario.isEmpty() ){
			JOptionPane.showMessageDialog( 
					this, "Debe conocer su nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE
				);
		} else {
			UsuarioModel usuario = this.gestor.getUsuariosModel().getUsuario( new UsuarioModel(nombreUsuario) );
			
			if(usuario==null){
				JOptionPane.showMessageDialog( 
						this, "El usuario ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE
					);
			} else {
				String respuesta = JOptionPane.showInputDialog(
						this, "Ingrese la respuesta a su pregunta '"+usuario.getPregunta()+"'"
					);
				if(respuesta!=null){
					if(respuesta.trim().equals(usuario.getRespuesta())){
						
						this.mostrarMain();
						
					} else {
						JOptionPane.showMessageDialog( 
								this, "Respuesta equivocada!", "Error", JOptionPane.ERROR_MESSAGE
							);
					}
				}
			}
		}
	}

	protected void grabarNuevoUsuario() {
		String nuevoUsuario = this.txtNuevoUsuario.getText().trim();
		String nuevaClave = new String(this.txtNuevaClave.getPassword()).trim();
		String confirmacionNuevaClave = new String(this.txtConfirmacionNuevaClave.getPassword()).trim();
		String preguntaSecreta = this.txtPreguntaSecreta.getText().trim();
		String respuesta = this.txtRespuestaSecreta.getText().trim();
		
		if( nuevoUsuario.isEmpty() || nuevaClave.isEmpty() || confirmacionNuevaClave.isEmpty() 
			|| preguntaSecreta.isEmpty() || respuesta.isEmpty() ){
			JOptionPane.showMessageDialog( 
					this, "Todos los campos son mandatorios para crear usuario!", "Error", JOptionPane.ERROR_MESSAGE
				);
		} else {
			
			UsuarioModel usuario = new UsuarioModel(nuevoUsuario, nuevaClave, preguntaSecreta, respuesta);
			
			if( this.gestor.getUsuariosModel().getUsuarios().contains(usuario)){
				
				this.txtNuevoUsuario.setText("");
				this.txtNuevaClave.setText("");
				this.txtConfirmacionNuevaClave.setText("");
				this.txtPreguntaSecreta.setText("");
				this.txtRespuestaSecreta.setText("");
				
				JOptionPane.showMessageDialog( 
						this, "El usuario ya existe! Por favor ingrese", "Error", JOptionPane.WARNING_MESSAGE
					);	
			
			} else if( nuevaClave.equals(confirmacionNuevaClave) ) {
				
				this.gestor.getUsuariosModel().agregarUsuario(usuario);
				this.mostrarMain();
				
			} else {
				this.txtNuevaClave.setText("");
				this.txtConfirmacionNuevaClave.setText("");
				
				JOptionPane.showMessageDialog( 
						this, "Las claves no coinciden", "Error", JOptionPane.ERROR_MESSAGE
					);
			}
		}
	}

	@Override
	public void mostrarView() {
		this.setVisible(true);
	}
	
	@Override
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Rectangle getDefaultBounds() {
		return defaultBounds;
	}
}
