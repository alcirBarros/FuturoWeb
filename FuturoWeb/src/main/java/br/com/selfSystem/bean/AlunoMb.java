package br.com.selfSystem.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AlunoMb implements Serializable {

    private static final long serialVersionUID = -6025296957797399439L;
    String a = "aaaaaaaaaaaaaa";

    public void inicio() {
        System.out.print("Paaaaaaaaaaaaaaaaaa");
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
