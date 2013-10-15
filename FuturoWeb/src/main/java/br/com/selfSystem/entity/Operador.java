package br.com.selfSystem.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.selfSystem.base.Acesso;
import br.com.selfSystem.base.Menu;

@Entity
@Table(name = "opr_operador")
public class Operador implements Serializable {

    private static final long serialVersionUID = -723668970898386600L;
    public static final String patternLoginValido = "[a-zA-Z0-9]{6,20}";
    public static final String patternSenhaValida = "[a-zA-Z0-9]{8,20}";
    @Id
    @GeneratedValue
    @Column(name = "opr_id")
    private Long id;
    
    @Column(name = "opr_logim")
    private String identificador;
    
    @Column(name = "opr_senha")
    private String senha;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pss_id", referencedColumnName = "pss_id")
    private Pessoa pessoa;
    
    @OneToMany(mappedBy = "operador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Acesso> acessos = new ArrayList<Acesso>();
    
    @ManyToOne
    @JoinColumn(name = "mdl_id", referencedColumnName = "mdl_id")
    private Modulo moduloSelecionado;
    
    @ManyToOne
    @JoinColumn(name = "acs_id", referencedColumnName = "acs_id")
    private Acesso acessoSelecionado;
    

    public Operador() {
        pessoa = new Pessoa();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    public Modulo getModuloSelecionado() {
        return moduloSelecionado;
    }

    public void setModuloSelecionado(Modulo moduloSelecionado) {
        this.moduloSelecionado = moduloSelecionado;
    }

    public Acesso getAcessoSelecionado() {
        return acessoSelecionado;
    }

    public void setAcessoSelecionado(Acesso acessoSelecionado) {
        this.acessoSelecionado = acessoSelecionado;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Operador other = (Operador) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
