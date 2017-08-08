package test.solutions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval implements Intervals{
	//nested class
	public class Interval implements Comparable<Interval>{
		public int start,end;
		public Interval(int x,int y){
			start=x;
			end = y;
		}
		
		public int compareTo(Interval that){
			if(this.start > that.start)
				return 1;
			else if(this.start < that.start)
				return -1;
			else 
				return 0;
		}
		
	}
	//
	List<Interval> list = new LinkedList<>();

	public void addInterval(int from, int to) {
		// TODO Auto-generated method stub
		list.add(new Interval(from,to));
	}

	@Override
	public int getTotalCoverageLength() {
		// TODO Auto-generated method stub
		Collections.sort(list);
		int total = 0;
		Interval last = new Interval(0,0);
		for(Interval cur: list){
			if(cur.start>last.end){
				total+=cur.end-cur.start;
				last = cur;
			}else{
				if(cur.end>last.end){
					total+=cur.end - last.end;
					last = cur;
				}
				
			}
			
		}
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeInterval test = new MergeInterval();
		test.addInterval(1, 2);
		test.addInterval(2, 3);
		test.addInterval(0, 5);
		test.addInterval(7, 9);
		System.out.println(test.getTotalCoverageLength());
		
	}
}
