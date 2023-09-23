package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

//GUI

public class Demo01 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();
		
		Usuario u = new Usuario();
		u.setCod_usua(123);
		u.setNom_usua("Juan");
		u.setApe_usua("Perez");
		u.setUsr_usua("jperez");
		u.setCla_usua("7854");
		u.setFna_usua("2000/01/15");
		u.setIdtipo(1);
		u.setEst_usua(1);
		//(123,"Juan","Perez","jperez","7854",
				//"2000/01/15",1,1);
		
		em.getTransaction().begin();
		em.persist(u);
		//UPDATE tb_xxx set campo =?.....  where...
		em.merge(u);
		//delete from tb_xxx where
		em.remove(u);
		// select¨from tb_xxx where
		Usuario x = em.find(Usuario.class, 1);
		em.getTransaction().commit();
		System.out.print("Registro OK");
		
		em.close();
		
	}

}
