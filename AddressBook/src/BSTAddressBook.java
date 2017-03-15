/** JN
 * An implementation of an address book using a binary search tree.
 * @author Jimmy nguyen
 * @version 7/18/2016
 */
public class BSTAddressBook implements AddressBookInterface{
	
	private TreeNode root; // JN The root of the whole tree.
	
	/** JN
	 * The constructor that initializes any fields.
	 */
	public BSTAddressBook(){
		this.root = null;
	}
	
	/** JN
	 * Adds a new Tree node with the person's information.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 * @param phone The phone # of the person.
	 * @param email The email of the person.
	 */
	public void insert(String firstName, String lastName, String phone, String email) {
		// JN Creates a new tree node.
		TreeNode newNode = new TreeNode(capsName(firstName), capsName(lastName), phone, email);
		root = insert(root, newNode); // JN Calls the insert method and uses the newNode as a parameter.
		
	}

	/** JN
	 * Inserts the a node into the Binary tree.
	 * @param root The root of the tree.
	 * @param newNode The new node that will be added.
	 * @return Returns the root of the new tree.
	 */
	private TreeNode insert(TreeNode root, TreeNode newNode){
		// JN If there is nothing in the node, set the node to the newNode.
		if (root == null){
			root = newNode;
		}
		// JN Compares the names if there is a node in the tree already.
		else if (newNode.getName().compareTo(root.getName()) < 0){
			// JN Puts the node in the left child if it is less than the root node.
			root.setLeft(insert(root.getLeft(), newNode));
		}
		else {
			// JN Puts the node on the right if the new node is equal or more than the root.
			root.setRight(insert(root.getRight(), newNode));
		}
		return root;
	}
	
	/** JN
	 * Removes the node and value from the Binary Search Tree using the first and last name.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 */
	public void delete(String firstName, String lastName) {
		// JN Finds the record of the person.
		String fullName = capsName(firstName) + " " + capsName(lastName);
		TreeNode returnedNode = contains(root, fullName);
		// JN If the person is found, deletes them.
		if(returnedNode != null){
			System.out.println("Deleted " + returnedNode.getName() + ".\n");
			// JN If the node has a left child, find the largest node in the subtree and replace.
			if(returnedNode.hasLeft()){
				returnedNode.copyNode(largestNode(returnedNode.getLeft()));
				returnedNode.setLeft(removeNode(returnedNode.getLeft()));
			}
			// JN If the node only has a right child, replace the node with the right child.
			else if(returnedNode.hasRight()){
				returnedNode.copyNode(returnedNode.getRight());
				returnedNode.setRight(returnedNode.getRight().getRight());
			}
			// JN Does not replace the node, just deletes it since it is a leaf.
			else {
				returnedNode = null;
			}
		}
		else {
			// JN Did not find the person.
			System.out.println("Address book does not contain " + fullName + ".\n");
		}
	}
	
	/** JN
	 * Finds the largest node of the left child.
	 * @param root
	 * @return Returns the node with the largest value.
	 */
	private TreeNode largestNode(TreeNode root){
		// JN Iterates through the right child until there is no more right child.
		if (root.hasRight()) return largestNode(root.getRight());
		return root;
	}
	
	/** JN
	 * Removes the node from the Tree
	 * @param rootNode
	 * @return Returns the tree after the root is removed.
	 */
	private TreeNode removeNode(TreeNode rootNode){
		// JN Iterates through the tree if the node has a right child.
		if(rootNode.hasRight()){
			TreeNode rightChild = rootNode.getRight();
			TreeNode root = removeNode(rightChild);
			rootNode.setRight(root);
		}
		else {
			// JN Returns the left one of the right child is not available.
			rootNode = rootNode.getLeft();
		}
		return rootNode;
	}

	/** JN
	 * Searches to see if the address book contains the person.
	 * @param firstName The first name of the person.
	 * @param lastName The last name of the person.
	 */
	public void lookUp(String firstName, String lastName) {
		String print = ""; // JN Creates a new string that will hold what to print.
		String fullName = capsName(firstName) + " " + capsName(lastName);
		TreeNode returnNode = contains(root, fullName); // JN Finds the node if it exists.
		// JN If it exists, print out the information.
		if( returnNode!= null){
			print = "Information found\nName: " + returnNode.getName() + "\nE-mail: " +
					returnNode.getEmail() + "\nPhone #: " + returnNode.getPhone() + "\n";
		}
		else {
			// JN else print out that the AddressNook does not contain this person.
			print = "Address book does not contain " + fullName + ".\n";
		}
		System.out.println(print);
	}
	
	/** JN
	 * Searches the tree to see if there is a node that matches the person.
	 * @param root The tree's root.
	 * @param name The name of the person being searched.
	 * @return Returns the node of the person if found.
	 */
	private TreeNode contains(TreeNode root, String name){
		// JN If the subtree is empty return null
		if(root == null) return null;
		// JN Returns the root if the person matches.
		if(name.compareTo(root.getName()) == 0){
			return root;
		}
		// JN Iterates to the left if the person is less.
		else if(name.compareTo(root.getName()) < 0){
			return contains(root.getLeft(), name);
		}
		// JN Iterates to the right otherwise.
		else {
			return contains(root.getRight(), name);
		}
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
	 * The inner private TreeNode class that represents the Node object.
	 * @author Jimmy Nguyen
	 * @version 7/18/2016
	 */
	private class TreeNode{
		// JN Declares the necessary child fields
		private TreeNode left;
		private TreeNode right;
		
		// JN The data of the person
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		
		/** JN
		 * The constructor of a TreeNode using the name.
		 * @param firstName First name of the person.
		 * @param lastName Last name of the person.
		 * @param phone Phone number of the person.
		 * @param email Email of the person.
		 */
		public TreeNode(String firstName, String lastName, String phone, String email){
			this.left = null;
			this.right = null;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phone = phone;
		}
		
		/** JN
		 * Copies the data from the other node.
		 * @param node The other object node that is copied from.
		 */
		public void copyNode(TreeNode node){
			// JN Sets the fields to the data from the other node.
			this.firstName = node.firstName;
			this.lastName = node.lastName;
			this.email = node.getEmail();
			this.phone = node.getPhone();
		}
		
		/** JN
		 * Returns the left child.
		 * @return Returns the left child.
		 */
		public TreeNode getLeft(){
			return left;
		}
		
		/** JN
		 * Returns the right child.
		 * @return Returns the right child.
		 */
		public TreeNode getRight(){
			return right;
		}
		
		/** JN
		 * Sets the left child.
		 * @param left The new left child.
		 */
		public void setLeft(TreeNode left){
			this.left = left;
		}
		
		/** JN
		 * Sets the right child.
		 * @param left The new right child.
		 */
		public void setRight(TreeNode right){
			this.right = right;
		}
		
		/** JN
		 * Returns the full name.
		 * @return Returns the full name in a string.
		 */
		public String getName(){
			return firstName + " " + lastName;
		}
		
		/** JN
		 * Returns the email.
		 * @return Returns the email in a string.
		 */
		public String getEmail(){
			return email;
		}
		
		/** JN
		 * Returns the phone.
		 * @return Returns the phone number in a string.
		 */
		public String getPhone(){
			return phone;
		}
		
		/** JN
		 * Returns if the node has a left child.
		 * @return Returns if the left child is not null.
		 */
		public boolean hasLeft(){
			return left != null;
		}
		
		/** JN
		 * Returns if the node has a right child.
		 * @return Returns if the right child is not null.
		 */
		public boolean hasRight(){
			return right != null;
		}
	}
}
