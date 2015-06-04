package com.cloudwick.mapreduce.SequenceMaps;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SMMapper2 extends Mapper<Text, Text, Text, Text> {
	@Override
	protected void map(Text key, Text value,
			Mapper<Text, Text, Text, Text>.Context context) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub

		if (Integer.parseInt(value.toString()) > 1000
				&& Integer.parseInt(value.toString()) < 5000) {
			context.write(key, new Text(value));
		}

	}
}
