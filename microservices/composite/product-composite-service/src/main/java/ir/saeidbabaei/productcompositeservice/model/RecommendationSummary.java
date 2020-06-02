package ir.saeidbabaei.productcompositeservice.model;

/**
 * Author Saeid Babaei.
 */
public class RecommendationSummary {

    private long Id;
    private String author;
    private int rate;

    public RecommendationSummary(long Id, String author, int rate) {
        this.Id = Id;
        this.author = author;
        this.rate = rate;
    }

    public long getId() {
        return Id;
    }

    public String getAuthor() {
        return author;
    }

    public int getRate() {
        return rate;
    }
    
}