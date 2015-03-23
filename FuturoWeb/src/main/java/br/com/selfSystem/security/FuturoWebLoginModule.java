package br.com.selfSystem.security;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class FuturoWebLoginModule implements LoginModule {

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            if(name.equals("admin") && password.equals("admin")){
            //if (autenticouComSucesso(name, password)) {
            //    new LogService().info(name + " entrou no sistema.");
            //    salvaDadosParaAutorizacao(name);
                return true;
            }
            //new LogService().info("autenticacao falhou para " + name);
            throw new LoginException("Authentication failed");
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);
        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }

    private boolean autenticouComSucesso(String name, String password) {
        
        //if (name == null || !name.matches(Operador.patternLoginValido)) 
        //    return false;
        
        //if (password == null || !password.matches(Operador.patternSenhaValida)) 
        //    return false;
        
        //EntityManager em = new JPAUtil().getEntityManager();
        //Object count = em.createNativeQuery("select count(opr_id) from opr_operador where opr_identificador = :identificador and opr_senha = md5(:senha) and opr_ativo = 1")
//                .setParameter("identificador", name)
//                .setParameter("senha", password)
//                .setMaxResults(1)
//                .getSingleResult();
//        em.close();
        
        //if (!(count instanceof BigInteger) || ((BigInteger)count).intValue() == 0) 
        //    return false;
        
        return true;
    }

    private void salvaDadosParaAutorizacao(String name) {
        login = name;
        
//        EntityManager em = new JPAUtil().getEntityManager();
//        List<String> perfis = em.createNativeQuery("select prf.prf_sigla from opr_operador opr join acs_acesso acs on opr.opr_id = acs.opr_id join prf_perfil prf on acs.prf_id = prf.prf_id where opr.opr_identificador = :identificador")
//                .setParameter("identificador", name)
//                .getResultList();
//        em.close();
        
        userGroups = new ArrayList<>();
//        for (String perfil : perfis) {
//            userGroups.add(perfil);
//        }
    }
}
