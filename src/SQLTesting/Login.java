package SQLTesting;

import java.sql.Connection;
import java.sql.DriverManager;
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
//se incluyen las librerias del framework y las de SQL
public class Login {
//Declaracion de variables del framework y las variables de uso de SQL, Connection, Statement para envio de string a la DB y Resulset para resultados
	private JFrame frame;
	private JTextField textnombre;
	private JTextField textpassword;
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
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogin.setBounds(125, 11, 72, 44);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(50, 88, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(50, 124, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textnombre = new JTextField();
		textnombre.setBounds(106, 85, 146, 20);
		frame.getContentPane().add(textnombre);
		textnombre.setColumns(10);
		
		textpassword = new JTextField();
		textpassword.setBounds(106, 121, 146, 20);
		frame.getContentPane().add(textpassword);
		textpassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() { //Boton de login para usuarios registrados
			public void actionPerformed(ActionEvent e) {
				DataBaseAccess conexion = new DataBaseAccess(); //Llamando a la clase DataBaseAccess y estableciendo conexion
				
				String login=textnombre.getText().trim();        //Pasando los datos a comparar desde los textbox
				String password=textpassword.getText().trim();   //same
				
				conn=conexion.getcon(); //Estableciendo la conexion con la DB
				if (conn!=null)
					lblLogin.setText("Its connected!"); //Label temporal para saber si conecto o no.
				try {
					smtm = conn.createStatement();    //Creando el statement que se pasara a la DB, esta es temporal y sujeta a revision.
					String sql = "SELECT nombre,password FROM login WHERE nombre='frosty'and password='iwakura'";
			        rs=smtm.executeQuery(sql);
			        int count=0;  //Bucle de comprobacion utilizando Resultset para que se mueva a traves de los registros
			        while (rs.next()){
			        	count=count+1;
			        }
			        if (count==1) {   //Si es igual a 1 el campo existe en la DB
			        	JOptionPane.showMessageDialog(null, "Usuario Encontrado!, acceso permitido");
			        }
			        else if (count>1){ //Si es mayor a 1 el campo se encuentra duplicado en la DB
			        	JOptionPane.showMessageDialog(null, "Usuario Duplicado, acceso denegado");
			        }
			        else { //Si es cero entonces el campo no se encontro en la base de datos
			        	JOptionPane.showMessageDialog(null, "Usuario no encontrado, acceso denegado");
			        }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			
			}
		});
		btnLogin.setBounds(106, 186, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //comando para salir del programa
				
			}

			private Object dispose() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnSalir.setBounds(213, 186, 89, 23);
		frame.getContentPane().add(btnSalir);
		
	}

	private Object dispose() {
		// TODO Auto-generated method stub
		return null;
	}
}
