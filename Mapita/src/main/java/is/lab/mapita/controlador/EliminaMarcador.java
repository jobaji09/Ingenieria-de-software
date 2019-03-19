/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.lab.mapita.controlador;

import is.lab.mapita.modelo.Marcador;
import is.lab.mapita.modelo.MarcadorDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Marker;

/**
 *
 * @author jonh
 */
@ManagedBean
@ViewScoped
public class EliminaMarcador {
    private double lat;
    private double lng;
    
    private Marker marcador;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng; 
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marcador =(Marker) event.getOverlay();
        this.lat = marcador.getLatlng().getLat();
        this.lng = marcador.getLatlng().getLng();
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dlg').show();");
       
    }

    public String eliminaMarcador(){
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(lat, lng);
        mdb.delete(m);
        return "/superuser/eliminaMarcadores?faces-redirect=true";
    }
    
    public String eliminaMarcadorv2(){
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(lat, lng);
        if(m == null){
            Mensajes.error("no existe ningun marcador");
            return "";
        }
        mdb.delete(m);
        
        return "/superuser/eliminaMarcadores?faces-redirect=true";
    }
    
}
