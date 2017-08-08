package test.solutions;

import java.util.*;

public class SumCombination {
	public static List<List<Integer>> getSum(int n){
		List<List<Integer>> res = new LinkedList<>();
		helper(res,new LinkedList<>(),n,1);
		return res;
	}
	
	public static void helper(List<List<Integer>> res,List<Integer> tmp,int n,int start){
		if(n<0) return;
		if(n==0){
			if(tmp.size()>1)
				res.add(new LinkedList<>(tmp));
			return;
		}
		for(int i=start;i<=n;i++){
			//System.out.println(i);
			tmp.add(i);
			helper(res,tmp,n-i,i);
			tmp.remove(tmp.size()-1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> res = getSum(4);
		for(List<Integer> inside:res){
			for(Integer item:inside){
				System.out.print(item+" ");
			}
			System.out.println();
		}
		
	}

}
