/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.controlador;

import is.lab.mapita.modelo.Usuario;
import is.lab.mapita.modelo.UsuarioDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jonh
 */
@ManagedBean
@ViewScoped
public class verUsuarios {
    private List<Usuario> usuarios;
    
    @PostConstruct
    public void verUsuario(){
        UsuarioDAO udb = new UsuarioDAO();
        this.usuarios = udb.findAll();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    
}
