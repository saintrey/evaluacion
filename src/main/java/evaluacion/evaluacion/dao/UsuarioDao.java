package evaluacion.evaluacion.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import evaluacion.evaluacion.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    @Query(value = "select u.* from usuarios u where u.user = ?1 and u.password=?2", nativeQuery = true)
	public Usuario devuelve_acceso(String user, String password);

    @Query(value = "select u.* from usuarios u where u.user = ?1", nativeQuery = true)
	public Usuario devuelve_user(String user);
    
}
