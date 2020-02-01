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
 * @author igor
 */
public class UcetLoggerAcum extends AccumulatorV2<String, List<String>> {

    // simple logger        
    private List<String> loglist = new ArrayList<String>();

//    public void log(String s) {
//        loglist.add(s);
//    }
//
//    public int logCount() {
//        return loglist.size();
//    }
//
//    public void logPrint() {
//        for (String s : loglist) {
//            System.out.println(s);
//        }
//    }

    @Override
    public boolean isZero() {
        return true;
    }

    @Override
    public AccumulatorV2<String, List<String>> copy() {
        AccumulatorV2<String, List<String>> c = new UcetLoggerAcum();
        c.merge(this);
        return c;
    }

    @Override
    public void reset() {
        loglist.clear();
    }

    @Override
    public void add(String in) {
        loglist.add(in);
    }

    @Override
    public void merge(AccumulatorV2<String, List<String>> av) {
        loglist.addAll(av.value());
    }

    @Override
    public List<String> value() {
        return loglist;
    }
}
