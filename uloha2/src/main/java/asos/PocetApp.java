package asos;

import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class PocetApp {

    public static Map<String,Long> pocet(JavaSparkContext sc, String dir) {
        
        // IMPLEMENTOVAT
        throw new java.lang.UnsupportedOperationException("Treba implementovat!"); 
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("spark-words-app");
        if (conf.get("spark.master", null) == null) {
            conf.setMaster("local[2]");
        }
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        Map<String, Long> rmap = pocet(sc, "src/main/resources");
        
        sc.close();
        
        // Doplnte si vypis pre kontrolu vysledku
    }
}
