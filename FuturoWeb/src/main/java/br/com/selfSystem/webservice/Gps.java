package br.com.selfSystem.webservice;

import br.com.selfSystem.facade.ModuloFacade;
import br.com.selfSystem.model.LatLongGPS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Path("/gps")
public class Gps {

    static final private Map<Integer, LatLongGPS> eventosMap;
    @Inject
    private ModuloFacade moduloFacade;

    static {
        eventosMap = new HashMap<Integer, LatLongGPS>();

        LatLongGPS b1 = new LatLongGPS();
        b1.setId(1);
        b1.setLatitude("25.030442381294925"); 
        b1.setLongitude("-51.20699644088745");
        eventosMap.put(b1.getId(), b1);
        
        LatLongGPS b2 = new LatLongGPS();
        b2.setId(2);
        b2.setLatitude("40.030442381294925");
        b2.setLongitude("-51.20699644088745");
        eventosMap.put(b2.getId(), b2);
    }

    @GET
    @Produces("text/json")
    public List<LatLongGPS> getEventos() {
        return new ArrayList<LatLongGPS>(eventosMap.values());
    }
    
//    @Path("{id}")
//    @GET
//    @Produces("text/xml")
//    public Evento getEvento(@PathParam("id") int id) {
//        return eventosMap.get(id);
//    }
//
    @POST
    @Path("/post")
    @Consumes("text/json")
    @Produces("text/plain")
    public String adiciona(LatLongGPS latLongGPS) {
        LatLongGPS get = eventosMap.get(latLongGPS.getId());
        if(get != null){
            get.setLatitude(latLongGPS.getLatitude());
            get.setLongitude(latLongGPS.getLongitude());
        }else{
            latLongGPS.setId(latLongGPS.getId());
            eventosMap.put(latLongGPS.getId(), latLongGPS);
        }
        return "Adicionado com sucesso.";
    }
    
    @GET
    @Path("/get")
    @Produces("text/json")
    public List<LatLongGPS> getCoodenadas() {
        return new ArrayList<LatLongGPS>(eventosMap.values());
    }
//
//    @Path("{id}")
//    @PUT
//    @Consumes("text/xml")
//    @Produces("text/plain")
//    public String atualizaEvento(Evento evento, @PathParam("id") int id) {
//        Evento atual = eventosMap.get(id);
//        atual.setNome(evento.getNome());
//        atual.setInicio(evento.getInicio());
//        return evento.getNome() + " atualizada.\n";
//    }
//
//    @Path("{id}")
//    @DELETE
//    @Produces("text/plain")
//    public String removeEvento(@PathParam("id") int id) {
//        eventosMap.remove(id);
//        return "Evento removido.\n";
//    }
}
