package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;

//GUI

public class Demo09 {
	
	//Listados de los usuatios segun un criterio(FILTRO)
	
	public static void main(String[] args) {
		
		String usuario = JOptionPane.showInputDialog("Ingrese Usuario");
		String clave = JOptionPane.showInputDialog("Ingrese Clave");
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();
		
		//select * from tb_usuarios where cusr_usua = ? --> Lista
		String jpql = "select u from Usuario u where u.usr_usua = :xusr and u.cla_usua = :xcla";
		try {
			Usuario u = em.createQuery(jpql, Usuario.class).
					setParameter("xusr", usuario).setParameter("xcla", clave).getSingleResult();
			
			
			//mostrar el contenido del listado

				//System.out.println("Codigo....: " + u.getCod_usua());
				//System.out.println("Nombre....: " + u.getNom_usua() + "" + u.getApe_usua());
				//System.out.println("Tipo....: " + u.getIdtipo() + "-" + u.getObjTipo().getDescripcion());
				//System.out.println("------------------");
				
				JOptionPane.showMessageDialog(null,"Bienvenido " + u.getNom_usua());
				//Abrir ventana prnincipal
				FrmManteProd v = new FrmManteProd();
				v.setVisible(true);
				//dispose();
				
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,"Error: Uusario o clave incorrecto");
			
		}
			
		
		em.close();
		
	}

}
