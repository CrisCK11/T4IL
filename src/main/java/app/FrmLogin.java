package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
	
	//validacion del usuario correcto
	String leerUsuario() {
		if (!txtUsuario.getText().matches("[A-Za-z0-9]+[@][a-z0-9]+[.][a-z]{2,3}")) {
			JOptionPane.showMessageDialog(null, "Ingrese un correo");
			return null;
		}
		return txtUsuario.getText();
	}
	
	
	void registrar() {
		/*
		String usuario=txtUsuario.getText();
		String clave=txtClave.getText();
		*/
		//usare metodos para que pueda validar el usuario y la clave (validaciones)
		String usuario=leerUsuario();
		String clave=txtClave.getText();
		
		//validacion
		if(usuario == null || clave==null) {
			return; //para que salga del flujo
		}
		
		
		//1. Obtener la conexion->tiene que llamar a la unidad de persistencia
		EntityManagerFactory fabrica= 
				Persistence.createEntityManagerFactory("jpa_sesion2");
		//2. CREAR UN MANEJADOR DE LAS ENTIDADES
		EntityManager em=fabrica.createEntityManager();
		//4. ste es mi sentencia debe llevar where para tener un filtro donde idtipo=? --> Lista
		//int xtipo=1; //mi valor que voy a leer 
		String jpql="select u from Usuario u where u.usr_usua= :xusr and u.cla_usua= :xcla"; //xtipo es una variable que va recibir el tipo de dato que yo le quiero dar
		
		//Debera devolverme un elmeento de tipo usuario solo el primero por eso antes era List ahora ya no
		//List<Usuario> lstUsuarios= em.createQuery(jpql,Usuario.class).setParameter("xusr",usuario).setParameter("xcla", clave).getResultList();//getResultlist le devuelve la lsita d eusuarios, el primer valor del set parameter es el valor de :xtipo y el segundo valor es el int xtipo
		
		
		try {
			Usuario u= em.createQuery(jpql,Usuario.class).setParameter("xusr",usuario).setParameter("xcla", clave).getSingleResult();//getResultlist le devuelve la lsita d eusuarios, el primer valor del set parameter es el valor de :xtipo y el segundo valor es el int xtipo
			//5. mostrar el contenido del listado
			//tampoco ya no se necesitaria un for porque es para el login osea traer un usuario
			//for (Usuario u : lstUsuarios) {
			
			//Mostrar contenido de usuario
			/*
			System.out.println("Codigo.....: " + u.getCod_usua());
			System.out.println("Nombre.....: " + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo.....: " + u.getIdtipo() + "-" + u.getObjTipo().getDescripcion()); // a traves de getObjTipo ingreso a la propiedad de mi descripcion de mi tipo
			System.out.println("-------------------------------");
			*/
			
			//Mostrar dialogo de bienvenido
			JOptionPane.showMessageDialog(null, "Bienvenido"+ u.getNom_usua());
			//abrir la ventana de menu principal (como prueba abriremos la ventana de mantenimiento)
			FrmManteProd v=new FrmManteProd();
			v.setVisible(true);
			dispose(); //esto se usa en caso tengamos una gui pero como no tenemos y esto es una clase de java no podemos usarla ahora (cerrar la ventana)
			//test de comentario
			//}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Usuario o clave incorrecta");
		}
			
				
		em.close();
	}
}