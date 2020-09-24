package types;

public class Customer {
	private String id;
	private String firstName;
	private String lastName;
	private Order[] orders;
	
	public Customer() {}
	
	public Customer(String id, String firstName, String lastName, Order[] orders) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.orders = orders;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the orders
	 */
	public Order[] getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
}
