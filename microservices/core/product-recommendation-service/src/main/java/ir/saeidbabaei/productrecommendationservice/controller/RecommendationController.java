package ir.saeidbabaei.productrecommendationservice.controller;

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

import ir.saeidbabaei.productrecommendationservice.model.Recommendation;
import ir.saeidbabaei.productrecommendationservice.service.IRecommendationService;

/**
 * 
 * @author Saeid Babaei
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product-recommendation")
public class RecommendationController {
	
    @Autowired
    private IRecommendationService recommendationService;
    
    /**Saving recommendation.
     * 
     * @param	recommendation		Recommendation information.
     * @return 			 	Response 200 OK. Recommendation info.
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recommendation> saveRecommendation(@RequestBody Recommendation recommendation) {
    	
    	recommendationService.save(recommendation);
	          
        return ResponseEntity.ok(recommendation);
    }    
    
    /**Get list of all recommendation.
     * 
     * @return				Response 200 OK. List of recommendation.
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Recommendation>> getRecommendationList() {
	      
		  return ResponseEntity.ok(recommendationService.findAll());
	}
	  
	/**Retrieve recommendation by id.
	 * 
	 * @param	id			Recommendation id.
     * @return				Response 200 OK. Recommendation info.
	 * @throw				EntityNotFoundException.     
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Recommendation> retrieveRecommendation(@PathVariable long id) {
		
			Optional<Recommendation> recommendation = recommendationService.findById(id);

			if (!recommendation.isPresent())
				throw new EntityNotFoundException("Recommendation");

	        return ResponseEntity.ok(recommendation.get());
	}

	/**Update recommendation by id.
	 * 
	 * @param	recommendation 		Recommendation update info.
	 * @param	id			Recommendation id that should be updated. 
	 * @return				Response 200 OK. recommendation info.
	 * @throw				EntityNotFoundException.
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Recommendation> updateRecommendation(@RequestBody Recommendation recommendation, @PathVariable long id) {

		Optional<Recommendation> recommendationOptional = recommendationService.findById(id);

		if (!recommendationOptional.isPresent())
			throw new EntityNotFoundException("Recommendation");

		recommendation.setId(id);
		
		recommendationService.save(recommendation);
		
		return ResponseEntity.ok(recommendation);
	}
	
	/**Delete recommendation by id.
	 * 
	 * @param	id			Recommendation id that should be deleted.
	 * @return				Response 202 Accepted.
	 * @throw				EntityNotFoundException.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Recommendation> deleteRecommendation(@PathVariable long id) {
		
		Optional<Recommendation> recommendationOptional = recommendationService.findById(id);
		
		if (!recommendationOptional.isPresent())
			throw new EntityNotFoundException("Recommendation");
		
		recommendationService.deleteById(id);
		
		return ResponseEntity.accepted().build();
		
	}
	
	/**Retrieve recommendation by productId.
	 * 
	 * @param	productId		Product id.
     * @return					Response 200 OK. List of recommendations.     
	 */
	@GetMapping("/get-by-product/{id}")
	public ResponseEntity<List<Recommendation>> retrieveRecommendationByProductId(@PathVariable long id) {

	        return ResponseEntity.ok(recommendationService.findByProductId(id));
	}
    
}