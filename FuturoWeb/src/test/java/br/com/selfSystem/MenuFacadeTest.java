package br.com.selfSystem;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.selfSystem.base.MenuFacade;
import br.com.selfSystem.util.JPAUtil;

public class MenuFacadeTest implements Serializable {

    private static final long serialVersionUID = 5674382124442820982L;
    private static EntityManager em;
    private static MenuFacade facade;

    @BeforeClass
    public static void setUpClass() throws Exception {
        em = new JPAUtil().getEntityManager();
        //	facade = new MenuFacade().setEm(em);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        em.close();
    }

    @Test
    public void test() {
        //	facade.listarTodos();
    }
}
