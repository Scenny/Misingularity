package util;

import java.io.*;
import java.util.*;

public class CopyOfSSDTreap {
	
	public static class TreapNode implements Comparable<TreapNode> {

		public TreapNode leftChild, rightChild;
		public int key;
		public int priority;
		
		public TreapNode(int v) {
			key = v;
			// Pick a random value as priority in Range [0, 10000)
			priority = (int)(Math.random()*10000); 
			
			leftChild = null;
			rightChild = null;
		}

		@Override
		public int compareTo(TreapNode o) {
			return key - o.key;
		}
	}
	
	TreapNode root;
    
    public CopyOfSSDTreap() {
    	root = null;
    }
	
	public TreapNode leftRotate(TreapNode t) { // now t is x's left child
		TreapNode x = t.rightChild;
		t.rightChild = x.leftChild;
		x.leftChild = t;
		return x;
	}
	
	public TreapNode rightRotate(TreapNode t) { // now t is x's right child
		TreapNode x = t.leftChild;
		t.leftChild = x.rightChild;
		x.rightChild = t;
		return x;
	}
	
	public TreapNode insert(TreapNode cur, int key) {
		if (cur == null) {
			cur = new TreapNode(key);
			return cur;
		}
		
		if (key < cur.key) {
			cur.leftChild = insert(cur.leftChild, key);
			if (cur.leftChild.priority > cur.priority) // Make a Max-heap according to priority
				cur = rightRotate(cur);
		} else if (key > cur.key) {
			cur.rightChild = insert(cur.rightChild, key);
			if (cur.rightChild.priority > cur.priority) 
				cur = leftRotate(cur);
		} else cur.priority = (int)(Math.random()*10000);
		
		return cur;
	}
	
	public TreapNode remove(TreapNode cur, int key) {
		if (cur == null) return null;
		
		if (key < cur.key) {
			cur.leftChild = remove(cur.leftChild, key);
		} else if (key > cur.key) {
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
	
	public int findMax() {
		TreapNode t = root;
		for (; t.rightChild != null; ) t = t.rightChild;
		return t.key;
	}
	
	public TreapNode buildTreap() {
        // Root start with null 
        for (int i = 0; i < 100; ++i) {
        	root = insert(root, i);
        }
        return root;
    }
}


