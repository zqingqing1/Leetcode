package test.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet<T> {
	 Map<T,List<Integer>> map; // (cat: [0,1])
	 Map<Integer,T> index; // (0,cat) (1,cat)
	    /** Initialize your data structure here. */
	 public RandomizedSet() {
	    index=new HashMap<>();
	    map=new HashMap<>();
	 }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	 public void insert(T val) {
		 int size = index.size();
		 index.put(size, val);
		 if(!map.containsKey(val))
			 map.put(val, new ArrayList<>());
		 map.get(val).add(size); 
	 }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	 public boolean remove(T val) {
	    if(!map.containsKey(val)){
	        return false;
	    }
	    else{
	    		
	        int i=map.get(val).get(0); // get the first one 
	        removeWithIndex(i);
	        return true;
	    }
	 }
	 
	 public void removeWithIndex(int i){
		 int size = index.size();
		 System.out.println("size: "+size);
		 T cur = index.get(i);
		 T lastele = index.get(size-1);
		 index.put(i, lastele);
		 List<Integer> l1 = map.get(cur);
		 int k;
		 for(k=0;k<l1.size();k++)
			 if(l1.get(k)==i)
				 break;
		 l1.remove(k);
		 if(l1.size()==0){
			 map.remove(cur);
		 }
		 List<Integer> l2=map.get(lastele);
		 int j;
		 for(j=0;j<l2.size();j++){
			 if(l2.get(j)==size-1){
				 break;
			 }		 
		 }
		 map.get(lastele).set(j, i);
		 index.remove(size-1);
	 }
	    
	    /** Get a random element from the set. */
	public T getRandom() {
	    Random rm=new Random();
	    return index.get(rm.nextInt(index.size()));
	}
	
	public void randomRemove(){
		T item = getRandom();
		remove(item);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 RandomizedSet<String> obj = new RandomizedSet<>();
		 obj.insert("Cat");
		 obj.insert("Dog");
		 obj.insert("Cow");
		 obj.insert("Pig");
		 obj.insert("Dog");
		 obj.insert("Pig");
		 boolean param_5 = obj.remove("Pig");
		 System.out.println(obj.toString());
		 
		 String param_6 = obj.getRandom();
		 System.out.println(param_6);
		 obj.randomRemove();
		 System.out.println(obj.toString());
	}

	@Override
	public String toString() {
		return "RandomizedSet [map=" + map + ", index=" + index + "]";
	}

}
