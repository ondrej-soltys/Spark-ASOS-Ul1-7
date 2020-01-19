/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author vsa
 */
public class Priemer {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("priemer").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Double> listdate = new ArrayList() {
            {
                add(1.0);
                add(2.0);
                add(3.0);
                add(4.0);
                add(5.0);
                add(6.0);
                add(7.0);
                add(8.0);
                add(9.0);
                add(10.0);

            }
        };
        JavaRDD<Double> rdd = sc.parallelize(listdate);

        System.out.println("priemer je " + priemer(rdd));

        sc.stop();
        sc.close();

    }

    public static Double priemer(JavaRDD<Double> rdd) {
        double x = rdd.reduce((a, b) -> a + b);
        double y = (rdd.count());
        return x / y;
    }

}
