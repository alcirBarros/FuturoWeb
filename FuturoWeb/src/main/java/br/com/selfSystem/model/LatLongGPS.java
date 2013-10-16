package br.com.selfSystem.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LatLongGPS {
   
    private Integer id;
    private String latitude;
    private String longitude;
    private List<HistoricoCoodenadas> historicoCoodenadas;
    
    public LatLongGPS(){
        this.historicoCoodenadas = new ArrayList<HistoricoCoodenadas>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<HistoricoCoodenadas> getHistoricoCoodenadas() {
        return historicoCoodenadas;
    }

    public void setHistoricoCoodenadas(List<HistoricoCoodenadas> historicoCoodenadas) {
        this.historicoCoodenadas = historicoCoodenadas;
    }
    
    public void addCoordenadas(String string, String string0) {
        HistoricoCoodenadas historicoCoodenada = new HistoricoCoodenadas();
        historicoCoodenada.order = (this.historicoCoodenadas.size() + 1);
        historicoCoodenada.latitude = string;
        historicoCoodenada.longitude = string0;
        this.historicoCoodenadas.add(historicoCoodenada);
    }
    
    public class HistoricoCoodenadas {
        
        private Integer order;
        private String latitude;
        private String longitude;

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
