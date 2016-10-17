package com.tapthis.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beer_review")
public class ReviewInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_review")
	private int reviewId;
	
	@Column(name="beer_name")
	private String beerName;
	
	@Column(name="brewery_name")
	private String breweryName;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="beer_rating_overall_quality")
	private int beerRating;
	
	@Column(name="hops_rating")
	private int hopsRating;
	
	@Column(name="malt_rating")
	private int maltRating;
	
	@Column(name="review_comment")
	private String reviewComment;
	
	@Column(name="beer_info")
	private String beerInfo;
	
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}
	
	public String getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	public int getBeerRating() {
		return beerRating;
	}

	public void setBeerRating(int beerRating) {
		this.beerRating = beerRating;
	}

	public int getHopsRating() {
		return hopsRating;
	}

	public void setHopsRating(int hopsRating) {
		this.hopsRating = hopsRating;
	}

	public int getMaltRating() {
		return maltRating;
	}

	public void setMaltRating(int maltRating) {
		this.maltRating = maltRating;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public String getBeerInfo() {
		return beerInfo;
	}

	public void setBeerInfo(String beerInfo) {
		this.beerInfo = beerInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
