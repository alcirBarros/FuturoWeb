package br.com.selfSystem.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.selfSystem.entity.Operador;

@Entity
@Table(name = "acs_acesso")
public class Acesso implements Serializable {

    private static final long serialVersionUID = -9076818626518638423L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acs_id")
    private Integer id;
    
    @JoinColumn(name = "opr_id", referencedColumnName = "opr_id")
    @ManyToOne(optional = false)
    private Operador operador;
    
//    @JoinColumn(name = "ins_id", referencedColumnName = "ins_id")
//    @ManyToOne(optional=false)
//    private Instituicao instituicao;
    
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    @ManyToOne(optional = false)
    private Perfil perfil;

    public Acesso() {
    }

    public Acesso(Operador operador, Perfil perfil) {
        this.operador = operador;
        //   this.instituicao = instituicao;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Instituicao getInstituicao() {
//        return instituicao;
//    }
//
//    public void setInstituicao(Instituicao instituicao) {
//        this.instituicao = instituicao;
//    }
    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Acesso other = (Acesso) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Acesso{" + "id=" + id + ", operador=" + operador + ", perfil=" + perfil + '}';
    }
}
