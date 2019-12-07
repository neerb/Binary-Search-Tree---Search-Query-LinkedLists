package BinarySearchTree;

public class Record
{
	int id;
	String title;
	String author;
	Record next;

	Record(int i, String t, String a, Record r)
	{
		this.id = i;
		this.title = t;
		this.author = a;
		this.next = r;
	}

	public void print()
	{
		System.out.println(this.title);
		System.out.println(this.author);
	}
	
	// Overridden .equals method for a record already containing a matching record.
	boolean equals(Record r)
	{
		return this.title == r.title && this.author == r.author && this.id == r.id;
	}
}
