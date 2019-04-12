/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.controlador;

import is.lab.mapita.modelo.Marcador;
import is.lab.mapita.modelo.MarcadorDAO;
import is.lab.mapita.modelo.Usuario;
import is.lab.mapita.modelo.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
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
public class VerMarcadoresU implements Serializable{
    private MapModel simpleModel;
    
    private Marker marker;
    
    private Marcador marcador;
    
    private Set marcadores;
    @PostConstruct
    public void verMarcadores(){
        this.simpleModel = new DefaultMapModel();
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        this.marcadores = u.getMarcadors();
        for(Object o :marcadores){
            Marcador m = (Marcador)o;
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker mark = new Marker(cord,m.getDescripcion());
            mark.setIcon("../"+m.getIcon());
            this.simpleModel.addOverlay(mark);
        }
        
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay();
       MarcadorDAO mdb = new MarcadorDAO();
       
       this.marcador = mdb.buscaMarcadorPorLatLng(marker.getLatlng().getLat(),marker.getLatlng().getLng());
       PrimeFaces current = PrimeFaces.current();
       current.executeScript("PF('dlg').show();");
    }

    public Marker getMarker() {
        return marker;
    }

    public Set getMarcadores() {
        return marcadores;
    }
    
    public Marcador getMarcador() {
        return marcador;
    }
    
    public void eliminaMarcador(){
        MarcadorDAO mdb = new MarcadorDAO();
        this.simpleModel.getMarkers().remove(this.marker);
        mdb.delete(this.marcador);
        Mensajes.info("Se a borrado el marcador\n"+this.marker.getLatlng());
    }
    
}
