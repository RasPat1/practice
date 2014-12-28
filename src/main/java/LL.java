package gcj1A;

public class LL {
	private Node head;
	private Node tail;
	private Node currentPosition;
	private int length;
	
	public void reset() {
		
	}
	public class Node {
		int data;
		Node next;
		
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		public String toString() {
			return String.valueOf("value: " + this.data);
		}	
	}
	
	public LL(Node head) {
		this.head = head;
		this.length = 0;
//		this.currentPosition = 0;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public boolean search(int target) {
//		while(this.head.next != null) {
//			if (this.head.data == target) {
//				return true;
//			} else {
//				return this.next.search(target);
//			}
//		}
		return false;
	}
	public void delete() {
		
	}
	public void add(Node next) {
		
	}
	public void insert() {
		
	}
	public static void main(String args[]) {
//		LL l1 = new LL(5, null);
//		System.out.println(l1);
	}
}
