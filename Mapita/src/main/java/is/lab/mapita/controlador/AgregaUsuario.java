/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.controlador;

import is.lab.mapita.modelo.Rol;
import is.lab.mapita.modelo.Usuario;
import is.lab.mapita.modelo.UsuarioDAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jonathan
 */
@ManagedBean
@RequestScoped
public class AgregaUsuario {
    private String nombre;
    private String correo;
    private String contrasenia;
    private Date fechanacimiento;
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    
    public void agregaUsuario(){
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasenia(contrasenia);
        u.setFechanacimiento(fechanacimiento);
        if(this.rol.equals("user")){
            u.setRol(Rol.USER);
        }else{
            u.setRol(Rol.SUPERUSER);
        }
            
        UsuarioDAO udb = new UsuarioDAO();
        udb.save(u);
        
    }
    
}
