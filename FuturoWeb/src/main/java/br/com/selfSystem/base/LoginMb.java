package br.com.selfSystem.base;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.selfSystem.entity.Operador;
import br.com.selfSystem.util.JsfUtil;

@Named
@ViewScoped
public class LoginMb implements Serializable {

    private static final long serialVersionUID = -2552987715281768070L;
    @Inject
    private OperadorLogado operadorLogado;
    @Inject
    private LoginFacade facade;
    @Inject
    private MenuMb menuMb;
    private String identificador;
    private String senha;

    public String login() {
        try {
            // limpar
            operadorLogado.setModulo(null);
            operadorLogado.setOperador(null);

            // validacao
            if (identificador == null || senha == null || identificador.trim().isEmpty() || senha.trim().isEmpty()) {
                return falharLogin();
            }

            //  logger.info(identificador + " esta tentando logar");

            // login
            Operador operador = facade.login(identificador, senha);

            // validacoes
            if (operador == null) {
                return falharLogin();
            }
            if (operador.getAcessos() == null || operador.getAcessos().isEmpty()) {
                return falharLogin();
            }

            // instituicao selecionada
            operadorLogado.setPerfil(operador.getAcessos().get(0).getPerfil());

            // atualizar operador logado (session)
            operadorLogado.setOperador(operador);
            operadorLogado.setModulos(null);
            operadorLogado.setModulo(operador.getModuloSelecionado());

            // cria menu
            menuMb.gerarMenu(operadorLogado.getOperador().getAcessoSelecionado().getPerfil());

            // limpar e gravar chave de segurança na sessao
            identificador = "";
            senha = "";

            return "/pages/principal/principal.jsf?redirect";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String falharLogin() {
        JsfUtil.addMensagem("Falha de autenticação!");
        return "";
    }

    public String logout() {

        return "/index.jsf";
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
