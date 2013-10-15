package br.com.selfSystem.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import br.com.selfSystem.util.BusinessException;

@Entity
@Table(name = "tb_perfil")
public class Perfil implements Serializable {

    private static final long serialVersionUID = -2775993238318442787L;
    
    @Id
    @GeneratedValue
    @Column(name = "per_id")
    private Integer id;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "fmp_funcionalidade_menu_perfil", joinColumns = {
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")},
    inverseJoinColumns = {
    @JoinColumn(name = "men_menu_id", referencedColumnName = "men_id")})
    private List<Menu> funcionalidades = new ArrayList<Menu>();
    
    @Transient
    private Map<Integer, Integer> funcionalidadesMap;
    
    @Transient
    private Integer totalPerfis;

    public void adicionarFuncionalidade(Menu funcionalidadeMenu) throws BusinessException {
        for (Menu funcionalidade_ : getFuncionalidades()) {
            if (funcionalidade_.equals(funcionalidadeMenu)) {
                throw new BusinessException("Esta funcionalidade já foi adicionada.");
            }
        }
        this.getFuncionalidades().add(funcionalidadeMenu);
    }

    public Perfil() {
        super();
    }

    public Perfil(String descricao) {
        super();
        this.descricao = descricao;
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

    public List<Menu> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Menu> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public Map<Integer, Integer> getFuncionalidadesMap() {
        return funcionalidadesMap;
    }

    public void setFuncionalidadesMap(Map<Integer, Integer> funcionalidadesMap) {
        this.funcionalidadesMap = funcionalidadesMap;
    }

    public Integer getTotalPerfis() {
        return totalPerfis;
    }

    public void setTotalPerfis(Integer totalPerfis) {
        this.totalPerfis = totalPerfis;
    }
}
