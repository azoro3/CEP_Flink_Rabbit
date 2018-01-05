import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class testSpark {
    private static final Receiver RECEIVER = new Receiver();
    private static final Sender SENDER = new Sender();
    private static final String HOST="localhost";
    private static final int[] PORTS={5672,5673,5674};
    private static RandomValue rd=new RandomValue();

    public static void main(String[] args) throws IOException, TimeoutException {


        SENDER.send("Hello world!",HOST,PORTS[rd.getValue()]);
        SparkConf sparkConf = new SparkConf().setAppName("JavaRabbitMQConsumer").setMaster("local[2]");
        List<String> res = RECEIVER.receive(HOST,PORTS[rd.getValue()]);
        JavaSparkContext jssc = new JavaSparkContext(sparkConf);
        JavaRDD<String> test = jssc.parallelize(res);
        long res2 = test.count();
        System.out.printf("message count: %d", res2);
        test.foreach((VoidFunction<String>) s -> {
            System.out.printf("message :%s\n", s);
            SENDER.send("Hi too !",HOST,PORTS[rd.getValue()]);
        });
        RECEIVER.receive(HOST,PORTS[rd.getValue()]);
    }
}
