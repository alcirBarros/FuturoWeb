package br.com.selfSystem.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

public class LoginFilter implements Filter {

    private final static String FILTER_APPLIED = "_security_filter_applied";
    private HashMap<String, HashMap<Date, String>> usuarioMap;

    public LoginFilter() {
        usuarioMap = new HashMap<>();
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hresp = (HttpServletResponse) response;
        HttpSession session = hreq.getSession();

        hreq.getPathInfo();
        String paginaAtual = new String(hreq.getRequestURL());
        HashMap<Date, String> rastreador = new HashMap<>();

        if (!paginaAtual.endsWith("login.jsp")
                && (paginaAtual.endsWith(".faces"))) {

            String user = null;
            if (StringUtils.isNotBlank(hreq.getUserPrincipal().getName())) {
                user = hreq.getUserPrincipal().getName();
                rastreador.put(new Date(), paginaAtual);
                if (!usuarioMap.containsKey(user)) {
                    usuarioMap.put(user, rastreador);
                } else {
                    usuarioMap.get(user).putAll(rastreador);
                }
            }

            if ((user == null) || (user.equals(""))) {
                hresp.sendRedirect("/salute/pages/principal.faces");
                return;
            }
            
            for (Map.Entry<String, HashMap<Date, String>> entrySet : usuarioMap.entrySet()) {
                String key = entrySet.getKey();
                HashMap<Date, String> value = entrySet.getValue();
                
                System.out.println("Mapa key:" + key);
                for (Map.Entry<Date, String> entrySet1 : entrySet.getValue().entrySet()) {
                    Date key1 = entrySet1.getKey();
                    String value1 = entrySet1.getValue();
                    
                    System.out.println("Data:" + key1);
                    System.out.println("Pagina:" + value1);
                }
            }
            
        }
        // entrega a requisição para o proximo filtro    
        chain.doFilter(request, response);
    }
}
