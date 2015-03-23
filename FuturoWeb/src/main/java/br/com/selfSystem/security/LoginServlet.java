package br.com.selfSystem.security;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    String runningVersion = null;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        runningVersion = StringUtils.substringBefore(StringUtils.substringAfter(servletConfig.getServletContext().getRealPath("/"), "##"), "/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtils.isNotBlank(runningVersion)) {
        //    operadorLogado.setRunningVersion(runningVersion);
        }
        response.sendRedirect(request.getContextPath() + "/pages/security/login.jsp");
    }
}
