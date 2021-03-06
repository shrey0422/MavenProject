package com.cloudwick.com.HashPartitioner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HPMapper1 extends
		Mapper<LongWritable, Text, HPCompositeGroupKey, Text> {
	
	@Override
	protected void setup(org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		super.setup(context);
	}
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String[] word = line.split(",");
		HPCompositeGroupKey compkey = new HPCompositeGroupKey(new IntWritable(
				Integer.parseInt(word[2])), new IntWritable(1));
		context.write(compkey, new Text(word[0] + " " + word[1]));
	}

}
