package ir.saeidbabaei.productrecommendationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * The persistent class for the recommendation database table.
 * 
 */
@Entity
public class Recommendation  {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
    @NotNull
	private long productId;

    @Size(min = 3, max = 50)
    @NotNull
	private String author;

    @NotNull
    private int rate;
    
    @Size(min = 3, max = 50)
    @NotNull
    private String content;
	

	public Recommendation() {
	}
	
	/**
	 * @param	productId		Product of recommendation.
	 * @param	author			Author of recommendation.
	 * @param	rate			Rate of recommendation.
	 * @param	content			Content of recommendation.	 
	 */
    public Recommendation(@NotNull long productId, @Size(min = 3, max = 50) @NotNull String author, @NotNull int rate, @Size(min = 3, max = 50) @NotNull String content) {
        this.productId = productId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }
    

	/**
	 * @return The recommendation id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id The recommendation id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return The product id
	 */
    public long getProductId() {
        return productId;
    }

	/**
	 * @param productId The product id to set
	 */
    public void setProductId(long productId) {
        this.productId = productId;
    }
    
	/**
	 * @return The author name
	 */
    public String getAuthor() {
        return author;
    }

	/**
	 * @param author The author to set
	 */
    public void setAuthor(String author) {
        this.author = author;
    }
	
	/**
	 * @return The rate
	 */
    public int getRate() {
        return rate;
    }

	/**
	 * @param rate The rate to set
	 */
    public void setSubject(int rate) {
        this.rate = rate;
    }
    
	/**
	 * @return The content
	 */
    public String getContent() {
        return content;
    }

	/**
	 * @param content The content to set
	 */
    public void setContent(String content) {
        this.content = content;
    }

}