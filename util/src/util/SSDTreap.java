package util;


public class SSDTreap<V extends Comparable<V>> {
    public static String idxSuffix = ".idx";
    public static String datSuffix = ".dat";
	
	public static class TreapNode<V extends Comparable<V>> {

		public TreapNode<V> leftChild, rightChild;
		public V key;
		public int priority;
		
		public TreapNode(V v) {
			key = v;
			// Pick a random value as priority in Range [0, 10000)
			priority = (int)(Math.random()*10000); 
			
			leftChild = null;
			rightChild = null;
		}
	}
	
	TreapNode<V> root;
    
    public SSDTreap() {
    	root = null;
    }
	
	public TreapNode<V> leftRotate(TreapNode<V> t) { // now t is x's left child
		TreapNode<V> x = t.rightChild;
		t.rightChild = x.leftChild;
		x.leftChild = t;
		return x;
	}
	
	public TreapNode<V> rightRotate(TreapNode<V> t) { // now t is x's right child
		TreapNode<V> x = t.leftChild;
		t.leftChild = x.rightChild;
		x.rightChild = t;
		return x;
	}
	
	public TreapNode<V> insert(TreapNode<V> cur, V key) {
		if (cur == null) {
			cur = new TreapNode<V>(key);
			return cur;
		}
		
		if (key.compareTo(cur.key) < 0) {
			cur.leftChild = insert(cur.leftChild, key);
			if (cur.leftChild.priority > cur.priority) // Make a Max-heap according to priority
				cur = rightRotate(cur);
		} else if (key.compareTo(cur.key) > 0) {
			cur.rightChild = insert(cur.rightChild, key);
			if (cur.rightChild.priority > cur.priority) 
				cur = leftRotate(cur);
		} else cur.priority = (int)(Math.random()*10000);
		
		return cur;
	}
	
	public TreapNode<V> remove(TreapNode<V> cur, V key) {
		if (cur == null) return null;
		
		if (key.compareTo(cur.key) < 0) {
			cur.leftChild = remove(cur.leftChild, key);
		} else if (key.compareTo(cur.key) > 0) {
			cur.rightChild = remove(cur.rightChild, key);
		} else {
			if (cur.leftChild == null && cur.rightChild == null) {
				return null;
			} else if (cur.leftChild == null || 
					(cur.rightChild != null && cur.rightChild.priority > cur.leftChild.priority)) {
				cur = leftRotate(cur);
				cur = remove(cur, key);
			} else {
				cur = rightRotate(cur);
				cur = remove(cur, key);
			}
		}
		
		return cur;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void removeAll() {
		root = null;
	}
	
	public V findMax() {
		TreapNode<V> t = root;
		for (; t.rightChild != null; ) t = t.rightChild;
		return t.key;
	}
}


