/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul3;

import java.util.Arrays;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 *
 * @author vsa
 */
public class Tuple {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Tuple").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaRDD<String> file = sc.textFile("/home/vsa/NetBeansProjects/SparkUl3/src/main/java/asos/sparkul3/*.txt");
        JavaRDD<String> words = file.flatMap(x -> Arrays.asList(x.split(" ")).iterator());
        JavaRDD<String> filtred = words.filter(x -> x.matches("^[a-zA-Z0-9]*$")).map(x -> x.toLowerCase());
        JavaPairRDD<String, Integer> tuple = filtred.mapToPair(x -> new Tuple2(x, 1));

        filtred.countByValue().forEach((K, x) -> System.out.println(K + "="+x));        
        tuple.countByValue().forEach((K, x) -> System.out.println(K._1 + "="+x));
        
        sc.stop();
        sc.close();
    }
}
