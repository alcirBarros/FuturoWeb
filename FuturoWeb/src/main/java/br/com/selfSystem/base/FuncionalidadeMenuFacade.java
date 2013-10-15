package br.com.selfSystem.base;

import br.com.selfSystem.facade.Facade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

import br.com.selfSystem.util.GenericFacade;

public class FuncionalidadeMenuFacade extends Facade implements Serializable {

    private static final long serialVersionUID = 6197409005068444645L;
    
    @Inject
    private GenericFacade<Menu> genericFacade;

    public List<Menu> listarTodos(String... orders) {
        return genericFacade.listarTodos(orders);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> listarPorModuloESuperior(Integer tipoMenu, Menu superior) {

        TipoMenu tipoMenu_ = null;

        for (TipoMenu tipo : TipoMenu.values()) {
            if (tipo.getCodigo().equals(tipoMenu.toString())) {
                tipoMenu_ = tipo;
                break;
            }
        }

        if (superior != null) {
            return em.createQuery("select f from Menu f where menuSuperior =:superior")                                                                                                                                        .getResultList();
        } else {
            return em.createQuery("select f from Menu f where f.tipoMenu = :tipoMenu")                                                                                                                                     .getResultList();
        }
    }
}
