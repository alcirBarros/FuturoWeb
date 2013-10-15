package br.com.selfSystem.base;

import br.com.selfSystem.entity.Modulo;
import br.com.selfSystem.facade.ModuloFacade;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.column.Column;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;

@Named
@RequestScoped
public class MenuMb implements Serializable {

    private static final long serialVersionUID = 540840303997356469L;
    @Inject
    private OperadorLogado operadorLogado;
    @Inject
    private ModuloFacade moduloFacade;

    /**
     * @author Alci Barros
     * @since 05/09/2013
     */
    public void gerarMenu(Perfil perfil) {
        try {
            List<Modulo> modulos = moduloFacade.listarModulosDoPerfil(perfil);
            operadorLogado.setModulos(modulos);

            Modulo moduloSelecionado = operadorLogado.getModulo();
            for (Modulo modulo : modulos) {

                if (modulo.equals(moduloSelecionado)) {
                    moduloSelecionado = modulo;
                    break;
                }

            }

            DefaultMenuModel menuModel = new DefaultMenuModel();
            if (moduloSelecionado.getMenus() != null && !moduloSelecionado.getMenus().isEmpty()) {
                for (Menu funcionalidadeMenu : moduloSelecionado.getMenus()) {

                    Submenu menu0 = new Submenu();
                    menu0.setLabel(funcionalidadeMenu.getDescricao());

                    // menuPrincipal
                    Submenu menuPrincipal = new Submenu();
                    menuPrincipal.setLabel(funcionalidadeMenu.getDescricao());

                    // coloutros
                    Column colPrincipal = new Column();
                    colPrincipal.getChildren().add(menuPrincipal);

                    for (Menu funcionalidadeMenu1 : funcionalidadeMenu.getMenus()) {

                        Submenu menu1 = new Submenu();
                        menu1.setLabel(funcionalidadeMenu1.getDescricao());
                        Column col1 = new Column();
                        col1.getChildren().add(menu1);

                        menu0.getChildren().add(col1);

                        for (Menu funcionalidadeMenu2 : funcionalidadeMenu1.getMenus()) {
                            MenuItem item1 = new MenuItem();
                            item1.setValue(funcionalidadeMenu2.getDescricao());
                            item1.setUrl(funcionalidadeMenu2.getUrl());
                            menu1.getChildren().add(item1);
                        }

                    }
                    menuModel.addSubmenu(menu0);
                }
            }

            operadorLogado.setMenuModel(menuModel);

            //        operadorBean.persistirModulo(operadorLogado.getModulo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Alci Barros
     * @since 06/09/2013
     */
    public void gerarMenu() {
        try {

            if (operadorLogado.getModulos().isEmpty()) {
                Perfil perfil = operadorLogado.getOperador().getAcessoSelecionado().getPerfil();
                List<Modulo> modulos = moduloFacade.listarModulosDoPerfil(perfil);
                operadorLogado.setModulos(modulos);
            }

            List<Modulo> modulos = operadorLogado.getModulos();

            Modulo moduloSelecionado = operadorLogado.getModulo();
            for (Modulo modulo : modulos) {

                if (modulo.equals(moduloSelecionado)) {
                    moduloSelecionado = modulo;
                    break;
                }

            }

            DefaultMenuModel menuModel = new DefaultMenuModel();
            if (moduloSelecionado.getMenus() != null && !moduloSelecionado.getMenus().isEmpty()) {
                for (Menu funcionalidadeMenu : moduloSelecionado.getMenus()) {

                    Submenu menu0 = new Submenu();
                    menu0.setLabel(funcionalidadeMenu.getDescricao());

                    // menuPrincipal
                    Submenu menuPrincipal = new Submenu();
                    menuPrincipal.setLabel(funcionalidadeMenu.getDescricao());

                    // coloutros
                    Column colPrincipal = new Column();
                    colPrincipal.getChildren().add(menuPrincipal);



                    for (Menu funcionalidadeMenu1 : funcionalidadeMenu.getMenus()) {

                        Submenu menu1 = new Submenu();
                        menu1.setLabel(funcionalidadeMenu1.getDescricao());
                        Column col1 = new Column();
                        col1.getChildren().add(menu1);

                        menu0.getChildren().add(col1);

                        for (Menu funcionalidadeMenu2 : funcionalidadeMenu1.getMenus()) {
                            MenuItem item1 = new MenuItem();
                            item1.setValue(funcionalidadeMenu2.getDescricao());
                            item1.setUrl(funcionalidadeMenu2.getUrl());
                            menu1.getChildren().add(item1);
                        }

                    }
                    menuModel.addSubmenu(menu0);
                }
            }

            operadorLogado.setMenuModel(menuModel);


            //    operadorBean.persistirModulo(operadorLogado.getModulo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
