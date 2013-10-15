package br.com.selfSystem.base;

import java.io.Serializable;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class AbstractMb implements Serializable {

    private static final long serialVersionUID = -2181262520709480304L;
    protected ModoTela modo = new ModoTela();

    protected String getParameter(String paramName) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(paramName);
    }

    public ModoTela getModo() {
        return modo;
    }

    public void setModo(ModoTela modo) {
        this.modo = modo;
    }

    // substituido pela geracao de nova navegacao string
    public void cleanSubmittedValues(UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        if (component.getChildCount() > 0) {
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
    }
}
