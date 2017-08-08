package test.solutions;

public class MinMax3Array {
	private static int[] find(int[] A,int[] B,int[] C){
		int[] res=new int[3];
		int min=Integer.MAX_VALUE;
		
		int i = 0, j = 0, k = 0;
		while(true){
			if(i>=A.length&&j>=B.length&&k>=C.length)
				break;
			int a = i>=A.length?A[A.length-1]:A[i];
			int b = j>=B.length?B[B.length-1]:B[j];
			int c = k>=C.length?C[C.length-1]:C[k];
			int val = Math.max(Math.max(Math.abs(a-b),Math.abs(b-c)),Math.abs(a-c));
			if(val<min){
				res[0]=a;res[1]=b;res[2]=c;
				min = val;
			}
			
			if(i>=A.length)
				a = Integer.MAX_VALUE;
			if(j>=B.length)
				b = Integer.MAX_VALUE;
			if(k>=C.length)
				c = Integer.MAX_VALUE;
			int m=Math.min(Math.min(a,b),c);
			if(i<A.length&&m==A[i]){
				i++;
			}else if(j<B.length&&m==B[j]){
				j++;
			}else if(k<C.length&&m==C[k]){
				k++;
			}
			
		}
		
		return res;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A=new int[]{20,24,100};
		int[] B=new int[]{2,19,22,79,800};
		int[] C=new int[]{10,12,23,24,119};
		int[] res = find(A,B,C);
		for(int i:res)
			System.out.println(i);
	}

}
