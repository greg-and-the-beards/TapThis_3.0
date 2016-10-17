package com.tapthis.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.tapthis.entity.ReviewInfo;

 
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public 	ReviewInfo getReviewById(int reviewId) {
		return hibernateTemplate.get(ReviewInfo.class, reviewId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewInfo> getOneReviewByUserId(int reviewUserId, int reviewId) {
		String hql = "FROM ReviewInfo where reviewUserId = " + reviewUserId + " and reviewId = " + reviewId;
		return (List<ReviewInfo>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewInfo> getReviewByBeerName(String beerInfo) {
		String hql = "FROM ReviewInfo where beerInfo = '" + beerInfo + "'";
		return (List<ReviewInfo>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewInfo> getAllReviewsByUserId(int reviewUserId) {
		String hql = "FROM ReviewInfo where reviewUserId = " + reviewUserId;
		return (List<ReviewInfo>) hibernateTemplate.find(hql);
	}
	
	@Override
	public boolean addReview(ReviewInfo review) {
		hibernateTemplate.save(review);
		return false;
	}
	
	@Override
	public void updateReview(ReviewInfo review) {
		ReviewInfo record = getReviewById(review.getReviewId());
		
		record.setBeerName(review.getBeerName());
		record.setBreweryName(review.getBreweryName());
		record.setUserName(review.getUserName());
		record.setBeerRating(review.getBeerRating());
		record.setHopsRating(review.getHopsRating());
		record.setMaltRating(review.getMaltRating());
		record.setReviewComment(review.getReviewComment());
		
		hibernateTemplate.update(record);
	}
	
	@Override
	public void deleteReview(int reviewId) {
		hibernateTemplate.delete(getReviewById(reviewId));
	}
}
