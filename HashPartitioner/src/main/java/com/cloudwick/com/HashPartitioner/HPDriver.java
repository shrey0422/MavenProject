package com.cloudwick.com.HashPartitioner;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;




public class HPDriver extends Configured implements Tool {



	public int run(String[] args) throws Exception {

		if (!(args.length == 2 || args.length == 3)) {
			System.out.printf(
					"Usage: %s [generic options] <input dir> <output dir>\n",
					getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}
		Job job = new Job(getConf());

		job.setJarByClass(HPDriver.class);
		job.setJobName(this.getClass().getName());
		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, HPMapper1.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, HPMapper2.class);
		job.setPartitionerClass(HPTaggedJoiningPartitioner.class);
		job.setGroupingComparatorClass(HPNaturalKeyGroupingComparator.class);
		job.setSortComparatorClass(HPCompositeKeyComparator.class);
		job.setReducerClass(HPReducer.class);

		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		FileInputFormat.setInputPaths(job,new Path(args[0]), new Path(args[1]));
		

		job.setMapOutputKeyClass(HPCompositeGroupKey.class);
		job.setMapOutputValueClass(Text.class);

		

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		if (job.waitForCompletion(true)) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new HPDriver(), args);
		// sLocation = args[2];

		System.exit(exitCode);
	}

}