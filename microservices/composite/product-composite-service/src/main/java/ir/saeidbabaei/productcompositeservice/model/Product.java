package ir.saeidbabaei.productcompositeservice.model;

public class Product  {


	private long id;
	private String name;
    private String color;
	

	public Product() {
	}
	
	/**
	 * @param name        Full name of product.
	 * @param color		  Color of product.
	 */
    public Product(String name, String color) {
        this.name = name;
        this.color = color;
    }
    

	/**
	 * @return The product id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param productId The product id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color The color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

}