package test.solutions;

import java.util.*;

public class nearestKPoint {

	
	static Point1 center;
	static PriorityQueue<Point1> maxHeap=new PriorityQueue<>(new Comparator<Point1>(){
		public int compare(Point1 p1, Point1 p2){
			int a = getDiffSquare(p2,center);
			int b = getDiffSquare(p1,center);
			return a-b;
		}
	});
	

	public static int getDiffSquare(Point1 p1, Point1 p2){
		int diffX = Math.abs(p1.x-p2.x);
		int diffY = Math.abs(p1.y-p2.y);
		int res=diffX*diffX+diffY*diffY;
		//System.out.println(res);
		return res;
	}

//	public static List<Point1> nearestK(List<Point1> list,Point1 p, int k){
//		center = p;
//		for(int i=0;i<list.size();i++){
//			int diff = getDiffSquare(list.get(i),p);
//			System.out.println("size:"+maxHeap.size());
//			if(maxHeap.size()<k)
//				maxHeap.add(list.get(i));
//			else{
//				if(diff<getDiffSquare(maxHeap.peek(),p)){
//					maxHeap.poll();
//					maxHeap.add(list.get(i));
//				}
//			}
//		}
//		List<Point1> res= new LinkedList<>();
//		for(Point1 point:maxHeap){
//			res.add(point);
//		}
//		return res;
//	}
	public static int partition(List<Point1> list,Point1 p, int low,int high){
		int i = low, j = high+1;
		int pivot = getDiffSquare(list.get(low),p);
		while(true){
			while(getDiffSquare(list.get(++i),p)<pivot){
				if(i==high) break;
			}
			while(getDiffSquare(list.get(--j),p)>pivot){
				if(j==low) break;
			}
			
			if(i>=j)
				break;
			//swap
			Point1 tmp =list.get(i);
			list.set(i,list.get(j));
			list.set(j,tmp);
		}
		//swap pivot
		Point1 tmp =list.get(j);
		list.set(j,list.get(low));
		list.set(low,tmp);
		
		return j;
		
	}
	public static List<Point1> nearestK(List<Point1> list,Point1 p, int k){
		List<Point1> res=new LinkedList<>();
		int lo=0, high = list.size()-1;
		
		while(lo<=high){
			int index = partition(list,p,lo,high);
			if(index==k){
				for(int i=0;i<k;i++){
					res.add(list.get(i));
				}
				return res;
			}	
			if(index>k){
				high = index-1;
			}else{
				lo = index+1;
			}
		}
		return res;
	}
	
	
	public static void main(String[] args){
		List<Point1> list = new LinkedList<>();
		list.add(new Point1(2,0));
		list.add(new Point1(3,4));
		list.add(new Point1(-2,0));
		list.add(new Point1(2,2));
		
		List<Point1> res = new LinkedList<>();
		
		res=nearestK(list,new Point1(0,0),2);
		
		for(Point1 p:res){
			System.out.println("4:"+p.x+" "+p.y);
		}
		
//		while(maxHeap.size()!=0){
//			Point1 tmp =maxHeap.poll();
//			System.out.println("main:"+tmp.x+" "+tmp.y);
//		}
		
	}
}
