package test.solutions;

public class IntersectionBetween2Lines {
	static class Line{
		Point1<Double> a;
		Point1<Double> b;
		
		Line(Point1<Double> a,Point1<Double> b){
			this.a=a;
			this.b=b;
		}
		
		boolean samePoint(){
			return a.x==b.x&&a.y==b.y;
		}
		
		boolean vertical(){
			return a.x==b.x&&a.y!=b.y;
		}
		 
		double getSlope(){
			return (b.y-a.y)/(b.x-a.x);
		}
		
		double getInterception(){
			return b.y-getSlope()*b.x;
		}
	}
	
	//assuming a1 before a2, b1 before b2, [a1- a2] before [b1-b2]
	static Point1<Double> intersection(Point1<Double> a1,Point1<Double> a2,Point1<Double> b1,Point1<Double> b2){
		Line l1 =new Line(a1,a2);
		Line l2 = new Line(b1,b2);
		
		if(l1.vertical()&&l2.vertical()){
			if(a1.x == b1.x){
				if(a1.y <= b2.y && a1.y >b1.y){
					return b2;
				}
				if(a2.y <b2.y && a2.y >=b1.y){
					return b1;
				}
			}
		}
		
		if(l1.getSlope()==l2.getSlope()){
			if(l1.getInterception()==l2.getInterception()&&isBetween(a1,b1,a2))
				return b1;
			return null;
		}
		
		double x = (l2.getInterception()-l1.getInterception())/(l1.getSlope()-l2.getSlope());
		double y = x*l1.getSlope()+l1.getInterception();
		
		Point1<Double> inter = new Point1<>(x,y);
		
		if(isBetween(a1,inter,a2)&&isBetween(b1,inter,b2)){
			return inter;
		}
		
		return null;
	}
	private static boolean isBetween(Point1<Double> a1, Point1<Double> inter, Point1<Double> a2) {
		// TODO Auto-generated method stub
		if(inter.x <= a2.x && inter.x>=a1.x &&(inter.y <= a2.y && inter.y>=a1.y) || (inter.y <= a1.y && inter.y>=a2.y))
			return true;
		
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
