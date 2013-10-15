package br.com.selfSystem.bean;

import br.com.selfSystem.base.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PerfilMb implements Serializable{
    
    private List<Perfil> perfius =new ArrayList<Perfil>();

    public List<Perfil> getPerfius() {
        return perfius;
    }

    public void setPerfius(List<Perfil> perfius) {
        this.perfius = perfius;
    }
}
