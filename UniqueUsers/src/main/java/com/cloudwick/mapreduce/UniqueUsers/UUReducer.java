package com.cloudwick.mapreduce.UniqueUsers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UUReducer extends Reducer<Text, Text, Text, IntWritable> {

protected void reduce(Text key, Iterable<Text> values,
Reducer<Text, Text, Text, IntWritable>.Context context)
throws IOException, InterruptedException {
// TODO Auto-generated method stub
int count=0;
Set<String> obj= new HashSet<String>();
for (Text value: values) {
	obj.add(value.toString());
	}
count=obj.size();
context.write(key, new IntWritable(count));
}
}
