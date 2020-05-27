package ir.saeidbabaei.productservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * The persistent class for the product database table.
 * 
 */
@Entity
public class Product  {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

    @Size(min = 3, max = 50)
    @NotNull
	private String name;

    @Size(min = 3, max = 20)
    @NotNull
    private String color;
	

	public Product() {
	}
	
	/**
	 * @param name        Full name of product.
	 * @param color		  Color of product.
	 */
    public Product(@Size(min = 3, max = 50) @NotNull String name, @Size(min = 3, max = 20) @NotNull String color) {
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