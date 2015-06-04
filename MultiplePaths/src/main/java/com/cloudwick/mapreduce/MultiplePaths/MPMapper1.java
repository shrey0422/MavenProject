package com.cloudwick.mapreduce.MultiplePaths;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MPMapper1 extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String line = value.toString();
		if (line.contains("CA")) {
			String[] split = line.split(",");

			context.write(new Text(split[0] + "        " + split[2]), new Text(
					split[1]));
		}

	}
}
