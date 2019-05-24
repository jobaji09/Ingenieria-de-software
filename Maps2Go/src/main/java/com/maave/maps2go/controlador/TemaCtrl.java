package com.maave.maps2go.controlador;

import com.maave.maps2go.modelo.Tema;
import com.maave.maps2go.modelo.TemaDAO;
import com.maave.maps2go.modelo.Usuario;
import com.maave.maps2go.modelo.UsuarioDAO;
import com.maave.maps2go.modelo.Marcador;
import com.maave.maps2go.modelo.MarcadorDAO;
import com.maave.maps2go.vista.TemaAgregadoIH;
import com.maave.maps2go.vista.TemaExistenteIH;
import com.maave.maps2go.vista.CampoVacioIH;
import com.maave.maps2go.vista.ColorExistenteIH;
import com.maave.maps2go.vista.MarcadorAgregadoIH;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class TemaCtrl implements Serializable{

    private String color;
    private String tipoTema;
    private Tema selectedTema;
    private List<Tema> temas;
    private String datosUtiles;
    private String descripcion;
    private double latitud;
    private double longitud;
    private int numMarcador;
    private Tema tema;
    private Marker marcador;
    private Marker marker;
    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        TemaDAO tm = new TemaDAO();
        temas = tm.consultarTodos();
        simpleModel = new DefaultMapModel();
        marcador = new Marker(new LatLng(23.382390, -102.291477));
        marcador.setDraggable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }

    public String getDatosUtiles() {
        return datosUtiles;
    }

    public void setDatosUtiles(String datosUtiles) {
        this.datosUtiles = datosUtiles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTipoTema() {
        // Automatically generated method. Please do not modify this code.
        return this.tipoTema;
    }

    public void setTipoTema(String tipoTema) {
        // Automatically generated method. Please do not modify this code.
        this.tipoTema = tipoTema;
    }

    public String getColor() {
        // Automatically generated method. Please do not modify this code.
        return this.color;
    }

    public void setColor(String color) {
        // Automatically generated method. Please do not modify this code.
        this.color = color;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public Tema getSelectedTema() {
        return selectedTema;
    }

    public void setSelectedTema(Tema selectedTema) {
        this.selectedTema = selectedTema;
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public String agregarTema() {
        TemaDAO tdb = new TemaDAO();
        if(tipoTema.compareTo("")==0 || 
                color==null || 
                descripcion.compareTo("")==0 || 
                datosUtiles.compareTo("") == 0){
            CampoVacioIH vacio = new CampoVacioIH();
            vacio.mostrarMensaje();
//            
//            System.out.println("Campo Vacio");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"WARNING!", "Campo Vacio"));
            return "";
        }else if(tdb.existeTema(tipoTema)){
            TemaExistenteIH existe = new TemaExistenteIH();
            existe.mostrarMensaje();
           //System.out.println("Ya existe");
           return "";
        }else if(tdb.existeColor(color)){
            ColorExistenteIH existeC = new ColorExistenteIH();
            existeC.mostrarMensaje();            
            //System.out.println("Color existe");
        }else {
            Tema t = new Tema();
            UsuarioDAO udb = new UsuarioDAO();
            SessionCtrl.UsuarioLogged us = (SessionCtrl.UsuarioLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            Usuario u = udb.buscaPorCorreo(us.getCorreo());
            MarcadorDAO mdb = new MarcadorDAO();
            Marcador m = new Marcador();
            t.setTipoTema(tipoTema);
            t.setColor(color);
            t.setUsuario(u);
            tdb.agregar(t);
            m.setDescripcion(descripcion);
            m.setDatosUtiles(datosUtiles);
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setTema(t);
            mdb.agregar(m);
            
            TemaAgregadoIH exito = new TemaAgregadoIH();
            exito.mostrarMensaje();
            //System.out.println("Agregado exitosamente");
        }
        return "/informador/perfil?faces-redirect=true";
    }

    public String agregarMarcador() {
        MarcadorDAO mdb = new MarcadorDAO();
        if(tipoTema.compareTo("")==0 || descripcion.compareTo("")==0 || datosUtiles.compareTo("")==0){
            CampoVacioIH vacio = new CampoVacioIH();
            vacio.mostrarMensaje();
        }else{
            Tema t = new Tema();
            Marcador m = new Marcador();
            t.setTipoTema(tipoTema);
            m.setDescripcion(descripcion);
            m.setDatosUtiles(datosUtiles);
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setTema(t);
            mdb.agregar(m);
            
            MarcadorAgregadoIH exito = new MarcadorAgregadoIH();
            exito.mostrarMensaje();
        }
        return "/informador/agregaMarcador?faces-redirect=true";
    }


    public void consultarTemas() {
    }
    
    public List<Tema> temasPropios(String tipoTema, int usuario){
        UsuarioDAO udb = new UsuarioDAO();
        SessionCtrl.UsuarioLogged us = (SessionCtrl.UsuarioLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        TemaDAO tdb = new TemaDAO();
        List<Tema> t = tdb.temaPropio(tipoTema, u.getIdUsuario());
        return t;
    }
    
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay(); 
    }
    
    public void onMarkerDrag(MarkerDragEvent event){
        marcador = event.getMarker();
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        marcador = simpleModel.getMarkers().get(0);
        marcador.setLatlng(latlng);
        this.latitud = latlng.getLat();
        this.longitud = latlng.getLng();

    }

    /*los siguientes metodos son lolo temporales para probar el listener de temas*/
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Tema Selected", ((Tema) event.getObject()).getTipoTema());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Tema Unselected", ((Tema) event.getObject()).getTipoTema());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
