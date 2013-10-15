package br.com.selfSystem.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

import br.com.selfSystem.util.GenericFacade;

public class GenericFacadeFactory implements Serializable {

    private static final long serialVersionUID = -163994129671732536L;

    @Produces
    public GenericFacade createGenericFacade(InjectionPoint injectionPoint, EntityManager em) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];
        return new GenericFacade(em, classe);
    }
}
