package ir.saeidbabaei.productreviewservice.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ir.saeidbabaei.productreviewservice.model.Review;
import ir.saeidbabaei.productreviewservice.service.IReviewService;

/**
 * 
 * @author Saeid Babaei
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product-review")
public class ReviewController {
	
    @Autowired
    private IReviewService reviewService;
    
    /**Saving review.
     * 
     * @param	review		Review information.
     * @return 			 	Response 200 OK. Review info.
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
    	
    	reviewService.save(review);
	          
        return ResponseEntity.ok(review);
    }    
    
    /**Get list of all review.
     * 
     * @return				Response 200 OK. List of review.
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> getReviewList() {
	      
		  return ResponseEntity.ok(reviewService.findAll());
	}
	  
	/**Retrieve review by id.
	 * 
	 * @param	id			Review id.
     * @return				Response 200 OK. Review info.
	 * @throw				EntityNotFoundException.     
	 */
	@GetMapping("/edit/{id}")
	public ResponseEntity<Review> retrieveReview(@PathVariable long id) {
		
			Optional<Review> review = reviewService.findById(id);

			if (!review.isPresent())
				throw new EntityNotFoundException("Review");

	        return ResponseEntity.ok(review.get());
	}

	/**Update review by id.
	 * 
	 * @param	review 		Review update info.
	 * @param	id			Review id that should be updated. 
	 * @return				Response 200 OK. review info.
	 * @throw				EntityNotFoundException.
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable long id) {

		Optional<Review> reviewOptional = reviewService.findById(id);

		if (!reviewOptional.isPresent())
			throw new EntityNotFoundException("Review");

		review.setId(id);
		
		reviewService.save(review);
		
		return ResponseEntity.ok(review);
	}
	
	/**Delete review by id.
	 * 
	 * @param	id			Review id that should be deleted.
	 * @return				Response 202 Accepted.
	 * @throw				EntityNotFoundException.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Review> deleteReview(@PathVariable long id) {
		
		Optional<Review> reviewOptional = reviewService.findById(id);
		
		if (!reviewOptional.isPresent())
			throw new EntityNotFoundException("Review");
		
		reviewService.deleteById(id);
		
		return ResponseEntity.accepted().build();
		
	}
	
	/**Retrieve review by productId.
	 * 
	 * @param	productId		Product id.
     * @return					Response 200 OK. List of reviews.     
	 */
	@GetMapping("/get-by-product/{id}")
	public ResponseEntity<List<Review>> retrieveReviewByProductId(@PathVariable long id) {

	        return ResponseEntity.ok(reviewService.findByProductId(id));
	}
    
}