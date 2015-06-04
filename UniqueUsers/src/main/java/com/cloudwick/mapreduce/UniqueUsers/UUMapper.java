package com.cloudwick.mapreduce.UniqueUsers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UUMapper  extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	protected void map(LongWritable key, Text value,
	org.apache.hadoop.mapreduce.Mapper.Context context)
	throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	String line = value.toString();
	String [] word =line.split(",");
	context.write(new Text(word[1]), new Text(word[0]));
	}

	}
	
