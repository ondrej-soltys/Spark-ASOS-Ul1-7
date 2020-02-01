package asos;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SucetApp {

    public static double sucet(JavaSparkContext sc, String tf) {

        JavaRDD<String> rdd = sc.textFile(tf);        
        
        Double sucet = rdd.map(s -> {
            Double d = 0.0;
            try {
                d = Double.parseDouble(s);
            } catch (Exception ex) {
                System.out.println("Syntakticka chyba");;
            }
            return d;
        }).reduce((a, b) -> a + b);
        
        return sucet;
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("spark-words-app");
        if (conf.get("spark.master", null) == null) {
            conf.setMaster("local[2]");
        }
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        System.out.println("" + sucet(sc, "src/main/resources/data1.txt"));
        
        sc.close();
        
    }
}
