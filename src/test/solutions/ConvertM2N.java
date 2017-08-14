package test.solutions;

public class ConvertM2N {
	private String convertM2N(String s,int srcBase,int desBase){
		if(srcBase==desBase) return s;
		String digits= "0123456789ABCDEF";
		if(desBase!=10){
			s=convertM2N(s,srcBase,10);
		}else{
			int n=0;
			for(int i=s.length()-1;i>=0;i--){
				n+=digits.indexOf(s.charAt(i))*Math.pow(srcBase,s.length()-i-1);
			}
			return n+"";
		}
		return convertDecimal2N(s,desBase);		
	}
	
	private String convertDecimal2N(String s,int desBase){
		//System.out.println(s);
		int num = Integer.valueOf(s);
		if(num==0)
			return "0";
		if(desBase > 16){
			return "Error Des Base";
		}
		StringBuilder sb = new StringBuilder();
		String digit = "0123456789ABCDEF";
		while(num>0){
			sb.insert(0, digit.charAt(num%desBase));
			num/=desBase;
		}
		return sb.toString();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConvertM2N test = new ConvertM2N();
		String s = "11110";
		
		
		System.out.println(test.convertM2N(s, 2, 8));
		System.out.println(test.convertM2N(s, 2, 9));
		System.out.println(test.convertM2N(s, 2, 10));
		System.out.println(test.convertM2N(s, 2, 16));
		
	}

}
