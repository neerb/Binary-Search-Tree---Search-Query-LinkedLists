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
			// TODO Instantialize a new Node with keyword k.
			this.keyword = k;
		}

		public void update(Record r)
		{
			// TODO Adds the Record r to the linked list of records. You do not have to
			// check if the record is already in the list.
			// HINT: Add the Record r to the front of your linked list.
					
			if(record == null)
			{
				record = r;
			}
			else
			{
				Record temp = record;
				
				record = r;
				
				record.next = temp;
			}
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
		
		if(currentNode.keyword.compareTo(keyword) > 0)
		{
			currentNode.r = insert(keyword, fd, currentNode.r);
		}
		else if(currentNode.keyword.compareTo(keyword) < 0)
		{
			currentNode.l = insert(keyword, fd, currentNode.l);
		}
		else
			currentNode.update(newRecord);
		
		return currentNode;
	}

	public boolean contains(String keyword)
	{
		// TODO Write a recursive function which returns true if a particular keyword
		// exists in the bst
		
		
		return contains_record(keyword, root);
	}
	
	private boolean contains_record(String keyword, Node currentNode)
	{
		if(currentNode.keyword.compareTo(keyword) > 0 && currentNode.r != null)
		{
			return contains_record(keyword, currentNode.r);
		}
		else if(currentNode.keyword.compareTo(keyword) < 0 && currentNode.l != null)
		{
			return contains_record(keyword, currentNode.l);
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
	
	private Record find_record(String keyword, Node currentNode)
	{
		if(currentNode.keyword.compareTo(keyword) > 0 && currentNode.r != null)
		{
			return find_record(keyword, currentNode.r);
		}
		else if(currentNode.keyword.compareTo(keyword) < 0 && currentNode.l != null)
		{
			return find_record(keyword, currentNode.l);
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

	public void delete(String keyword)
	{
		// TODO Write a recursive function which removes the Node with keyword from the
		// binary search tree.
		// You may not use lazy deletion and if the keyword is not in the bst, the
		// function should do nothing.
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
		else
		{
			//System.out.print("Node is null");
		}
	}
}
