package BinarySearchTree;

class bst
{
	Node root;

	public class Node
	{
		// These attributes of the Node class will not be sufficient for those
		// attempting the AVL extra credit.
		// You are free to add extra attributes as you see fit, but do not remove
		// attributes given as it will mess with help code.
		String keyword;
		Record record;
		int size;
		Node l;
		Node r;

		private Node(String k)
		{
			// Instantialize a new Node with keyword k.
			this.keyword = k;
			l = null;
			r = null;
		}

		public void update(Record r)
		{
			// Adds the Record r to the linked list of records. You do not have to
			// check if the record is already in the list (I figured I'd do it anyways).
			// HINT: Add the Record r to the front of your linked list.
								
			if(record == null)
			{
				record = r;
				record.next = null;
			}
			else
			{
				// If the record already exists in the node, do not add it to the list
				// (I know this wasn't required but I wanted to do it anyways, I hope that's okay)
				if(!containsRecord(record, r))
				{					
					Record temp = record;
					
					record = r;
					
					record.next = temp;
				}
				else
				{
					System.out.println("Error: cannot add already existing record.");
				}
			}
		}
		
		// Checks record list to see if the inserting record is already in the list
		boolean containsRecord(Record list, Record newRecord)
		{
			Record current = list;
			
			while(current != null)
			{
				if(current.equals(newRecord))
				{
					return true;
				}
				else
				{
					current = current.next;
				}
			}
			
			return false;
		}
	}

	public bst()
	{
		this.root = null;
	}

	public void insert(String keyword, FileData fd)
	{
		Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
		// Write a recursive insertion that adds recordToAdd to the list of records
		// for the node associated
		// with keyword. If there is no node, this code should add the node.
		
		if(root == null)
		{
			root = new Node(keyword);
			root.record = recordToAdd;
		}
		else
		{
			insert(keyword, fd, root);
		}
	}
	
	// Recursive method to insert a new keyword/node into the BST
	private Node insert(String keyword, FileData fd, Node currentNode)
	{
		Node newNode = new Node(keyword);
		Record newRecord = new Record(fd.id, fd.title, fd.author, null);
		
		if(currentNode == null)
		{
			newNode.update(newRecord);
			currentNode = newNode;
			
			return currentNode;
		}
		
		// If the keyword is less than the current node, traverse to the left
		if(currentNode.keyword.compareTo(keyword) > 0)
		{
			currentNode.l = insert(keyword, fd, currentNode.l);
		}
		// If the keyword is greater than the currentNode, traverse to the right
		else if(currentNode.keyword.compareTo(keyword) < 0)
		{
			currentNode.r = insert(keyword, fd, currentNode.r);
		}
		// When the correct position is found, update the LinkedList of records
		else
			currentNode.update(newRecord);
		
		return currentNode;
	}

	public boolean contains(String keyword)
	{
		// Write a recursive function which returns true if a particular keyword
		// exists in the bst
		
		return contains_node(keyword, root);
	}
	
	// Recursive method that returns true if the specified node exists in the tree, false otherwise
	private boolean contains_node(String keyword, Node currentNode)
	{
		if(currentNode.keyword.compareTo(keyword) > 0 && currentNode.l != null)
		{
			return contains_node(keyword, currentNode.l);
		}
		else if(currentNode.keyword.compareTo(keyword) < 0 && currentNode.r != null)
		{
			return contains_node(keyword, currentNode.r);
		}
		else if(currentNode.keyword.equals(keyword))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Record get_records(String keyword)
	{
		// Returns the first record for a particular keyword. This record will link
		// to other records
		// If the keyword is not in the bst, it should return null.
		
		return find_record(keyword, root);
	}
	
	// Recursive method to find and return record
	private Record find_record(String keyword, Node currentNode)
	{
		if(currentNode != null)
		{
			if(currentNode.keyword.compareTo(keyword) > 0 && currentNode.l != null)
			{
				return find_record(keyword, currentNode.l);
			}
			else if(currentNode.keyword.compareTo(keyword) < 0 && currentNode.r != null)
			{
				return find_record(keyword, currentNode.r);
			}
			else if(currentNode.keyword.equals(keyword))
			{
				return currentNode.record;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	public void delete(String keyword)
	{
		// Write a recursive function which removes the Node with keyword from the
		// binary search tree.
		// You may not use lazy deletion and if the keyword is not in the bst, the
		// function should do nothing.
				
		if(find_record(keyword, root) != null)
		{
			root = delete(keyword, root);
			System.out.println("Node with keyword \"" + keyword + "\" deleted.");
		}
		else
		{
			System.out.println("Error: attempted to delete a node that does not exist in the tree.");
		}
	}
	
	// Recursive method to delete node with specified keyword
	private Node delete(String keyword, Node currentNode)
	{	
		// Base case for empty tree
		if(currentNode == null)
			return currentNode;
		
		if(currentNode.keyword.compareTo(keyword) > 0)
		{
			currentNode.l = delete(keyword, currentNode.l);
		}
		else if(currentNode.keyword.compareTo(keyword) < 0)
		{
			currentNode.r = delete(keyword, currentNode.r);
		}
		else
		{
			if(currentNode.l == null)
			{
				return currentNode.r;
			}
			else if(currentNode.r == null)
			{
				return currentNode.l;
			}
			
			currentNode.keyword = minimumValue(currentNode.r);
			
			currentNode.r = delete(currentNode.keyword, currentNode.r);
		}
		
		return currentNode;
	}
	
	// Find minimum value in subtree
	private String minimumValue(Node root)
	{
		String min = root.keyword;
		
		// Base case
		if(root.l != null)
		{
			return minimumValue(root.l);
		}
		else
			return min;
	}

	public void print()
	{
		print(root);
	}

	private void print(Node t)
	{
		if (t != null)
		{
			print(t.l);
			System.out.println(t.keyword);
			Record r = t.record;
			
			while (r != null)
			{
				System.out.printf("\t%s - %s\n", r.title, r.author);
				r = r.next;
			}
			
			print(t.r);
		}
	}
}
