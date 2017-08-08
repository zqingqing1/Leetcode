package test.solutions;

import java.util.*;

public class StringCombination {
	private  List<Integer> getIntinbracket(String s){
		String[] str = s.split(",");
		List<Integer> res = new LinkedList<>();
		
		for(String item:str){
			res.add(Integer.parseInt(item));
		}
		return res;
	}
	
	private List<String> resolve(String s){
		String[] str = s.split("\\.");
		List<String> res = new LinkedList<>();
		helper(str,res,0,new StringBuilder());
		return res;	
	}
	
	private void helper(String[] str,List<String> res,int depth,StringBuilder tmp){
		if(depth == str.length){
			res.add(new String(tmp.toString()));
			return;
		}
		String val = str[depth];
		int open = val.indexOf('[');
		int close = val.indexOf(']');
		if(open==-1&&close==-1){
			tmp.append(".").append(val);
			helper(str,res,depth+1,tmp);
			return;
		}
		String pre = val.substring(0, open);
		List<Integer> list = getIntinbracket(val.substring(open+1,close));
		
		for(int i=0;i<list.size();i++){
			int size = tmp.length();
			if(depth==0)
				tmp.append(pre).append(list.get(i));
			else
				tmp.append(".").append(pre).append(list.get(i));
			helper(str,res,depth+1,tmp);
			tmp.setLength(size);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringCombination sc = new StringCombination();
		String s = "app[1,2].corp[3,4,5].com";
		//System.out.println(s);
		List<String> res = sc.resolve(s);
		
		for(String st:res)
			System.out.println(st);
		
	}

}
