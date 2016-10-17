package com.tapthis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tapthis.dao.ReviewDAO;
import com.tapthis.entity.ReviewInfo;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO reviewDAO;
	
	@Override
	public ReviewInfo getReviewById(int reviewId) {
		ReviewInfo obj = reviewDAO.getReviewById(reviewId);
		return obj;
	}
	
	@Override
	public List<ReviewInfo> getOneReviewByUserId(int reviewUserId, int reviewId) {
		return reviewDAO.getOneReviewByUserId(reviewUserId, reviewId);
	}
	
	@Override
	public List<ReviewInfo> getReviewByBeerName(String beerInfo) {
		return reviewDAO.getReviewByBeerName(beerInfo);
	}
	
	@Override
	public List<ReviewInfo> getAllReviewsByUserId(int reviewUserId) {
		return reviewDAO.getAllReviewsByUserId(reviewUserId);
	}

	@Override
	public synchronized boolean addReview(ReviewInfo review) {
		reviewDAO.addReview(review);
		return true;
	}
	
	@Override
	public void updateReview(ReviewInfo review) {
		reviewDAO.updateReview(review);
	}
	
	@Override
	public void deleteReview(int reviewId) {
		reviewDAO.deleteReview(reviewId);
	}
}
