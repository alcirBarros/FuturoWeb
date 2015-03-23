package br.com.selfSystem.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "authErrorServlet", urlPatterns = {"/authError"})
public class AuthErrorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "Login/senha inválidos. Tente novamente.");
        request.getRequestDispatcher("/pages/security/login.jsp").forward(request, response);
    }
}
