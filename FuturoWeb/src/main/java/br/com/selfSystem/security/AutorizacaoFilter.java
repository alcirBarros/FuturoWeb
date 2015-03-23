package br.com.selfSystem.security;

import java.io.IOException;
import java.security.Principal;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "bAutorizacaoFilter", urlPatterns = "/pages/*")
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String context = httpServletRequest.getContextPath();

        String url = httpServletRequest.getRequestURI().replaceFirst(httpServletRequest.getContextPath(), "");

        if (url.equals("/pages/security/login.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        // permissao temporária para relatorios! implementar controle na tela de perfis de acesso.
        if (url.startsWith("/pages/filtro-relatorio/") || url.startsWith("/pages/digitacao/acompanhamentoDigitacao")) {
            chain.doFilter(request, response);
            return;
        }
        // permissao temporária
        if (url.endsWith("/pages/mensagem/mensagem.faces")) {
            chain.doFilter(request, response);
            return;
        }

        Principal principal = httpServletRequest.getUserPrincipal();

        if (principal == null) {
            chain.doFilter(request, response);
            return;
        }

//        if (operadorLogadoEstaVazio() || umUsuarioDiferenteLogou(principal)) {
//            Operador operador = operadorFacade.carregaPor(principal.getName());
//            Acesso acesso = preencheAcessoAtivo(operador);
//            preencheOperadorLogado(operador, acesso);
//            preencheModuloAtivo(operador);
//            gravaIdentificadorNaSessao(httpServletRequest, principal);
//        }

        if (url.equals("/pages/principal.faces") || url.equals("/pages/security/login.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        
//        Perfil perfil = acessoAtivo.getAcesso().getPerfil();

//        if (!perfil.possuiFuncionalidadeMenu(url)) {
//            new LogService().debug("Tentando acessar " + url + "... Negado!");
//            enviaRequisicaoParaPaginaPrincipal(httpServletResponse, context);
//        }

        chain.doFilter(request, response);
    }

    private void gravaIdentificadorNaSessao(HttpServletRequest httpServletRequest, Principal principal) {
        httpServletRequest.getSession().setAttribute("identificador", principal.getName());
    }

    private void enviaRequisicaoParaPaginaPrincipal(HttpServletResponse httpServletResponse, String context) throws IOException {
        httpServletResponse.sendRedirect(context + "/pages/principal.faces");
    }

//    private boolean umUsuarioDiferenteLogou(Principal principal) {
//        return !operadorLogado.getOperador().getIdentificador().equals(principal.getName());
//    }

//    private boolean operadorLogadoEstaVazio() {
//        return operadorLogado.getOperador() == null;
//    }
    
//    private Acesso preencheAcessoAtivo(Operador operador) {
//        UltimoAcessoAtivo ultimoAcesso = ultimoAcessoAtivoFacade.carregaPor(operador);
//        acessoAtivo.setAcesso(acessoFacade.carregar(ultimoAcesso.getAcessoId()));
//        if (ultimoAcesso.getSubsetorId() != null) {
//            acessoAtivo.setSubsetor(subsetorFacade.carregar(ultimoAcesso.getSubsetorId()));
//        }
//        return acessoAtivo.getAcesso();
//    }

//    private void preencheOperadorLogado(Operador operador, Acesso acesso) {
//        
//        operadorLogado.setOperador(operador);
//        
//        operadorLogado.setAcessoAtivo(acesso);
//
//        operadorLogado.setPerfilId(acesso.getPerfil().getId());
//        
//        Municipio municipio = municipioFacade.carregaMunicipioPadraoLimpo();
//        operadorLogado.setCidade(municipio.getCidade());
//
//        Estabelecimento estabelecimentoAtivo = new Estabelecimento(acesso.getEstabelecimento().getId(), acesso.getEstabelecimento().getNomeFantasia());
//        estabelecimentoAtivo.setCnpjMantenedora(acesso.getEstabelecimento().getCnpjMantenedora());
//        operadorLogado.setEstabelecimentoAtivo(estabelecimentoAtivo);
//    }
    
//    private void preencheModuloAtivo(Operador operador) {
//        UltimoModuloAtivo ultimoModuloAtivo = ultimoModuloAtivoFacade.carregaPor(operador);
//        Modulo modulo = moduloFacade.carregaPor(ultimoModuloAtivo.getModuloId());
//        moduloAtivo.set(modulo);
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
