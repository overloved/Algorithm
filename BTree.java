/**
* in-order, pre-order, post-order traversal (recurtion, non-recurstion)
* level-order traversal
* LCA, LCA with parent node
* BFS, DFS
* isBST()
* BST insert()
* minHeight(), maxHeight(); getHeigth();
*/

import java.util.*;

public class BTree {

	static Node root;

	private static class Node {
		int val;
		Node left;
		Node right;
		Node parent;

		public Node(int val) {
			this.val = val;
			left = null;
			right = null;
			parent = null;
		}
	}

	public static void insert(int val) {
		root = insert(root, val);
	}

	public static Node insert(Node root, int val) {
		if (root == null) root = new Node(val);
		if (root.val == val) return root;
		if (root.val > val) {
			root.left = insert(root.left, val);
		} else {
			root.right = insert(root.right, val);	
		}
		return root;
	}

	public static void inorder() {
		inorder(root);
	}

	public static void inorder(Node root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}

	public static void inorderNonrecursion() {
		inorderNonrecursion(root);
	}

	public static void inorderNonrecursion(Node root) {
		if (root == null) return;
		Stack<Node> stk = new Stack<Node>();

		Node cur = root;
		
		while(!stk.isEmpty() || cur != null) {

			if (cur != null) {
				stk.push(cur);
				cur = cur.left;
			} else {
				Node temp = stk.pop();
				System.out.print(temp.val + " ");
				cur = temp.right;
			}	
		}
	}

	public static void preorder() {
		preorder(root);
	}

	public static void preorder(Node root) {
		if (root == null) return;
		System.out.print(root.val + " ");
		preorder(root.left);
		preorder(root.right);
	}

	public static void preorderNonrecursion() {
		preorderNonrecursion(root);
	}

	public static void preorderNonrecursion(Node root) {
		if (root == null) return;
		Stack<Node> stk = new Stack<Node>();
		Node cur = root;
		
		stk.push(cur);

		while(!stk.isEmpty()) {
			Node temp = stk.pop();
			System.out.print(temp.val + " ");
			if (temp.right != null) {
				stk.push(temp.right);
			}
			if (temp.left != null) {
				stk.push(temp.left);
			}
		}
	}

	public static void postorderNonrecursion() {
		postorderNonrecursion(root);
	}

	public static void postorderNonrecursion(Node root) {
		if (root == null) return;
		Stack<Node> s = new Stack<Node>();
		Stack<Node> p = new Stack<Node>();
		Node cur = root;
		s.push(cur);

		while(!s.isEmpty()) {
			Node temp = s.pop();
			p.push(temp);
			if (temp.left != null) {
				s.push(temp.left);
			}

			if (temp.right != null) {
				s.push(temp.right);
			}
		}

		while (!p.isEmpty()) {
			System.out.print(p.pop().val + " ");
		}
	}

	public static void levelOrderTraversal() {
		List<Node> res = levelOrderTraversal(root);
		for(int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i).val + " ");
		}
	}

	public static List<Node> levelOrderTraversal(Node root) {
		List<Node> res = new ArrayList<Node>();

		if (root == null) return res;

		Stack<Node> curLevel = new Stack<Node>();
		Stack<Node> nextLevel = new Stack<Node>();

		curLevel.push(root);
		while(!curLevel.isEmpty()) {
			
			Node cur = curLevel.pop();
			res.add(cur);

			if (cur.left != null) {
				nextLevel.push(cur.left);
			}

			if (cur.right != null) {
				nextLevel.push(cur.right);
			}
			
			if (curLevel.isEmpty()) {
				curLevel = nextLevel;
				nextLevel = new Stack<Node>();
			}
		}
		return res;
	}

	public static void bfs() {
		Node cur = root;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(cur);

		while(!queue.isEmpty()) {
			Node node = queue.remove();
			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	public static void dfs() {
		Node cur = root;
		Stack<Node> stk = new Stack<Node>();

		stk.push(cur);
		while(!stk.isEmpty()) {
			Node node = stk.pop();
			System.out.print(node.val + " ");
			if (node.right != null) {
				stk.push(node.right);
			}
			if (node.left != null) {
				stk.push(node.left);
			}
		}
	}

	public static boolean isBST() {
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		return isBST(root, min, max);
	}

	public static boolean isBST(Node root, int min, int max) {
		if (root == null) return true;
		if (root.val < min || root.val > max) return false;
		if (!isBST(root.left, min, root.val) || !isBST(root.right, root.val, max)) return false;
		return true;
	}

	public static int minHeight() {
		return minHeight(root);
	}

	public static int minHeight(Node root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		if (root.left == null) return minHeight(root.right) + 1;
		if (root.right == null) return minHeight(root.left) + 1;
		return Math.min(minHeight(root.left), minHeight(root.right)) + 1;
	}

	public static int maxHeight() {
		return maxHeight(root);
	}

	public static int maxHeight(Node root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		if (root.left == null) return maxHeight(root.right) + 1;
		if (root.right == null) return maxHeight(root.left) + 1;
		return Math.max(maxHeight(root.left), maxHeight(root.right)) + 1;
	}

	public static int lca(int a, int b) {
		return lca(root, new Node(a), new Node(b)).val;
	}

	public static Node lca(Node root, Node p, Node q) {
		if (root == null) return null;
		if (p == root || q == root) return root;
		Node left = lca(root.left, p, q);
		Node right = lca(root.right, p, q);
		if (left != null && right != null) return root;
		return left != null ? left : right;
	}

	public static Node LCA2(Node p, Node q) {
		int h1 = getHeight(p);
		int h2 = getHeight(q);

		int diff = Math.abs(p - q);
		
		for (int h = 0; h < diff; h++) {
			if (h1 > h2) {
				p = p.parent();
			} else {
				q = q.parent();
			}
		}

		while(p != null && q != null) {
			if (p == q) return p;
			p = p.parent;
			q = q.parent;
		}
		return null;
	}

	public static int getHeight(Node node) {
		int height = 0;
		while(node != null) {
			height++;
			node = node.parent;
		}
		return height;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 4, 5, 2, 3};
		for (int i : arr) {
			insert(i);
		}

		System.out.print("In-order with recursion: ");
		inorder();
		System.out.println();

		System.out.print("In-order without recursion: ");
		inorderNonrecursion();
		System.out.println();

		System.out.print("Pre-order with recursion: ");
		preorder();
		System.out.println();

		System.out.print("Pre-order without recursion: ");
		preorderNonrecursion();
		System.out.println();

		System.out.print("Post-order without recursion: ");
		postorderNonrecursion();
		System.out.println();

		System.out.print("Level order traversal: ");
		levelOrderTraversal();
		System.out.println();

		System.out.print("DFS: ");
		dfs();
		System.out.println();

		System.out.print("Is it a BST? ");
		System.out.println(isBST());

		System.out.print("Min height is: ");
		System.out.println(minHeight());

		System.out.print("Max height is: ");
		System.out.println(maxHeight());

		System.out.print("LCA for 2 and 5 is: ");
		System.out.println(lca(2, 5));
	}
}