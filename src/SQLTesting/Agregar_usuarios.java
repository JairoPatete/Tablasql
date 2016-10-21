package SQLTesting;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//se incluyen las librerias del framework y las de SQL
public class Agregar_usuarios {
	//Declaracion de variables del framework y las variables de uso de SQL, Connection, Statement para envio de string a la DB y Resulset para resultados
	private JFrame frmAgregarUsuarios;
	private JTextField textusuario;
	private JTextField textpassword;
	private JTextField textpasswordrepeat;
	private Connection conn = null;
	private Statement smtm = null;
	private ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_usuarios window = new Agregar_usuarios();
					window.frmAgregarUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Agregar_usuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgregarUsuarios = new JFrame();
		frmAgregarUsuarios.setTitle("Agregar Usuarios");
		frmAgregarUsuarios.setBounds(100, 100, 450, 300);
		frmAgregarUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgregarUsuarios.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(74, 72, 46, 14);
		frmAgregarUsuarios.getContentPane().add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(66, 97, 46, 14);
		frmAgregarUsuarios.getContentPane().add(lblNewLabel);
		
		JLabel lblRepetirPassword = new JLabel("Repetir password");
		lblRepetirPassword.setBounds(29, 122, 91, 14);
		frmAgregarUsuarios.getContentPane().add(lblRepetirPassword);
		
		JLabel lbltestsql = new JLabel("test");
		lbltestsql.setBounds(183, 22, 83, 14);
		frmAgregarUsuarios.getContentPane().add(lbltestsql);
		
		
		
		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {         //Boton de agregar usuario a la base de datos
				DataBaseAccess conexion = new DataBaseAccess();  //Llamando a la clase DataBaseAccess y estableciendo conexion
				
				String usuario=textusuario.getText().trim();   //Obteniendo los valores del textfield
				String password=textpassword.getText().trim();
				String checkpassword=textpasswordrepeat.getText().trim();
				conn=conexion.getcon();
				if (conn!=null)
					lbltestsql.setText("Its connected!"); //Label temporal para saber si conecto o no.
				try {
					smtm = conn.createStatement();
					String sql = "INSERT INTO login (nombre, password)" +
			                "VALUES (?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
			        preparedStatement.setString(1, usuario);
			        preparedStatement.setString(2, password);
			        
			        preparedStatement.executeUpdate(); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnCrearUsuario.setEnabled(false);
		btnCrearUsuario.setBounds(48, 204, 108, 23);
		frmAgregarUsuarios.getContentPane().add(btnCrearUsuario);
		
		
		
		
		textusuario = new JTextField();
		textusuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (textusuario.getText()!="")
				{
					btnCrearUsuario.setEnabled(true);
				}
				else btnCrearUsuario.setEnabled(false);
			}
		});
		textusuario.setBounds(145, 69, 142, 20);
		frmAgregarUsuarios.getContentPane().add(textusuario);
		textusuario.setColumns(10);
		
		textpassword = new JTextField();
		textpassword.setBounds(145, 94, 142, 20);
		frmAgregarUsuarios.getContentPane().add(textpassword);
		textpassword.setColumns(10);
		
		textpasswordrepeat = new JTextField();
		textpasswordrepeat.setBounds(145, 119, 142, 20);
		frmAgregarUsuarios.getContentPane().add(textpasswordrepeat);
		textpasswordrepeat.setColumns(10);
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textusuario.setText("");
				textpassword.setText("");
				textpasswordrepeat.setText("");
				btnCrearUsuario.setEnabled(false);
			}
		});
		btnClear.setBounds(177, 204, 89, 23);
		frmAgregarUsuarios.getContentPane().add(btnClear);
		
		
	}

}
