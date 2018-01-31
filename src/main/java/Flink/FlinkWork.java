package Flink;


import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Arthur
 */
public class FlinkWork {

    public void wordCount(String data) {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // get input data
        DataSet<String> text = env.fromElements(data);

        DataSet<Tuple2<String, Integer>> counts
                = // split up the lines in pairs (2-tuples) containing: (word,1)
                text.flatMap(new ComaSpliter())
                        // group by the tuple field "0" and sum up tuple field "1"
                        .groupBy(0)
                        .sum(1);

        try {
            // execute and print result
            counts.print();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
