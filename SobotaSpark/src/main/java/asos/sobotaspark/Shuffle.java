/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sobotaspark;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 *
 * @author vsa
 */
public class Shuffle {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("shuffle").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("/home/vsa/NetBeansProjects/SobotaSpark/src/main/java/data/data1.txt");
        JavaRDD<String> words = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator());
        JavaPairRDD<String, Integer> pairs = words.mapToPair(x -> new Tuple2(x, 1));
        
        JavaPairRDD<String, Integer> pocetnosti = pairs.reduceByKey((a,b) -> a+b);
        
        pocetnosti.collectAsMap().forEach((K, V) -> System.out.println(" " +K+ " " +V));
        
        
        sc.stop();
        sc.close();
        
    }
}
