/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparklearning;

import java.io.Serializable;

/**
 *
 * @author vsa
 */
public class Ucet implements Serializable{
    
    private String nazov = "";
    private double stav = 0.0;
    
    
    public Ucet(String nazov, double stav){
        this.nazov = nazov;
        this.stav = stav;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }
    
}
