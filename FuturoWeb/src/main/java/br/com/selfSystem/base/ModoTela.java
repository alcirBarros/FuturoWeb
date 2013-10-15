package br.com.selfSystem.base;

import java.io.Serializable;

public class ModoTela implements Serializable {

    private static final long serialVersionUID = 4399346380316391755L;
    public static final Integer LOCALIZACAO = 1;
    public static final Integer VISUALIZACAO = 2;
    public static final Integer INSERCAO = 3;
    public static final Integer ALTERACAO = 4;
    private Integer id;
    private String desc;

    public ModoTela() {
        this.id = 1;
        this.desc = "Localização";
    }

    public ModoTela(int id) {
        this.id = id;
        if (id == 1) {
            this.desc = "Localização";
        }
        if (id == 2) {
            this.desc = "Visualização";
        }
        if (id == 3) {
            this.desc = "Inserção";
        }
        if (id == 4) {
            this.desc = "Alteração";
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        mudarPara(id);
    }

    public void mudarPara(int novoId) {
        this.id = novoId;
        if (id == LOCALIZACAO) {
            this.desc = "Localização";
        }
        if (id == VISUALIZACAO) {
            this.desc = "Visualização";
        }
        if (id == INSERCAO) {
            this.desc = "Inserção";
        }
        if (id == ALTERACAO) {
            this.desc = "Alteração";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModoTela other = (ModoTela) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }
}
