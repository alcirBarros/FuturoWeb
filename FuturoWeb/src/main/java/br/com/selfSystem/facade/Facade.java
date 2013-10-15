package br.com.selfSystem.facade;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author alcir
 */
public class Facade implements Serializable{
    
    @Inject
    protected EntityManager em;
//    
//    @Inject
//    protected transient ResourceBundle bundle;
//    
    
}
