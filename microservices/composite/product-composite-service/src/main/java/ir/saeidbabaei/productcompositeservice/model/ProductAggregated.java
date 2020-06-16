package ir.saeidbabaei.productcompositeservice.model;

import java.util.List;
import java.util.stream.Collectors;

import ir.saeidbabaei.productcompositeservice.model.RecommendationSummary;
import ir.saeidbabaei.productcompositeservice.model.ReviewSummary;

/**
 * Author Saeid Babaei.
 */
public class ProductAggregated {
    private long id;
    private String name;
    private String color;
    private List<RecommendationSummary> recommendations;
    private List<ReviewSummary> reviews;

    public ProductAggregated(Product product, List<Recommendation> recommendations, List<Review> reviews) {

        if (product != null)
        {
	        this.id = product.getId();
	        this.name = product.getName();
	        this.color = product.getColor();
	
	        if (recommendations != null)
	            this.recommendations = recommendations.stream()
	                .map(r -> new RecommendationSummary(r.getId(), r.getAuthor(), r.getRate()))
	                .collect(Collectors.toList());
	
	        if (reviews != null)
	            this.reviews = reviews.stream()
	                .map(r -> new ReviewSummary(r.getId(), r.getAuthor(), r.getSubject()))
	                .collect(Collectors.toList());
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }
}