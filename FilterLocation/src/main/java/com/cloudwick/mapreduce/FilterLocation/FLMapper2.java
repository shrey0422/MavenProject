package com.cloudwick.mapreduce.FilterLocation;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Mapper;

public class FLMapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {
	//public void configure(JobConf conf){
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String line = value.toString();
		Configuration conf = context.getConfiguration();
		
		String location = conf.get("Location");
		//System.out.println(location);
		if (line.contains(location)) {
			String[] split = line.split(",");

			context.write(new Text(split[0] + "        " + split[2]), new Text(
					split[1]));
		}

	}
}
