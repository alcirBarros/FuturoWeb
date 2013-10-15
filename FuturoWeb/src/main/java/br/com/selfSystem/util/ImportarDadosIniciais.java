package br.com.selfSystem.util;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.com.selfSystem.base.Acesso;
import br.com.selfSystem.base.Menu;
import br.com.selfSystem.base.Perfil;
import br.com.selfSystem.entity.Modulo;
import br.com.selfSystem.entity.Operador;
import br.com.selfSystem.entity.Pessoa;
import br.com.selfSystem.facade.OperadorFacade;
import br.com.selfSystem.facade.PessoaFacade;

public class ImportarDadosIniciais implements Serializable {

    private static final long serialVersionUID = 8542703419739512432L;
    private static EntityManager em;

    public static void main(String[] args) {

        try {
            em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();
            criaMenus();
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    private static void criaMenus() throws Exception {
        
        Modulo modulo = new Modulo();
        modulo.setId(1);
        modulo.setDescricao("ADMINISTRADOR");
        modulo.setIcone("configuracao.png");
        modulo.setSigla("ADMINISTRACAO");
        modulo.setOrdem(1);
        em.persist(modulo);

        // perfis
        Perfil perfilAdministrador = new Perfil("ADMINISTRADOR");
               
        em.persist(perfilAdministrador);

        Menu nivel1 = new Menu();
        nivel1.setId(1);
        nivel1.setModulo(modulo);
        nivel1.setDescricao("ADMINISTRAÇÃO");
        
        Menu nivel2 = new Menu();
        nivel2.setId(2);
        nivel2.setModulo(modulo);
        nivel2.setDescricao("CADASTRO");
        nivel2.setMenuSuperior(nivel1);
        
        Menu operador = new Menu();
        operador.setId(3);
        operador.setModulo(modulo);
        operador.setDescricao("OPERADOR");
        operador.setUrl("URL");
        nivel2.adicionarMenu(operador);
        
        Menu pessoa = new Menu();
        pessoa.setId(4);
        pessoa.setModulo(modulo);
        pessoa.setDescricao("PESSOA");
        pessoa.setUrl("/pages/pessoa.jsf");
        nivel2.adicionarMenu(pessoa);
        
        Menu perfil = new Menu();
        perfil.setId(5);
        perfil.setModulo(modulo);
        perfil.setDescricao("PERFIL");
        perfil.setUrl("URL");
        nivel2.adicionarMenu(perfil);

        Menu localizacao = new Menu();
        localizacao.setId(6);
        localizacao.setModulo(modulo);
        localizacao.setDescricao("LOCALIZACAO");
        localizacao.setMenuSuperior(nivel1);
        
        Menu mapa = new Menu();
        mapa.setId(7);
        mapa.setModulo(modulo);
        mapa.setDescricao("MAPA");
        mapa.setUrl("/pages/mapa/mapaTeste.jsf");
        localizacao.adicionarMenu(mapa);
        
        nivel1.adicionarMenu(nivel2);
        
        nivel1.adicionarMenu(localizacao);
        

        em.persist(nivel1);
        

        perfilAdministrador.adicionarFuncionalidade(nivel1);
        perfilAdministrador.adicionarFuncionalidade(nivel2);
        perfilAdministrador.adicionarFuncionalidade(operador);
        perfilAdministrador.adicionarFuncionalidade(pessoa);
        perfilAdministrador.adicionarFuncionalidade(perfil);
        perfilAdministrador.adicionarFuncionalidade(localizacao);
        perfilAdministrador.adicionarFuncionalidade(mapa);
        
        

        em.merge(perfilAdministrador);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("JOSÉ DA SILVA");
        //	pessoa.setSexo(Sexo.MASCULINO);
        //	pessoa.setDataNascimento(new Date());
        //	pessoa.setMae(new Pessoa("MARIA DA SILVA"));
        //	pessoa.setEnderecoPrincipal(null);
        new PessoaFacade().setEm(em).inserir(pessoa2);

        Operador operador2 = new Operador();
        operador2.setPessoa(pessoa2);
        operador2.setIdentificador("adminn");
        operador2.setSenha("s4l11t32012");
        operador2.setAcessos(new ArrayList<Acesso>());
        
        Acesso acesso = new Acesso(operador2, perfilAdministrador);
        operador2.getAcessos().add(acesso);
        operador2.setAcessoSelecionado(acesso);
        operador2.setModuloSelecionado(modulo);
        new OperadorFacade().setEm(em).inserir(operador2);

    }
}
