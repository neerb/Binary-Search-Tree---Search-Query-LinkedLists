package BST;

public class TesterProgram 
{
	public static void main(String[] args) 
	{
		TreeMapPT tree = new TreeMapPT();

		// Inserting roots and leaf nodes of binary tree
		tree.put(5, "Five");

		tree.put(10, "Ten");
		
		tree.put(30, "Thirty");
		
		tree.put(6, "Six");
		
		
		tree.get(5);
		
		// Prints tree after removal of nodes with keys above
		tree.print();
	}
}
