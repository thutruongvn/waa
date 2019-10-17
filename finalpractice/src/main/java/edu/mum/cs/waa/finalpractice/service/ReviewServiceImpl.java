package edu.mum.cs.waa.finalpractice.service;

import edu.mum.cs.waa.finalpractice.domain.Review;
import edu.mum.cs.waa.finalpractice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}
