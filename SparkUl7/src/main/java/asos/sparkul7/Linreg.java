/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul7;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 *
 * @author vsa
 */
public class Linreg {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("linreg").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaPairRDD<String, String> pairLin = sc.textFile("/home/vsa/NetBeansProjects/SparkUl7/src/main/java/asos/sparkul7/body.csv")
                .mapToPair(x -> new Tuple2(x.split(";")[0],x.split(";")[1]));
        pairLin.foreach(x -> System.out.println(Double.parseDouble(x._1) +" "+Double.parseDouble(x._2)));
//        int n = 5;
        Integer n = pairLin.map(x -> (1)).reduce((a,b) ->a+b);
        Double xs = pairLin.map(x ->Double.parseDouble(x._1)).reduce((a,b) -> a+b);
        Double ys = pairLin.map(x ->Double.parseDouble(x._2)).reduce((a,b) -> a+b);
        Double xy = pairLin.map(x ->(Double.parseDouble(x._1)*Double.parseDouble(x._2))).reduce((a,b) -> a+b);
        Double x2 = pairLin.map(x ->(Double.parseDouble(x._1)*Double.parseDouble(x._1))).reduce((a,b) -> a+b);
        Double c = (n*xy - xs*ys)/ (n*x2 - xs*xs);
        Double a = (ys - c*xs)/n;
        System.out.println("y = a +c*x " +ys+ "=" + a + "+"+c+"*"+xs);
        
        //TODO priamka
        
        sc.stop();
        sc.close();
    }
    
}
