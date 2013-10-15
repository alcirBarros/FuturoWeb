package br.com.selfSystem.entity;

import br.com.selfSystem.base.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_modulo")
public class Modulo implements Serializable {

    @Id
    @Column(name = "mdl_id", nullable = false)
    private Integer id;
    
    @Column(name = "mdl_descricao", nullable = false)
    private String descricao;
    
    @Column(name = "mdl_icone")
    private String icone;
    
    @Column(name = "mdl_sigla")
    private String sigla;
    
    @Column(name = "mdl_ordem")
    private Integer ordem;
    
    @OneToMany(mappedBy = "modulo")
    private List<Menu> menus;

    public Modulo() {
        this.menus = new ArrayList<Menu>();
    }
    
    public Modulo(Integer id, String descricao, String icone, String sigla) {
        this.id = id;
        this.descricao = descricao;
        this.icone = icone;
        this.sigla = sigla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
