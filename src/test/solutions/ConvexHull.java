package test.solutions;

import java.util.LinkedList;
import java.util.List;


public class ConvexHull {
	public static int orientation(Point1 a, Point1 b,Point1 c){
		int val = (b.y-a.y)*(c.x-b.x)-(b.x-a.x)*(c.y-b.y);
		return val>0?1:val==0?0:2; // clockwise, counter,collinear
	}
	
	public static void convexhull(Point1[] ps){
		int n=ps.length;
		if(n<3) return;
		List<Point1> hull = new LinkedList<>();
		
		int leftmost = 0;
		for(int i=1;i<n;i++){
			if(ps[i].x<ps[leftmost].x)
				leftmost=i;
		}
		
		int a = leftmost, b;
		do{
			hull.add(ps[a]);
			b = (a+1)%n;
			for(int i=0;i<n;i++){
				if(orientation(ps[a],ps[i],ps[b])==2)
					b =i;
			}
			
			a = b;
		}while(a != leftmost);
		
		for(int i=0;i<hull.size();i++){
			System.out.println("("+hull.get(i).x+","+hull.get(i).y+")");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point1[] ps = {new Point1(0,3),new Point1(2,2),new Point1(2,1),new Point1(3,0)};
		convexhull(ps);
		
	}

}
