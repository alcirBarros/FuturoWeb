package br.com.selfSystem.facade;

import br.com.selfSystem.base.Menu;
import br.com.selfSystem.base.Perfil;
import br.com.selfSystem.entity.Modulo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;

/**
 * @author Alci Barros
 * @since 17/09/2013
 */
public class ModuloFacade extends Facade{
    
    public List<Modulo> listar(String orders) {
        StringBuilder query = new StringBuilder();
        query.append("select new Modulo( m.id, m.descricao, m.icone, m.sigla ) from Modulo m");

        if (orders != null && !orders.equals("")) {
            query.append(" order by ");
            query.append(orders);
        }

        List<Modulo> modulos = em.createQuery(query.toString()).getResultList();

        return modulos;
    }


    public List<Modulo> listarModulosDoPerfil(Perfil perfil) {
        Perfil perfil_ = (Perfil) em.createQuery("select p from Perfil p  where p =:perfil")
                                    .setParameter("perfil", perfil)
                                    .getSingleResult();

        if (perfil_ != null) {
            List<Modulo> modulos = new ArrayList<Modulo>();

            for (Menu menu : perfil_.getFuncionalidades()) {
                //Modulos
                if (!(modulos.contains(menu.getModulo()))) {
                    menu.getModulo().getMenus().clear();
                //    menu.getModulo().getFuncionalidadesNegocio().clear();
                    modulos.add(menu.getModulo());
                }

                for (Modulo modulo : modulos) {
                    if (modulo.getId().equals(menu.getModulo().getId())) {
                        //Acessos
                        if (!modulo.getMenus().contains(menu)) {
                            if (menu.getMenuSuperior()!= null && menu.getMenuSuperior().getMenuSuperior() == null) {
                                if (!modulo.getMenus().contains(menu.getMenuSuperior())) {
                                    menu.getMenuSuperior().getMenus().clear();
                                    modulo.getMenus().add(menu.getMenuSuperior());
                                }
                            }

                            for (Menu subMenus : modulo.getMenus()) {
                                if ((menu.getMenuSuperior() != null && menu.getMenuSuperior().getMenuSuperior() != null) && (menu.getMenuSuperior().getMenuSuperior().getId().equals(subMenus.getId())) && !subMenus.getMenus().contains(menu.getMenuSuperior())) {
                                    menu.getMenuSuperior().getMenus().clear();
                                    subMenus.adicionarMenu(menu.getMenuSuperior());
                                }
                            }
                            //add superiror 1
                            if ((menu.getMenuSuperior() != null && menu.getMenuSuperior().getMenuSuperior() != null) && !modulo.getMenus().contains(menu.getMenuSuperior().getMenuSuperior())) {
                                menu.getMenuSuperior().getMenuSuperior().getMenus().clear();
                                modulo.getMenus().add(menu.getMenuSuperior().getMenuSuperior());
                            }
                        }
                        for (Menu menu1 : modulo.getMenus()) {
                            if (menu.getMenuSuperior() != null && menu.getMenuSuperior().equals(menu1)) {
                                if (!menu1.getMenus().contains(menu)) {
                                    menu.getMenus().clear();
                                    menu1.adicionarMenu(menu);
                                }
                            }
                            //add menu 2
                            if ((menu.getMenuSuperior() != null) && (menu.getMenuSuperior().getMenuSuperior() != null && menu.getMenuSuperior().getMenuSuperior().equals(menu1))) {
                                if (!menu1.getMenus().contains(menu.getMenuSuperior())) {
                                    menu.getMenuSuperior().getMenus().clear();
                                    menu1.adicionarMenu(menu.getMenuSuperior());
                                }// add menu 3
                                for (Menu menu2 : menu1.getMenus()) {
                                    if (menu.getMenuSuperior().equals(menu2) && !menu2.getMenus().contains(menu)) {
                                        menu.getMenus().clear();
                                        menu2.adicionarMenu(menu);
                                    }
                                }
                            }
                        }
                    }
                }
            }


            for (Modulo modulo : modulos) {
                for (Menu modulo1 : modulo.getMenus()) {
                    for (Menu modulo2 : modulo1.getMenus()) {
                        Hibernate.initialize(modulo2.getMenus());
                    }
                }
            }


//            for (FuncionalidadeNegocio funcionalidadeNegocio : perfil_.getFuncionalidadesNegocio()) {
//                for (Modulo modulo : modulos) {
//                    if (funcionalidadeNegocio.getModulo().getId().equals(modulo.getId())) {
//                        modulo.getFuncionalidadesNegocio().add(funcionalidadeNegocio);
//                        break;
//                    }
//                }
//            }
            return modulos;

        }
        return new ArrayList<Modulo>();
    }
}
