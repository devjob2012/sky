package com.bskyb.tv.service.impl;

import static com.bskyb.tv.enums.ParentalControlEnum.valueOf;

import com.bskyb.tv.enums.ParentalControlEnum;
import com.bskyb.tv.exception.TitleNotFoundException;
import com.bskyb.tv.service.MovieService;
import com.bskyb.tv.service.ParentalControlService;

public class ParentalControlServiceImpl implements ParentalControlService {

	private ParentalControlEnum movieParentalControlLevel;
	private MovieService movieService;

	public ParentalControlServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception {

		try {
			movieParentalControlLevel = valueOf(movieService.getParentalControlLevel(movieId));
			return movieParentalControlLevel.ordinal() <= valueOf(customerParentalControlLevel).ordinal();
		} catch (TitleNotFoundException tnfEx) {
			throw new Exception("Movie not found");
		} catch (Exception e) {
			return false;
		}
	}
}
