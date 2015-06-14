package com.cloudwick.com.HashPartitioner;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class HPCompositeKeyComparator extends WritableComparator{
	protected HPCompositeKeyComparator() {
		super(HPCompositeGroupKey.class, true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		HPCompositeGroupKey k1 = (HPCompositeGroupKey)w1;
		HPCompositeGroupKey k2 = (HPCompositeGroupKey)w2;
		
		int result = k1.getDeptid().compareTo(k2.getDeptid());
		if(0 == result) {
			result = k1.getFlag().compareTo(k2.getFlag());
		}
		return result;
	}
}
