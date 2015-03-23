package br.com.selfSystem.security;

import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "pingServlet", urlPatterns = {"/ping"})
public class PingServlet extends HttpServlet {
    
//    @Inject
//    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //em.createNativeQuery("select 1").getResultList();
    }
}
