package br.com.selfSystem.base;

public enum TipoMenu {

    MODULO("1", "MENU"), SUBMENU("2", "SUBMENU"), MENU("3", "MENU");
    private final String codigo;
    private final String descricao;

    TipoMenu(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
