package com.cloudwick.mapreduce.FilterLocation;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FLDriver extends Configured implements Tool {

	static String sLocation;

	public int run(String[] args) throws Exception {

		if (!(args.length == 2 || args.length == 3)) {
			System.out.printf(
					"Usage: %s [generic options] <input dir> <output dir>\n",
					getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}
		Job job = null;
		if (args.length == 3) {
			Configuration conf = new Configuration();
			conf.set("Location", args[2]);
			job = new Job(conf);
		} else {
			job = new Job(getConf());
		}
		job.setJarByClass(FLDriver.class);
		job.setJobName(this.getClass().getName());
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		if (args.length == 2) {
			job.setMapperClass(FLMapper1.class);
		} else {
			// sLocation=args[2];
			job.setMapperClass(FLMapper2.class);
		}

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setNumReduceTasks(0);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		if (job.waitForCompletion(true)) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new FLDriver(), args);
		sLocation = args[2];

		System.exit(exitCode);
	}

}
