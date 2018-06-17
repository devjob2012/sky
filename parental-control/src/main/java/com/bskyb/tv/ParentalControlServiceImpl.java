package com.bskyb.tv;

import java.util.HashMap;
import java.util.Map;

public class ParentalControlServiceImpl implements ParentalControlService {
	private Map<String, Integer> parentalControlLevels = new HashMap<String, Integer>() {
		{
			put("U", 0);
			put("PG", 1);
			put("12", 2);
			put("15", 3);
			put("18", 4);
		}
	};

	private String movieParentalControlLevel;
	private MovieService movieService;

	public ParentalControlServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception {

		try {
			movieParentalControlLevel = movieService.getParentalControlLevel(movieId);
			return parentalControlLevels.get(movieParentalControlLevel) <= parentalControlLevels
					.get(customerParentalControlLevel);
		} catch (TitleNotFoundException tnfEx) {
			throw new Exception("Movie not found");
		} catch (Exception ex) {
			return false;
		}
	}

}
