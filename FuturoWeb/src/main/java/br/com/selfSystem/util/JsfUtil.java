package br.com.selfSystem.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil implements Serializable {

    private static final long serialVersionUID = 4986523543228115251L;

    public static void addMensagem(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
    }
}
