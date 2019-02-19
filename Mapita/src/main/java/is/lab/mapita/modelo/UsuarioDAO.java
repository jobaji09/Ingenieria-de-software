/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.modelo;

import java.util.List;

/**
 *
 * @author jonathan
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {
    
    /**
     * 
     */
    public UsuarioDAO(){
        super();
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void save(Usuario usuario){
        super.save(usuario);
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public  void update(Usuario usuario){
        super.update(usuario);
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void delete(Usuario usuario){
        super.delete(usuario);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Usuario find(int id){
        return super.find(Usuario.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Usuario> findAll(){
        return super.findAll(Usuario.class);
    
    }
    
}
