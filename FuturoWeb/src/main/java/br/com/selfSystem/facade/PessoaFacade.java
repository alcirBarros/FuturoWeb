package br.com.selfSystem.facade;

import java.io.Serializable;

import br.com.selfSystem.entity.Pessoa;
import br.com.selfSystem.util.GenericFacade;
import javax.persistence.EntityManager;

public class PessoaFacade extends Facade implements Serializable {

    private static final long serialVersionUID = -1485650375662462793L;
    
    private GenericFacade<Pessoa> genericFacade;

    public PessoaFacade setEm(EntityManager em) {
        this.em = em;
        genericFacade = new GenericFacade<Pessoa>(em, Pessoa.class);
        return this;
    }

    public Pessoa inserir(Pessoa entity) throws Exception {
        return genericFacade.inserir(entity);
    }
}
