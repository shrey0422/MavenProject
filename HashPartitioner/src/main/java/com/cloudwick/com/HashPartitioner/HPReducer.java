package com.cloudwick.com.HashPartitioner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HPReducer extends Reducer<HPCompositeGroupKey, Text, IntWritable, Text> {

	protected void reduce(HPCompositeGroupKey key, Iterable<Text> values,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count = 0;
	String dept="";
	int flag=0;
		for (Text value : values) {
			if (flag==0) {
				dept=value.toString();
				flag=1;
			}
			else {
				String [] record= value.toString().split(" ");
				context.write(new IntWritable(Integer.parseInt(record[0])), new Text(record[1]+"        "+dept));
		}
		
		
		}
		
	}
}