/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asos.sparkul6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.DataTypes;
/**
 *
 * @author vsa
 */
public class SetOperations {

    

    public static StructType createSchema(List<String> tableColumns){

        List<StructField> fields  = new ArrayList<StructField>();
        for(String column : tableColumns){         

                fields.add(DataTypes.createStructField(column, DataTypes.StringType, true));            

        }
        return DataTypes.createStructType(fields);
    }
    
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("set operatons").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> file1 = sc.textFile("/home/vsa/NetBeansProjects/SparkUl6/src/main/java/asos/sparkul6/data1.txt")
                .flatMap(x -> Arrays.asList(x.split(" ")).iterator()).map(x -> x.toLowerCase()).distinct();
        JavaRDD<String> file2 = sc.textFile("/home/vsa/NetBeansProjects/SparkUl6/src/main/java/asos/sparkul6/data2.txt")
                .flatMap(x -> Arrays.asList(x.split(" ")).iterator()).map(x -> x.toLowerCase()).distinct();
        JavaRDD<String> file3 = sc.textFile("/home/vsa/NetBeansProjects/SparkUl6/src/main/java/asos/sparkul6/data3.txt")
                .flatMap(x -> Arrays.asList(x.split(" ")).iterator()).map(x -> x.toLowerCase()).distinct();
        
        JavaRDD<String> unionAll = file1.union(file2).union(file3);
        unionAll.collect().forEach(x -> System.out.println(x.toString()));
        
        JavaRDD<String> intersectionAll = file1.intersection(file2).intersection(file3);
        intersectionAll.collect().forEach(x -> System.out.println(x.toString()));
        
        JavaRDD<String> subtractAll = file1.subtract(file2).subtract(file3);
        subtractAll.collect().forEach(x -> System.out.println(x.toString()));
        
        
        JavaRDD<Row> rowFile1 = file1.map(x -> RowFactory.create(file1));
        SQLContext sqlc = new SQLContext(sc);
        StructType schema = createSchema(Arrays.asList("column1"));
        Dataset<Row> df = sqlc.createDataFrame(rowFile1, schema);
        
        JavaRDD<Row> rowFile2 = file1.map(x -> RowFactory.create(file2));
        
        StructType schema2 = createSchema(Arrays.asList("column1"));
        Dataset<Row> df2 = sqlc.createDataFrame(rowFile2, schema2);
        
        
        RDD<Row> joinAll = df.join(df2).rdd();
//        joinAll.collect();
        System.out.println(joinAll.collect());
        
        sc.stop();
        sc.close();

    }
}
