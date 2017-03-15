/** JN
 * The hash bucket implementation of the Address Book data type. Uses a Hash bucket to store data and look up.
 * @author Jimmy Nguyen
 * @version 7/18/2016
 */
public class HashAddressBook implements AddressBookInterface{
	
	private HashNode[] addressBook; // JN The field containing the array of hashed nodes.
	
	/** JN
	 * The constructor for objects in the HashAddressBook.
	 */
	public HashAddressBook(){
		addressBook = new HashNode[13]; // JN Initializes the array.
	}
	
	/** JN
	 * Adds a new hash node with the person's information as a value and their hashed name as a key.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 * @param phone The phone # of the person.
	 * @param email The email of the person.
	 */
	public void insert(String firstName, String lastName, String phone, String email){
		// JN Calls the capsName function to format the name correctly.
		firstName = capsName(firstName);
		lastName = capsName(lastName);
		
		HashNode newNode = new HashNode(firstName, lastName, phone, email);// JN Creates a new node with the given data
		addNode(computeHash(firstName, lastName), newNode); // JN Computes the Hash and then adds the data into the array.
	}
	
	/** JN
	 * Removes the node and value from the array using the first and last name.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 */
	public void delete(String firstName, String lastName){
		// JN Calls the capsName function to format the name correctly.
		firstName = capsName(firstName);
		lastName = capsName(lastName);
		
		// Finds if the node is in the tree.
		HashNode returnNode = contains(firstName, lastName);
		if(returnNode != null){
			// JN Calls the private remove method that returns the node right before the one set to be removed.
			HashNode prev = remove(firstName, lastName);
			if (prev != null){
				System.out.println("Deleted " + prev.nextNode().getName() + ".\n");
				prev.setNext(prev.nextNode().nextNode()); // JN Removes the node if it is between two nodes.
			}
			else {
				System.out.println("Deleted " + returnNode.getName() + ".\n");
				// JN Deletes the first node and sets the second as the first.
				addressBook[computeHash(firstName, lastName)] = returnNode.nextNode();
			}
		}
		else {
			System.out.println("Address book does not contain " + capsName(firstName) + " " + capsName(lastName) + ".\n");
		}
	}
	
	/** JN
	 * Searches to see if the address book contains the person.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 */
	public void lookUp(String firstName, String lastName){
		// JN Calls the contains function to search for the person.
		HashNode returnNode = contains(capsName(firstName), capsName(lastName));
		String print = ""; // JN Creates a new string to contain will be printed.
		if(returnNode == null){
			// JN If the search returns nothing, print out that it does not contain the person.
			print = "Address book does not contain " + firstName + " " + lastName + ".\n";
		}
		else {
			// JN Prints out the information of the person when found.
			print = "Information found\nName: " + returnNode.getName() + "\nE-mail: " +
					returnNode.getEmail() + "\nPhone #: " + returnNode.getPhone() + "\n";
		}
		System.out.println(print);
	}
	
	/** JN
	 * Computes the Hash using the first and last name of the person 
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 * @return Returns the hash as an integer.
	 */
	private int computeHash(String firstName, String lastName){
		String name = firstName + lastName;
		int hash = name.hashCode()%13;
		if(hash < 0) hash = hash*-1;
		return hash;
	}
	
	/** JN
	 * Capitalizes the first letter of a string.
	 * @param name The first/last name that will be capitalized.
	 * @return Returns the capitalized version of the string.
	 */
	private String capsName(String name){
		name = name.toLowerCase();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	/** JN
	 * Adds the node into the hash bucket.
	 * @param index The index of where the Node should be.
	 * @param node The node that will be added.
	 */
	private void addNode(int index, HashNode node){
		// JN If the AddressBook has something in the index already, add it to the front of the chain.
		if(addressBook[index] != null){
			node.setNext(addressBook[index]);
		}
		addressBook[index] = node; // JN Sets the new node as the first node of the chain.
	}
	
	/** JN
	 * Searches to see if the Hash table has the person already.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 * @return Returns the node containing the information for the person.
	 */
	private HashNode contains(String firstName, String lastName){
		Boolean found = false; // JN New boolean identifying if the person has been found.
		int index = computeHash(capsName(firstName), capsName(lastName)); // JN Finds the index for the person.
		HashNode currentNode = addressBook[index];
		// JN Iterates through the linked chain list of the nodes for that index.
		if(currentNode != null){
			// JN Searches while the node still has a value and the person has not been found.
			while(currentNode != null && !found){
				String fullName = capsName(firstName) + " " + capsName(lastName);
				if(fullName.equals(currentNode.getName())){
					found = true; // JN The name has been found.
				}
				else {
					currentNode = currentNode.nextNode(); // JN Iterate to the next node if the name has not been found.
				}
			}
		}
		return currentNode; // JN Returns the node of the person of null if not found.
	}
	/** JN
	 * Removes the person from the array.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 * @return Returns the node right before the one that needs to be removed.
	 */
	private HashNode remove(String firstName, String lastName){
		// JN Computes the index of the person's name.
		int index = computeHash(capsName(firstName), capsName(lastName));
		Boolean found = false; // JN Variable to determine if we found the node to remove or not.
		HashNode currentNode = addressBook[index];
		HashNode prevNode = null; // JN The previous node that will be returned.
		
		// JN If the current node contains an object, we search through the chain.
		if(currentNode != null){
			// JN Iterates through while we have not found the node to remove.
			while(currentNode != null && !found){
				String fullName = capsName(firstName) + " " + capsName(lastName);
				if(fullName.equals(currentNode.getName())){
					found = true; // JN Found the node
				}
				else {
					// JN Iterates to the next node and sets previous.
					prevNode = currentNode;
					currentNode = currentNode.nextNode();
				}
			}
		}
		//if(!found) prevNode = null; // JN Returns null if we did not find the person
		return prevNode; // JN Otherwise return the node before it.
	}
	
	/** JN
	 * The private class HashNode that creates a node for a linked chain.
	 * @author Jimmy Nguyen
	 * @version 7/18/2016
	 */
	private class HashNode{
		
		private HashNode next; // JN The field representing the next node in the chain.
		private String firstName; // JN The data field containing the first name of the person.
		private String lastName; // JN The data field containing the last name of the person.
		private String email; // JN The data field containing the email of the person.
		private String phone; // JN The data field containing the phone of the person.
		
		/** JN
		 * The constructor that takes in parameters and initializes the HashNode.
		 * @param firstName First name of the person.
		 * @param lastName Last name of the person.
		 * @param phone Phone number of the person.
		 * @param email Email of the person.
		 */
		public HashNode(String firstName, String lastName, String phone, String email){
			// JN Initializes the fields according to the input.
			this.next = null;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phone = phone;
		}
		
		/** JN
		 * Sets the next node in the chain.
		 * @param next The next node in the chain.
		 */
		public void setNext(HashNode next){
			this.next = next;
		}
		
		/** JN
		 * Returns the full name of the person.
		 * @return Returns the full name of the person in a string.
		 */
		public String getName(){
			return firstName + " " + lastName; // JN Concatenates the first and last name with a space in between.
		}
		
		/** JN
		 * Returns the email
		 * @return Returns the email in a string.
		 */
		public String getEmail(){
			return email;
		}
		
		/** JN JN
		 * Returns the phone
		 * @return Returns the phone number in a string.
		 */
		public String getPhone(){
			return phone;
		}
		
		/** JN
		 * Returns the next node in the chain
		 * @return Returns the next HashNode.
		 */
		public HashNode nextNode(){
			return next;
		}
	}

}
