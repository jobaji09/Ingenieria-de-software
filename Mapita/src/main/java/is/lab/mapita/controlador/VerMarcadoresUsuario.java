/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.controlador;

import is.lab.mapita.modelo.Marcador;
import is.lab.mapita.modelo.MarcadorDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author jonh
 */
@ManagedBean
@ViewScoped
public class VerMarcadoresUsuario implements Serializable{
    
    private MapModel simpleModel;
    
    
    
    @PostConstruct
    public void verMarcadores(){
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        ControladorSesion.UserLogged ul = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        List<Marcador> marcadores = mdb.ObtenMarcadoresPorUsuario(ul.getCorreo());
        
        for(Marcador m :marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,"Mi Marcador",m.getDescripcion());
            simpleModel.addOverlay(marcador);
        }
        
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    
    
    
}
