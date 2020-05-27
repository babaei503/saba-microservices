package ir.saeidbabaei.productreviewservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * The persistent class for the review database table.
 * 
 */
@Entity
public class Review  {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
    @NotNull
	private long productId;

    @Size(min = 3, max = 50)
    @NotNull
	private String author;

    @Size(min = 3, max = 50)
    @NotNull
    private String subject;
    
    @Size(min = 3, max = 50)
    @NotNull
    private String content;
	

	public Review() {
	}
	
	/**
	 * @param	author        author of review.
	 * @param	subject		  subject of review.
	 * @param	content		  content of review.	 
	 */
    public Review(@NotNull long productId, @Size(min = 3, max = 50) @NotNull String author, @Size(min = 3, max = 50) @NotNull String subject, @Size(min = 3, max = 50) @NotNull String content) {
        this.productId = productId;
        this.author = author;
        this.subject = subject;
        this.content = content;
    }
    

	/**
	 * @return The review id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param productId The review id to set
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
	 * @return The subject
	 */
    public String getSubject() {
        return subject;
    }

	/**
	 * @param subject The subject to set
	 */
    public void setSubject(String subject) {
        this.subject = subject;
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