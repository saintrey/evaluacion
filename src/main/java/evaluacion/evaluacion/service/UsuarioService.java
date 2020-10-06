package evaluacion.evaluacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion.evaluacion.dao.UsuarioDao;
import evaluacion.evaluacion.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listaAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public Long devuelve_acceso(String user, String password) throws Exception {
        Usuario usuario = usuarioDao.devuelve_acceso(user, password);
        if(usuario != null){
            return (long) 1;
        }else{
            return (long) 0;
        }
    }

    @Override
    public Long devuelve_user(String user) throws Exception {
        Usuario usuario = usuarioDao.devuelve_user(user);
        if(usuario != null){
            return (long) 1;
        }else{
            return (long) 0;
        }
    }

}
