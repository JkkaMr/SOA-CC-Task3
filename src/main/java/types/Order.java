package types;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	// base value for newly created ids
	private static int count = 1000;
	
	private String id = count++ + "";
	private String deliveryAddress;
	
	public Order() {}
	
	public Order(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
}
