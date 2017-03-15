/** JN
 * A test class that tests the Address book.
 * @author Jimmy Nguyen
 * @version 7/18/2016
 */
public class TestCases {

	/** JN
	 * The main program of the application.
	 * @param args CommandLine arguments.
	 */
	public static void main(String[] args) {
		// JN Creates a new Address book using a hash table.
		AddressBookInterface addressBook = new HashAddressBook();
		System.out.println("Hash bucket Address book\n");
		testCases(addressBook); // JN Tests the cases.
		
		// JN Creates a new Address book using the binary search tree.
		addressBook = new BSTAddressBook();
		System.out.println("\nBST Address Book\n");
		testCases(addressBook); // JN Tests the cases.

	}
	
	/** JN
	 * Tests the addressbook's insert, delete and look up.
	 * @param addressBook The address book type.
	 */
	private static void testCases(AddressBookInterface addressBook){
		// JN Tests the insert functions.
		System.out.println("Inserts: Bob Smith, Jane Williams, Mohammed al-Salam, Pat Jones, Billy Kidd, "
				+ "H. Houdini, Jack Jones, Jill Jones, John Doe and Jane Doe.\n");
		addressBook.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
		addressBook.insert("Jane", "Williams", "555-235-1112", "jw@something.com");
		addressBook.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
		addressBook.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
		addressBook.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
		addressBook.insert("H.", "Houdini", "555-235-1117", "houdini@noplace.com");
		addressBook.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com");
		addressBook.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com");
		addressBook.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
		addressBook.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");
		
		// JN Tests the lookup and deletes.
		System.out.println("Looking up Pat Jones...");
		addressBook.lookUp("Pat", "Jones");
		System.out.println("Looking up Billy Kidd...");
		addressBook.lookUp("Billy", "Kidd");
		System.out.println("Deleting John Doe...");
		addressBook.delete("John", "Doe");
		
		// JN Tests inserts again.
		System.out.println("Inserting: Test Case, Nadezha Kanachekhovskaya, Jo Wu, Milliard Fillmore, Bob vanDyke, and Upside Down.\n");
		addressBook.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
		addressBook.insert("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
		addressBook.insert("Jo", "Wu", "555-235-1123", "wu@h.com");
		addressBook.insert("Milliard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us");
		addressBook.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com");
		addressBook.insert("Upside", "Down", "555-235-1125", "upsidedown@rightsideup.com");
		
		// JN Tests the lookups and deletes again.
		System.out.println("Looking up Jack Jones...");
		addressBook.lookUp("Jack", "Jones");
		System.out.println("Looking up John Doe...");
		addressBook.lookUp("John", "Doe");
		System.out.println("Looking up Nadezhda Kanachekhovskaya...");
		addressBook.lookUp("Nadezhda", "Kanachekhovskaya");
		System.out.println("Deleting Jill Jones...");
		addressBook.delete("Jill", "Jones");
		System.out.println("Deleting John Doe...");
		addressBook.delete("John", "Doe");
		System.out.println("Looking up Jill Jones...");
		addressBook.lookUp("Jill", "Jones");
		System.out.println("Looking up John Doe...");
		addressBook.lookUp("John", "Doe");
	}

}
