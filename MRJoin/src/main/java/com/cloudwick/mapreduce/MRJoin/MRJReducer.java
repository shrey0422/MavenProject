package com.cloudwick.mapreduce.MRJoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MRJReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

	protected void reduce(IntWritable key, Iterable<Text> values,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count = 0;
		String dept = "";
		for (Text value : values) {
			if (!value.toString().contains(",")) {
				dept=value.toString();
			}
		}
		
		for (Text value : values) {
			if (value.toString().contains(",")) {
				String [] record= value.toString().split(",");
				context.write(new IntWritable(Integer.parseInt(record[0])), new Text(record[1]+ "  "+dept));
				
			} 

		}
	
		
	}
}