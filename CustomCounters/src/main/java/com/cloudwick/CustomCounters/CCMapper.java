package com.cloudwick.CustomCounters;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CCMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	public static enum CustomCounters {
		Info,
		Debug,
		Error
		
		}
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Text out=new Text();
		String line = value.toString();
		if (line.contains("Info")){
			context.getCounter(CustomCounters.Info).increment(1);
		}else if(line.contains("Debug")){
			context.getCounter(CustomCounters.Debug).increment(1);
		}else if (line.contains("Error")){
			context.getCounter(CustomCounters.Error).increment(1);
		}
		out.set("success");

		context.write(out,out);
	}
}
