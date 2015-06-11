public class LinkedList {

	static Node head;

	private static class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
			next = null;
		}
	}

	public static void insert(int val) {
		if (head == null) {
			head = new Node(val);
		}
		else {
			Node cur = head;
			while(cur.next != null) {
				cur = cur.next;
			}
			cur.next = new Node(val);
		}
	}

	public static void reverse() {
		Node prev = null;
		Node cur = head;

		while(cur != null) {
			Node next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
	}

	public static void reverseDLL() {
		
		Node cur = head;

		while (cur != null) {
			Node next = cur.next;
			cur.next = cur.prev; 
			cur.prev = next;
			cur = next;
		}
		head = cur;
	}

	public static void printNode() {
		Node cur = head;
		while(cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 5, 3, 2, 4};
		for(int i : arr) {
			insert(i);
		}
		printNode();
		System.out.println("");

		reverse();

		printNode();
		System.out.println("");
	}

}