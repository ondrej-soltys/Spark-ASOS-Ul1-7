/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul2;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author vsa
 */
public class TextFile {

    public static void main(String[] args) {
        SparkConf spconf = new SparkConf().setAppName("text file").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(spconf);
        //overenie a funguje
        JavaRDD<String> discFile = sc.textFile("/home/vsa/NetBeansProjects/SparkUl2/src/main/java/asos/sparkul2/*.txt");
        // pocet datovych clenov v sade
        System.out.println("Pocet riadkov: " + discFile.count());

        //1. first vracia 1. clen datovej sady
        System.out.println("Prvy riadok: " + discFile.first());

        JavaRDD<String> words = discFile.flatMap(x -> Arrays.asList(x.split(" ")).iterator());

        //uloha4
        JavaRDD<String> words2 = discFile.flatMap(x -> new Function().doStuff(x).iterator());
        
        words.filter(f -> f.toLowerCase().contains("in")).collect().forEach(x -> System.out.println(x));
        words.filter(f -> f.toLowerCase().contains("in")).sample(true, 0.9).foreach(x -> System.out.println(x));
        //uloha4
        words2.filter(f -> f.toLowerCase().contains("in")).sample(false, 0.5).foreach(x -> System.out.println(x));

        System.out.println("Najdlhsi riadok: " + discFile.map(x -> x.length()).reduce((a,b) -> a < b ? b : a));
        
        sc.stop();
        sc.close();
    }
}
