/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.util.AccumulatorV2;

/**
 *
 * @author vsa
 */
public class LogAccumulator extends AccumulatorV2<String, List<String>> {
  
    private List<String> logy = new ArrayList<String>();

//    public LogAccumulator(List<String> logy) {
//        this.logy = logy;
//    }
//
//    public void setLogy(List<String> logy) {
//        this.logy = logy;
//    }

   
    

    
    
    @Override
    public boolean isZero() {
        return true;
    }

    @Override
    public AccumulatorV2<String, List<String>> copy() {
        AccumulatorV2<String, List<String>> value = new LogAccumulator();
        value.merge(this);
        return value;
    }

    @Override
    public void reset() {
        logy.clear();
    }

    @Override
    public void add(String in) {
        logy.add(in);
    }

    @Override
    public void merge(AccumulatorV2<String, List<String>> av) {
       logy.addAll(av.value());
    }

    @Override
    public List<String> value() {
        return logy;
    }

    
    
}
