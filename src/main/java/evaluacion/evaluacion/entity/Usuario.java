package evaluacion.evaluacion.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String user;
    private String email;
    private String password;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.DATE)
    private Date modified;

    @Column(name = "last_login")
    @Temporal(TemporalType.DATE)
    private Date last_login;

    private Boolean isactive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Phones> phones = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String user, String email, String password, Date created, Date modified, Date last_login,
            Boolean isactive, List<Phones> phones) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
        this.email = email;
        this.password = password;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.isactive = isactive;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
