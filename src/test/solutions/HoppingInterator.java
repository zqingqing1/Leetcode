package test.solutions;

import java.util.*;
import java.util.Iterator;

public class HoppingInterator<T> implements Iterator<T> {
	private final Iterator<T> it;
	private final int hops;
	
	public HoppingInterator(Iterator<T> it, int hops){
		this.it=it;
		this.hops = hops;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return it.hasNext();		
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		T val = it.next();
		for(int i=0;i<hops&&it.hasNext();i++){
			it.next();
		}
		return val;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		HoppingInterator<Integer> hi = new HoppingInterator<Integer>(
				list.iterator(), 3);
		System.out.println(hi.next());
		System.out.println(hi.hasNext());
		System.out.println(hi.next());
		System.out.println(hi.hasNext());
	}
	

}
