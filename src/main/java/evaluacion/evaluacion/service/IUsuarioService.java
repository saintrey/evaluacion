package evaluacion.evaluacion.service;

import java.util.List;

import evaluacion.evaluacion.entity.Usuario;

public interface IUsuarioService {

    public List<Usuario> listaAll();

    public Usuario findById(Long id);

    public Long save(Usuario usuario);

    public Long devuelve_acceso(String user, String password) throws Exception;

    public Usuario devuelve_user(String user) throws Exception;

}
