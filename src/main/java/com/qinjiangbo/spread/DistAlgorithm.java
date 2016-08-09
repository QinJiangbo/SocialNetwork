package com.qinjiangbo.spread;

import java.io.IOException;
import java.util.*;

import com.qinjiangbo.graph.GraphGen;
import com.qinjiangbo.graph.SNGraph;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import javax.xml.crypto.Data;


/**
 * Created by Richard on 5/22/16.
 */
public class DistAlgorithm {

    public static class Map extends MapReduceBase implements
            Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable influence = new IntWritable();
        private Text node = new Text();

        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
                throws IOException {
            List<String> data = new ArrayList<String>();
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line, "|");
            while (tokenizer.hasMoreTokens()) {
                data.add(tokenizer.nextToken());
            }
            GraphGen graphGen = GraphGen.getInstance();
            graphGen.generateGraph(data);
            SNGraph graph = graphGen.getGraph();
            Spreador spreador = Spreador.getInstance(graph);
            int SCALE = graph.getVertexNum();
            for (int i=0; i<SCALE; i++) {
                String vertex = graph.getVertexByIndex(i);
                node.set(vertex);
                influence.set(spreador.spread(i));
                output.collect(node, influence);
            }
        }
    }

    public static class Reduce extends MapReduceBase implements
            Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter)
                throws IOException {
            int sum = 0, occurrence = 0;
            while (values.hasNext()) {
                sum += values.next().get();
                occurrence++;
            }
            int avg = sum / occurrence;
            output.collect(key, new IntWritable(avg));
        }
    }

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(DistAlgorithm.class);
        conf.setJobName("Calculate AVG Influence");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}
