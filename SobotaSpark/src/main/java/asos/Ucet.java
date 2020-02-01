package asos;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author igor
 */
public class Ucet implements Serializable {
    
    private String cislo;
    private double stav;
    
    public void pripocitajUrok() {
        stav = stav + stav * 0.01;
    }

    public void pripocitajUrok(double sadzba) {
        stav = stav + stav * sadzba;
    }
    
    public void pripocitajUrok(UcetConfig uc) {
        stav = stav + stav * uc.sadzba;
    }

    @Override
    public String toString() {
        return "Ucet{" + "cislo=" + cislo + ", stav=" + stav + '}';
    }

    public Ucet(String cislo, double stav) {
        this.cislo = cislo;
        this.stav = stav;
    }

    public Ucet() {
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }

    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

}
