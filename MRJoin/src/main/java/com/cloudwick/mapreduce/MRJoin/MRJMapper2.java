package com.cloudwick.mapreduce.MRJoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MRJMapper2 extends Mapper<LongWritable, Text, IntWritable, Text> {
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String line = value.toString();
		
			String[] split = line.split(",");
			

			context.write(new IntWritable(Integer.parseInt(split[0])), new Text(split[1]));
		}

	}

