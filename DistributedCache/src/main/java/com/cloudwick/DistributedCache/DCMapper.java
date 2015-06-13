package com.cloudwick.DistributedCache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DCMapper extends Mapper<LongWritable, Text, Text, Text> {
	private BufferedReader brReader;
	private static HashMap<String, String> DepartmentMap = new HashMap<String, String>();
	private String strDeptName = "";

	@Override
	protected void setup(org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context
				.getConfiguration());

		for (Path eachPath : cacheFilesLocal) {
			if (eachPath.getName().toString().trim().equals("DeptJoin.txt")) {
				// context.getCounter(MYCOUNTER.FILE_EXISTS).increment(1);
				loadDepartmentsHashMap(eachPath, context);
			}
		}

	}

	private void loadDepartmentsHashMap(Path filePath, Context context)
			throws IOException {

		String strLineRead = "";

		try {
			brReader = new BufferedReader(new FileReader(filePath.toString()));

			// Read each line, split and load to HashMap
			while ((strLineRead = brReader.readLine()) != null) {
				String deptFieldArray[] = strLineRead.split(",");
				DepartmentMap.put(deptFieldArray[0].trim(),
						deptFieldArray[1].trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// context.getCounter(MYCOUNTER.FILE_NOT_FOUND).increment(1);
			// } catch (IOException e) {
			// context.getCounter(MYCOUNTER.SOME_OTHER_ERROR).increment(1);
			// e.printStackTrace();
		} finally {
			if (brReader != null) {
				brReader.close();
			}
		}
	}

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String[] word = line.split(",");
		try {
			strDeptName = DepartmentMap.get(word[2].toString());
		} finally {
			strDeptName = ((strDeptName.equals(null) || strDeptName.equals("")) ? "NOT-FOUND"
					: strDeptName);
		}

		context.write(new Text(word[0]), new Text(word[1] + "     "
				+ strDeptName));
	}

	@Override
	protected void cleanup(org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}

}
