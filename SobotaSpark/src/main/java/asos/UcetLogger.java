/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class UcetLogger implements Serializable {
        // simple logger        
        private  List<String> loglist= new ArrayList<String>();
        public  void log(String s){
            loglist.add(s);
        }
        
        public  int logCount() {
            return loglist.size();
        }
        public void logPrint() {
            for (String s: loglist)
                System.out.println(s);
        }
}
