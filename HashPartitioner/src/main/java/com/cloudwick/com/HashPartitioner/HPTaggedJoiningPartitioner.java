package com.cloudwick.com.HashPartitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class HPTaggedJoiningPartitioner  extends Partitioner<HPCompositeGroupKey,Text> {

    @Override
    public int getPartition(HPCompositeGroupKey taggedKey, Text value, int numPartitions) {
    	
    	String deptid=taggedKey.getDeptid();
    	String flag=taggedKey.getFlag();
    //	int numReduceTasks =10;
        //return 0;
        //this is done to avoid performing mod with 0
        if(numPartitions == 0)
            return 0;

        //if the age is <20, assign partition 0
        if(deptid =="101"){               
            return 1%numPartitions;
        }
        //else if the age is between 20 and 50, assign partition 1
        if(deptid =="102"){
           
            return 2%numPartitions ;
        }
        //otherwise assign partition 2
        else
            return 3%numPartitions ;
    }
}