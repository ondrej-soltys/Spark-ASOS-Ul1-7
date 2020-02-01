/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparklearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class SparkDemo {

    public static void main(String[] args) {
//        List<Ucet> ls = new ArrayList<>();
//        ls.add(new Ucet("U001", 100));
//        ls.add(new Ucet("U002", 200));
//        ls.add(new Ucet("U003", 300));
//        ls.add(new Ucet("U004", 400));
//        
//        SparkConf conf = new SparkConf().setAppName("meno aplikacie");
//
//        if (conf.get("spark.master", null) == null) {
//            conf.setMaster("local[2]");
//        }
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        JavaRDD<Ucet> rdd = sc.parallelize(ls);                 // ls.stream()
//        
//        double d = rdd.map(u->u.getStav()).reduce((a,b)->a+b);  // map(u->u.getStav()).reduce(0.0, (a,b)->a+b)
//        System.out.println("" + d);
//        sc.stop();
//        sc.close();
    SparkConf conf = new SparkConf().setAppName("random app");
    if(conf.get("spark.master", null) == null){
        conf.setMaster("local[2]");
    }
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> rdd = sc.textFile("/home/vsa/NetBeansProjects/sparkLearning/src/main/java/asos/sparklearning/text.csv");
    // pocet datovych clenov v sade
        System.out.println("Pocet riadkov: " + rdd.count() +"\n");

        //1. first vracia 1. clen datovej sady
        System.out.println("Prvy riadok: " + rdd.first()+"\n");
        
        //2. vytvori List vsetkych clenov RDD
        List<String> lines = rdd.collect();
        for (String s: lines) {
            System.out.println(s);
        }
            // 3. filter
        JavaRDD<String> filtered = rdd.filter(f->f.contains("older"));
        
        for (String s: filtered.collect())
            System.out.println("" + s);  
        
//        Integer lineSize = rdd.map(x -> x.length()).reduce((a,b) -> a > b ? a : b);

        System.out.println(rdd.map(x -> x.length()).reduce((a,b) -> (a > b ? a : b)));
        // rozdeli riadky na slova - vystupná sada obsahuje slová
        JavaRDD<String> words = rdd.flatMap(s -> Arrays.asList(s.split(",")).iterator());
        
        for (String w : words.collect()) {
            System.out.println(w);
        }
        JavaPairRDD<String, Integer> pairs = words.mapToPair(s -> new Tuple2(s, 1));
//        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b).filter( k -> ("older".equals(k._1)));
//        pairs.countByValue();
        System.out.println(pairs.countByValue());
//        Map<String, Integer> r = counts.collectAsMap();
//        for (String k: r.keySet())
//            System.out.println(k + ":" + r.get(k));
//        
        sc.stop();
        sc.close();
    }

}
