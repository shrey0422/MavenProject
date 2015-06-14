package com.cloudwick.com.HashPartitioner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class HPCompositeGroupKey implements WritableComparable<HPCompositeGroupKey> {
	
	public HPCompositeGroupKey(){
		this.deptid=null;
		this.flag=null;
	}
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	private String deptid;
	private String flag;
	
	public HPCompositeGroupKey(IntWritable intWritable, IntWritable intWritable2) {
		// TODO Auto-generated constructor stub
		this.deptid=intWritable.toString();
		this.flag=intWritable2.toString();
	}

	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, deptid);
		WritableUtils.writeString(out, flag);
	}

	public void readFields(DataInput in) throws IOException {
		this.deptid = WritableUtils.readString(in);
		this.flag = WritableUtils.readString(in);
	}

	public int compareTo(HPCompositeGroupKey pop) {
		if (pop == null)
			return 0;
		int intcnt = deptid.compareTo(pop.deptid);
		return intcnt == 0 ? flag.compareTo(pop.flag) : intcnt;
	}

	@Override
	public String toString() {
		return deptid.toString() + ":" + flag.toString();
	}
}
