package br.com.selfSystem.facade;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;

import br.com.selfSystem.base.Menu;
import br.com.selfSystem.base.Seguranca;
import br.com.selfSystem.entity.Operador;
import br.com.selfSystem.entity.Pessoa;
import br.com.selfSystem.util.BusinessException;
import br.com.selfSystem.util.GenericFacade;
import javax.persistence.EntityManager;

public class OperadorFacade extends Facade implements Serializable {

    private static final long serialVersionUID = -5527346777241342040L;
    
    private GenericFacade<Operador> genericFacade;

    @PostConstruct
    private void init() {
        genericFacade = new GenericFacade<Operador>(em, Operador.class);
    }
    
    public OperadorFacade setEm(EntityManager em) {
        this.em = em;
        genericFacade = new GenericFacade<Operador>(em, Operador.class);
        return this;
    }

    public void inserir(Operador operador) throws Exception {

        // exceções de sistema
        if (operador == null) {
            throw new Exception("O objeto operador informado é nulo!");
        }
        if (operador.getPessoa() == null) {
            throw new Exception("O objeto operador nao tem uma pessoa vinculada a ele.");
        }
        if (operador.getIdentificador() == null) {
            throw new Exception("O identificador é nulo!");
        }
        if (operador.getSenha() == null) {
            throw new Exception("A senha é nula!");
        }

        // exceções de negócio
        if (!operador.getIdentificador().matches(Operador.patternLoginValido)) {
            throw new BusinessException("Padrão de identificador inválido.");
        }
        if (!operador.getSenha().matches(Operador.patternSenhaValida)) {
            throw new BusinessException("Padrão de senha inválido.");
        }

        // criptografar senha
        try {
            operador.setSenha(new Seguranca().criptografaSenha(operador.getSenha()));
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception("Erro ao criptografar senha.");
        }

        if (operador.getPessoa().getId() == null) { //insert   // todo: <-- porque esse insert aqui???
            Pessoa pessoa = new PessoaFacade().setEm(em).inserir(operador.getPessoa());
            operador.setPessoa(pessoa);
        }
        genericFacade.inserir(operador);
    }
}
