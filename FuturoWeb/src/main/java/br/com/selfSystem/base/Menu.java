package br.com.selfSystem.base;

import br.com.selfSystem.entity.Modulo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "men_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 3546222050832794715L;
    @Id
    @Column(name = "men_id")
    private Integer id;
    
    @Column(name = "men_descricao")
    private String descricao;
    
    @Column(name = "men_url")
    private String url;
    
    @Column(name = "men_icone")
    private String icone;
    
    @ManyToOne
    @JoinColumn(name = "mdl_id", referencedColumnName = "mdl_id", nullable = false)
    private Modulo modulo;
    
    @ManyToOne
    @JoinColumn(name = "men_superior_men_id", referencedColumnName = "men_id")
    private Menu menuSuperior;
    
    @OneToMany(mappedBy = "menuSuperior", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Menu> menus;

    public void adicionarMenu(Menu menu) {
        if (this.menus == null) {
            this.menus = new ArrayList<Menu>();
        }
        menu.setMenuSuperior(this);
        menu.setModulo(this.modulo);
        this.menus.add(menu);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Menu getMenuSuperior() {
        return menuSuperior;
    }

    public void setMenuSuperior(Menu menuSuperior) {
        this.menuSuperior = menuSuperior;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
