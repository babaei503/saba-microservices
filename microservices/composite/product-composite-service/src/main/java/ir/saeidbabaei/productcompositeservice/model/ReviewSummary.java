package ir.saeidbabaei.productcompositeservice.model;

/**
 * Author Saeid Babaei.
 */
public class ReviewSummary {

    private long id;
    private String author;
    private String subject;

    public ReviewSummary(long id, String author, String subject) {
        this.id = id;
        this.author = author;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }
    
}