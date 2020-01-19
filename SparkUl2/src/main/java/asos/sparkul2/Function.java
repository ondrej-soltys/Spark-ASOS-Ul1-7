/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul2;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;

/**
 *
 * @author vsa
 */
class Function<T0, T1> {

    public Function() {
    }
    public List<String> doStuff(String x){
        return Arrays.asList(x.split(" "));
    }
    
}
