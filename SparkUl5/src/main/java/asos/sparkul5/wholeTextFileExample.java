/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul5;


import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.spark_project.guava.collect.Lists;
import scala.Tuple2;

/**
 *
 * @author vsa
 */
public class wholeTextFileExample {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wkole text file example").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaPairRDD<String, String> rddFilePair = sc.wholeTextFiles("/home/vsa/NetBeansProjects/SparkUl5/src/main/java/asos/sparkul5/*.txt");
        
        rddFilePair.foreach(x -> System.out.println("File: " + x._1 + " word: " + x._2));
        
        JavaPairRDD<String, String> wordFilePair = rddFilePair.flatMapValues(x -> Arrays.asList(x.split(" "))).filter(x-> x._2.matches("^[a-zA-Z0-9]*$"));
        JavaPairRDD<String, String> wordFilePairKos = rddFilePair.flatMapValues(s -> Lists.newArrayList(s.split("\\s+")));
        
//        wordFilePair.foreach(x-> System.out.println(x._1 + " " + x._2));
//        //to iste 
//        wordFilePairKos.foreach(x-> System.out.println(x._1 + " " + x._2));
        
        wordFilePair.collect().forEach(x -> System.out.println(x._1 +" " +  x._2));
        wordFilePairKos.collectAsMap().forEach((K, V) -> System.out.println(K +" " + V));
        
        wordFilePair.countByKey().forEach((K, V) -> System.out.println("Pocet slov " +V+ " v " + K));
//        
        wordFilePairKos.mapValues(x -> x.toLowerCase()).distinct().countByKey().forEach((K, V) -> System.out.println("Pocet roznych slov "+V+" v " +K));
//        
        wordFilePair.mapValues(x -> x.toLowerCase()).distinct().mapToPair(x -> new Tuple2(x._2, x._1)).countByKey().forEach((K, V) -> System.out.println("Slovo "+ K +" pocet: "+ V));
        
        
        sc.stop();
        sc.close();
    }
   
}
