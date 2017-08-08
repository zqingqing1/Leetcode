package test.solutions;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBlockingQueue<E> {
	private final Queue<E> queue = new LinkedList<>();
	private final int capacity;
	private final AtomicInteger count = new AtomicInteger(0);
	
	public BoundedBlockingQueue(int capacity){
		this.capacity=capacity;	
	}
	
	public int size(){
		return count.get();
	}
	
	public synchronized void add(E e) throws RuntimeException, InterruptedException{
		int oldCount = -1;
		while(count.get()==capacity) wait();
		
		queue.add(e);
		oldCount = count.getAndIncrement();
		if(oldCount==0){
			notifyAll();
		}
	}
	
	public synchronized E remove() throws NoSuchElementException, InterruptedException{
		E e;
		int oldCount = -1;
		while(count.get()==0) wait();
		
		e = queue.remove();
		oldCount = count.getAndDecrement();
		if(oldCount == this.capacity)
			notifyAll();
		return e;
	}
	
	public E peek(){
		if(count.get()==0) return null;
		synchronized(this){
			return queue.peek();
		}
	}
	
	public int evalRPN(String[] tokens) throws Exception{
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            if(tokens[i].equals("+")){
                int sum = stack.pop() + stack.pop();
                stack.push(sum);
            }else if(tokens[i].equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b-a);
            }else if(tokens[i].equals("*")){
                int sum = stack.pop() * stack.pop();
                stack.push(sum);
            }else if(tokens[i].equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                try{
                		int val = b/a;
                		stack.push(val);
                }catch(ArithmeticException e){
                		System.err.println(e);
                }
            }else{
	            	try{
	            		int val = Integer.parseInt(tokens[i]);
	                stack.push(val);
	            	}catch(NumberFormatException e){
	            		System.err.println(e);
	            	}
            }
        }
        return stack.pop();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BoundedBlockingQueue<String> queue = new BoundedBlockingQueue<String>(10);

		
	}

}
