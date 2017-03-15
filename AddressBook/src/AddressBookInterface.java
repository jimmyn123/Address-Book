/** JN
 * The interface for an Address Book data type. Requires each class to implement the insert, delete and lookup functions.
 * @author Jimmy Nguyen
 * @version 7/18/2016
 */
public interface AddressBookInterface {
	// JN The required methods that need to be overridden when the class implements AddressBookInterface
	public void insert(String firstName, String lastName, String phone, String email);
	public void delete(String firstName, String lastName);
	public void lookUp(String firstName, String lastName);
}
