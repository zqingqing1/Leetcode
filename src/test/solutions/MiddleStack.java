package test.solutions;

public class MiddleStack {
	
	private class Node{
		int val;
		Node pre;
		Node next;
		private Node(int val,Node pre,Node next){
			this.val=val;
			this.pre=pre;
			this.next=next;
		}
	}
	int count = 0;
	Node midPointer = null;
	Node head = null;
	Node tail = null;
	
	public void push(int val){
		if(count==0){
			head = tail = midPointer=new Node(val,null,null);
			count++;
		}else{
			Node node = new Node(val,tail,null);
			tail.next = node;
			tail = node;
			count++;
			if(count%2==1)
				midPointer=midPointer.next;
		}
			
	}
	
	public int pop(){
		if(count == 0)
			return -1;
		if(count%2==1)
			midPointer=midPointer.pre;
		int val = tail.val;
		Node node = tail.pre;
		if(node != null){
			node.next = null;
			count--;
		}
		else{
			head = tail = null;
			count=0;
		}
		return val;
	}
	
	public int findMiddle(){
		if(midPointer!=null)
			return midPointer.val;
		else{
			return -1;
		}
	}
	
	public void deleteMiddle(){
		if(midPointer!=null){
			if(count%2==0){
				if(count==2){
					head = tail = midPointer.next;
					head.pre = null;
					midPointer = head;
				}else{
					Node tmp =midPointer.next;
					midPointer.pre.next=midPointer.next;
					midPointer.next.pre = midPointer.pre;
					midPointer = tmp;
					midPointer.next =null;
					midPointer.pre = null;
				}
				count--;
			}else{
				if(count==1){
					head =tail = midPointer = null;	
				}else{
					midPointer.pre.next=midPointer.next;
					midPointer.next.pre = midPointer.pre;
					midPointer = midPointer.pre;
					midPointer.next =null;
					midPointer.pre = null;
				}
				count--;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiddleStack stack = new MiddleStack();
		stack.push(11);

		
		System.out.println("Item poped is "+ stack.pop());
		System.out.println("Middle Item is "+ stack.findMiddle());
		stack.deleteMiddle();
		System.out.println("Middle Item is "+ stack.findMiddle());
	}

}
