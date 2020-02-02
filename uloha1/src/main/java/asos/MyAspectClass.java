/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 *
 * @author vsa
 */
public class MyAspectClass {
    
    private int pocet = 0;
    public void messagePocet(JoinPoint jp){
        pocet = pocet +1;
    }

    public int getPocet() {
        return pocet;
    }
    
    public Object cenzure(ProceedingJoinPoint pjp, String message) throws Throwable{
        String msg = message;
        if(msg.contains("Greta")){
            msg = "cenzurovane";
            
        }
       Object[] obj = {msg};
        return pjp.proceed(obj);
    }
    
}
