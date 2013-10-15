package br.com.selfSystem.base;

import java.io.Serializable;
import java.util.List;

import br.com.selfSystem.facade.Facade;
import javax.inject.Inject;

import br.com.selfSystem.util.GenericFacade;

public class MenuFacade extends Facade implements Serializable {

    private static final long serialVersionUID = -5563120768628793551L;

    @Inject
    private GenericFacade<Menu> genericFacade;

    public List<Menu> listarTodos(String... orders) {
        return genericFacade.listarTodos(orders);
    }

    public List<Menu> listarFuncionalidadeMenu(TipoMenu tipoMenu) {
        return em.createQuery("select f from Menu f where f.tipoMenu =:tipoMenu")
                .setParameter("tipoMenu", tipoMenu)
                .getResultList();
    }
}
