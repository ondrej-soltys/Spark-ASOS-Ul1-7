/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkprojektskuska;

import com.google.common.collect.Lists;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 *
 * @author vsa
 */
public class SparkMain {
    
   
    
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("zadanie").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaPairRDD<String, String> suborSlovo = sc.wholeTextFiles("/home/vsa/NetBeansProjects/SparkProjektSkuska/src/main/java/asos/subory");
//        Map<String, Long> suborPocet = suborSlovo.flatMapValues(s -> Lists.newArrayList(s.split("\\s+"))).mapValues(x -> x.toLowerCase()).distinct().map(x -> x._2).countByValue();
//        JavaPairRDD<String, String> suborPocet2 = suborSlovo.flatMapValues(s -> Lists.newArrayList(s.split("\\s+"))).mapValues(x -> x.toLowerCase()).distinct().map(x -> new Tuple2(x._2, x._1));
        Map<String, Integer> suborPocet;
        suborPocet = suborSlovo.flatMapValues(s -> Lists.newArrayList(s.split("\\s+"))).mapValues(x -> x.toLowerCase()).distinct()
                .mapToPair(x -> new Tuple2(x._2, 1))
                .reduceByKey((a,b) -> (int) a+ (int)b)
                .collectAsMap();

        
        suborPocet.forEach((K,V) -> System.out.println("Key " +K+ " value " +V + "\n"));
//        suborPocet2.forEach((K,V) -> System.out.println("Key " +K+ " value " +V + "\n"));
//        suborPocet.foreach(x -> System.out.println("Key " +x._1+ " value " +x._2 + "\n"));
//        suborPocet2.foreach(x -> System.out.println("Key " +x._1+ " value " +x._2 + "\n"));
        
        sc.stop();
        sc.close();
    }
}
