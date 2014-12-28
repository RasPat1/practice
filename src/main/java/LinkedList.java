package gcj1A;

public class LinkedList {
	public Node head;
	public Node tail;
	public Node current;
	public int length;
	
	public LinkedList(Node head) {
		this.head = head;
		this.tail = head.end();
		this.current = head;
		this.length = head.getLength();
	}
	public void add(Node newNode) {
		this.tail.next = newNode;
		this.tail = newNode;
		this.length++;
	}
	public void deleteCurrent() {
		Node prev = head;
		while (prev.hasNext() &&
				prev.next != this.current) {
			prev = prev.next;
		}
		if (prev.next == this.current) {
			prev.next = this.current.next;
		}
	}
	public void insertCurrent(Node newNode) {
		newNode.next = this.current.next;
		this.current.next = newNode;
	}
	
	
	public class Node {
		int data;
		Node next;
		
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		public Node end() {
			Node end = this;
			
			while(end.hasNext()) {
				end = end.next;
			}
			return end;
		}
		public boolean hasNext() {
			if (this.next != null) {
				return true;
			}
			return false;
		}
		public int getLength() {
			int len = 0;
			Node current = this;
			while (current.hasNext()) {
				len++;
				current = current.next;
			}
			return len;
		}
	}
}
