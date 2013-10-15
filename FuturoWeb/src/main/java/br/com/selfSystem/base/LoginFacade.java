package br.com.selfSystem.base;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import br.com.selfSystem.entity.Operador;
import br.com.selfSystem.facade.Facade;

public class LoginFacade extends Facade implements Serializable {

    private static final long serialVersionUID = -8371573905354744238L;


    public Operador login(String identificador, String senha) {

        if (identificador == null || !identificador.matches(Operador.patternLoginValido)) {
            return null;
        }

        if (senha == null || !senha.matches(Operador.patternSenhaValida)) {
            return null;
        }

        try {
            senha = new Seguranca().criptografaSenha(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginFacade.class.getName()).log(Level.SEVERE,
                                                              null, ex);
        }

        Query query = em
                                                                                                                                                    .createQuery("select o from Operador o left join fetch o.acessos where o.identificador = :i and o.senha = :s");
        query.setParameter("i", identificador);
        query.setParameter("s", senha);
        List<Operador> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            Logger.getLogger(LoginFacade.class.getSimpleName()).log(Level.INFO,
                                                                    "{0} conectou-se ao sistema.", identificador);
            Operador op = resultList.get(0);
            return op;
        }

        return null;
    }
}
