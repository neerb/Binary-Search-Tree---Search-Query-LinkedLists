package BinarySearchTree;

import java.io.*;

class FileData
{
	/**
	 * Class: FileData Contains the content of a record found in the input file.
	 * Each FileData object contains exactly one record. An object of this type will
	 * be returned by readNextRecord(..) function on successful read. Fields: id :
	 * ID of the record title : contains the title of the paper author: contains the
	 * author of the paper keywords is an array of all keywords related to that
	 * paper.
	 */

	int id;
	String title;
	String author;
	String keywords[];

	/* Constructor */
	FileData(int id, String title, String author, int keywordCount)
	{
		this.id = id;
		this.title = title;
		this.author = author;
		keywords = new String[keywordCount];
		
		for (int i = 0; i < keywords.length; i++)
		{
			keywords[i] = null;
		}
	}

	/*
	 * Returns true if the keyword was successfully added Keyword addition might
	 * fail if it does not meet the original limit. This method adds a single
	 * keyword to the keywords array in the end. This method will be invoked by the
	 * getNextRecord() function at the time of building an object of this type
	 */
	boolean addKeyword(String keyword)
	{
		for (int i = 0; i < keywords.length; i++)
		{
			if (keywords[i] == null)
			{
				keywords[i] = keyword;
				return true;
			}
		}
		return false;
	}

}

class test
{

	BufferedReader b;
	bst a;

	/*
	 * Returns the next data record (a whole record object) in the data input file.
	 * Returns null if there is not such record. Hence a null indicates end of file
	 * or some error Error message will be displayed on the screen. DO NOT CHANGE
	 * THIS FUNCTION!
	 */
	public FileData readNextRecord()
	{
		if (b == null)
		{
			System.out.println("Error: You must open the file first.");
			return null;
		}
		else
		{
			FileData readData;
			try
			{
				String data = b.readLine();
				if (data == null)
					return null;
				int readNo = Integer.parseInt(data);
				readData = new FileData(readNo, b.readLine(), b.readLine(), Integer.parseInt(b.readLine()));
				for (int i = 0; i < readData.keywords.length; i++)
				{
					readData.addKeyword(b.readLine());
				}
				String space = b.readLine();
				if ((space != null) && (!space.trim().equals("")))
				{
					System.out.println("Error in file format");
					return null;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error Number Expected! ");
				return null;
			}
			catch (Exception e)
			{
				System.out.println("Fatal Error: " + e);
				return null;
			}
			return readData;
		}
	}

	public test(String filename)
	{
		try
		{

			this.a = new bst();
			this.b = new BufferedReader(new FileReader(filename));

			/* READS DATAFILE.TXT INTO DATASTRUCTURE */

			FileData fd;
			while ((fd = this.readNextRecord()) != null)
			{
				for (int i = 0; i < fd.keywords.length; i++)
				{
					a.insert(fd.keywords[i], fd);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (b != null)
					b.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
	
	// This method prints all the records in the list of records passed into the parameter
	public static void printRecord(Record r)
	{
		if(r == null)
		{
			System.out.println("Nothing was found with that keyword.");
		}
		
		Record next = r;
		
		System.out.println("List of records found with searched keyword: ");
		
		while(next != null)
		{
			System.out.println("\t" + next.title + " - " + next.author);
			next = next.next;
		}
	}

	public static void main(String[] args)
	{
		/// Testing file BST
		
		test T = new test("datafile.txt"); // THIS WILL CREATE YOUR BST AND FILL IT WITH THE INFORMATION FROM THE
											// DATAFILE
		T.a.print();
				
		T.a.get_records("database").print();

		T.a.print(); // Prints titles of all elements in the bst sorted by keyword.
		T.a.delete("medical");
		T.a.delete("learning");
		T.a.delete("concepts");

		T.a.print(); // Prints bst after the 3 deletions
		///
		
		System.out.println("\n--- TESTING FOR SELF-MADE BST BELOW ---\n");
		
		/// Testing self-made BST
		bst bstTest = new bst();
		
		/// Attempt to delete from empty tree
		bstTest.delete("fiction");
		
		// Create and insert test data into tree
		FileData scienceData1 = new FileData(3632, "A brief history of time", "Stephen Hawking", 1);
		FileData scienceData2 = new FileData(5402, "Reality Is Not What It Seems", "Carlo Rovelli", 1);
		FileData scienceData3 = new FileData(42, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1);

		FileData philosophyData = new FileData(5555, "Aristotle's Metaphysics", "Aristotle", 1);
		
		FileData religiousData = new FileData(7777, "The Bible", "Moses", 1);

		bstTest.insert("science", scienceData1);
		bstTest.insert("science", scienceData2);
		bstTest.insert("science", scienceData3);

		bstTest.insert("philosophy", philosophyData);
		
		bstTest.insert("religious", religiousData);
		
		bstTest.print();
		
		// Delete "religious" keyword
		bstTest.delete("religious");
		
		// Check if keywords exist
		System.out.println("Does this tree contain the keyword \"science\"?" + (bstTest.contains("science") == true ? " Yes, it does!" : " No, it does not."));
		System.out.println("Does this tree contain the keyword \"fiction\"?" + (bstTest.contains("fiction") == true ? " Yes, it does!" : " No, it does not."));

		// Print BST
		bstTest.print();
		
		// Print searched node list
		printRecord(bstTest.get_records("science"));
	}
}
