package br.com.selfSystem.base;

import br.com.selfSystem.entity.Modulo;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.selfSystem.entity.Operador;
import br.com.selfSystem.facade.ModuloFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.model.MenuModel;

@Named
@SessionScoped
public class OperadorLogado implements Serializable {
    
    @Inject
    private ModuloFacade moduloFacade;

    private List<Modulo> modulos;
    private Operador operador;
    private Perfil perfil;
    private Modulo modulo;
    private MenuModel menuModel;
    
    @PostConstruct
    public void init() {
        this.modulos = moduloFacade.listar("ordem");
    }

    public OperadorLogado() {
        this.modulos = new ArrayList<Modulo>();
        this.operador = new Operador();
        this.modulo = new Modulo();
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
