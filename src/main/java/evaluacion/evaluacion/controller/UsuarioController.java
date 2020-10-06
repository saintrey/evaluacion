package evaluacion.evaluacion.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import evaluacion.evaluacion.entity.Usuario;
import evaluacion.evaluacion.model.JwtUser;
import evaluacion.evaluacion.security.JwtGenerator;
import evaluacion.evaluacion.security.JwtValidator;
import evaluacion.evaluacion.service.IUsuarioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    @Autowired
    private JwtGenerator generator;

    @Autowired
    private JwtValidator validator;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) throws Exception {

        String user = usuario.getUser();
        String password = usuario.getPassword();
        if (user == null || password == null) {
            return new ResponseEntity<Void>(HttpStatus.FAILED_DEPENDENCY);
        }
        Integer valor = service.devuelve_acceso(user, password).intValue();
        switch (valor) {
            case 1:
                JwtUser jwt = new JwtUser();
                jwt.setId(user);
                jwt.setUserName(user);
                String token = generator.generate(jwt);
                if (token != null) {
                    Usuario login = service.devuelve_user(user);
                    login.setLast_login(new Date());
                    service.save(login);
                    return new ResponseEntity<>((Collections.singletonMap("token", token)), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
                }
            case 0:
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/listar_usuarios")
    public ResponseEntity<?> listar(@RequestHeader(name = "Authorization") String bearer) throws Exception {
        if (bearer == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        JwtUser jwtUser = validator.validate(bearer.substring(7));
        if (jwtUser != null) {
            String user = jwtUser.getId();
            if (service.devuelve_user(user) != null) {
                List<Usuario> usuario = service.listaAll();
                if (usuario == null) {
                    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(usuario, HttpStatus.OK);
                }
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/save_usuario")
    public ResponseEntity<?> save(@RequestBody Usuario usuario, @RequestHeader(name = "Authorization") String bearer)
            throws Exception {
        if (bearer == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        String email = usuario.getEmail();
        if(!email.contains("@")){
            return new ResponseEntity<>((Collections.singletonMap("El correo no es valido", email)), HttpStatus.BAD_REQUEST);
        }
        if(!email.contains("@dominio.cl")){
            return new ResponseEntity<>((Collections.singletonMap("El correo debe ser @dominio.cl", email)), HttpStatus.BAD_REQUEST);
        }
        
        JwtUser jwtUser = validator.validate(bearer.substring(7));
        if (jwtUser != null) {
            String user = jwtUser.getId();
            if (service.devuelve_user(user) != null) {
                if (service.save(usuario).intValue() == 1) {
                    return new ResponseEntity<>(usuario, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(usuario, HttpStatus.IM_USED);
                }
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id, @RequestHeader(name = "Authorization") String bearer)
            throws Exception {
        if (bearer == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        JwtUser jwtUser = validator.validate(bearer.substring(7));
        if (jwtUser != null) {
            String user = jwtUser.getId();
            if (service.devuelve_user(user) != null) {
                Usuario usuario = service.findById(id);
                if (usuario == null) {
                    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(usuario, HttpStatus.OK);
                }
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
