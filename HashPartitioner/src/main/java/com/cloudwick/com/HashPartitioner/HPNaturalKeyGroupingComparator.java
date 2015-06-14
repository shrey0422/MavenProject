package com.cloudwick.com.HashPartitioner;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class HPNaturalKeyGroupingComparator extends WritableComparator{
	protected HPNaturalKeyGroupingComparator() {
		super(HPCompositeGroupKey.class, true);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		HPCompositeGroupKey k1 = (HPCompositeGroupKey)w1;
		HPCompositeGroupKey k2 = (HPCompositeGroupKey)w2;
		
		return k1.getDeptid().compareTo(k2.getDeptid());
	}
}
