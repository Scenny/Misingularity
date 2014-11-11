package util;

public class Tester {

	public static void main(String[] args) {
		System.out.println("Start testing Treap...");
		SSDTreap<Integer> t = new SSDTreap<Integer>();
		for (int i = 0; i < 100; ++i) {
			t.root = t.insert(t.root, Integer.valueOf(i));
		}
		for (int i = 0; i < 100; ++i) 
		if (!t.isEmpty()) {
			Integer tmp = t.findMax();
			System.out.println(tmp);
			t.root = t.remove(t.root, tmp);
		} else {
			System.out.println("Treap is Empty!");
			break;
		}
	}
}
