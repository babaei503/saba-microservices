package ir.saeidbabaei.productcompositeservice.model;

public class Review  {

	private long id;
	private long productId;
	private String author;
    private String subject;
    private String content;
	

	public Review() {
	}
	
	/**
	 * @param	productId		Product of review.
	 * @param	author			Author of review.
	 * @param	subject			Subject of review.
	 * @param	content			Content of review.	 
	 */
    public Review(long productId, String author, String subject, String content) {
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
	 * @param id The review id to set
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