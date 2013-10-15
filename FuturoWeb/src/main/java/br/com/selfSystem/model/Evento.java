package br.com.selfSystem.model;


import br.com.selfSystem.webservice.DateAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Evento {

    private Integer id;
    private String nome;
    private Date inicio;

    public Evento() {
    }

    public Evento(Integer id, String nome, Date inicio) {
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
}
