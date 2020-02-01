/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos;

import java.io.Serializable;

/**
 *
 * @author igor
 */
public class UcetConfig implements Serializable {
        public double sadzba = 0.0;

    public UcetConfig(double s) {
        sadzba = s;
    }
        
}
