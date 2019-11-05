package BST;

import java.util.Random;

public class TreeMapPT implements SortedSetPT {
	MapEntryPT root;

	@Override
	public void get(int key) {
		String value = getValue(key, root);

		System.out.println("Found key value: " + value);
	}

	String getValue(int key, MapEntryPT root) {
		if (root != null) {
			if (root.key == key)
				return root.value;
		}

		if (root.left != null) {
			if (root.left.compareTo(new MapEntryPT(key)) == 1)
				return root.left.value;
			else
				return getValue(key, root.left);
		}

		if (root.right != null) {
			if (root.right.compareTo(new MapEntryPT(key)) == 1)
				return root.right.value;
			else
				return getValue(key, root.right);
		}

		return null;
	}

	@Override
	public void put(int key, String value) {
		root = newEntry(root, key, value);
	}

	MapEntryPT newEntry(MapEntryPT root, int key, String value) {
		if (root == null) {
			root = new MapEntryPT(key, value);
			return root;
		}

		if (key < root.key)
			root.left = newEntry(root.left, key, value);
		else if (key > root.key)
			root.right = newEntry(root.right, key, value);

		return root;
	}

	static String randomizedValueEntry() {
		char[] chars = new char[] { '!', '@', '#', '$', '%', '^', '&', '*' };
		String newStr = "";
		Random rand = new Random();

		for (int i = 0; i < rand.nextInt(10) + 5; i++)
			newStr += chars[rand.nextInt(chars.length)];

		return newStr;
	}

	@Override
	public void removeKey(int key) {
		keyRemoval(root, key);
	}

	void keyRemoval(MapEntryPT root, int key) {
		if (root != null) {
			if (root.key == key)
				root.key = -1;
		}

		if (root.left != null) {
			if (root.left.compareTo(new MapEntryPT(key)) == 1)
				root.left.key = -1;
			else
				keyRemoval(root.left, key);
		}

		if (root.right != null) {
			if (root.right.compareTo(new MapEntryPT(key)) == 1)
				root.right.key = -1;
			else
				keyRemoval(root.right, key);
		}
	}

	@Override
	public void print() {
		printSorted(root);
	}

	void printSorted(MapEntryPT root) {
		if (root != null) {
			printSorted(root.left);
			if (root.key != -1)
				System.out.println(root.key + " with value " + root.value);
			printSorted(root.right);
		}
	}
}