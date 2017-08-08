package test.solutions;

public class CubicRoot {
	private final double error;
	public CubicRoot(double e){
		error = e;
	}
	private double cubicRoot(double n){
		double start = 0, end = n;
		while(true){
			double mid = (start+end)/2.0;
			if(Math.abs(n-mid*mid*mid)<error){
				return mid;
			}
			if(mid*mid*mid<n)
				start = mid;
			else{
				end = mid;
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CubicRoot cr = new CubicRoot(0.0000001);
		System.out.println(cr.cubicRoot(8));
	}

}
