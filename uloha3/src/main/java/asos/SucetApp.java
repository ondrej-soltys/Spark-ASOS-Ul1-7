package asos;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SucetApp {

    public static double sucet(JavaSparkContext sc, String tf) {

        LogAccumulator acc = new LogAccumulator();
        
        JavaRDD<String> rdd = sc.textFile(tf);        
        sc.sc().register(acc,"myAcc");
        Double sucet = rdd.map(s -> {
            Double d = 0.0;
            try {
                d = Double.parseDouble(s);
            } catch (Exception ex) {
                acc.add("Syntakticka chyba " + s.toString());
                System.out.println("Syntakticka chyba");
            }
            return d;
        }).reduce((a, b) -> a + b);
        
        acc.value().forEach(x -> System.out.println(x));
        return sucet;
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("spark-words-app");
        if (conf.get("spark.master", null) == null) {
            conf.setMaster("local[2]");
        }
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        System.out.println("" + sucet(sc, "src/main/resources/data2.txt"));
        
        sc.close();
        
    }
}
