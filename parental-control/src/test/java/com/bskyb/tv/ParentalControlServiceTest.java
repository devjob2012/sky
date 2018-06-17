package com.bskyb.tv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {
	private ParentalControlService parentalControlServiceTest;
	private String movieId;
	private String parentalControlPreference;
	@Mock
	private MovieService movieService;

	@Before
	public void setup() {
		parentalControlServiceTest = new ParentalControlServiceImpl(movieService);
	}

	@After
	public void tearDown() throws TitleNotFoundException, TechnicalFailureException {
		verify(movieService).getParentalControlLevel(movieId);
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void whenParentalControlLevelLessThenOrEqualToParentalControlPrefThenCanWatchMovie() throws Exception {
		parentalControlPreference = "15";
		when(movieService.getParentalControlLevel(movieId)).thenReturn("U");
		boolean allowed = parentalControlServiceTest.canWatchMovie(parentalControlPreference, movieId);
		assertTrue(allowed);
	}

	@Test
	public void whenParentalControlLevelGreaterThenParentalControlPrefThenCanNotWatchMovie() throws Exception {
		parentalControlPreference = "15";
		when(movieService.getParentalControlLevel(movieId)).thenReturn("18");
		boolean allowed = parentalControlServiceTest.canWatchMovie(parentalControlPreference, movieId);
		assertFalse(allowed);

	}

	@Test(expected = Exception.class)
	public void whenMovieIdNotPresentThenThrowTitleNotFoundException() throws Exception {
		parentalControlPreference = "15";
		when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);
		parentalControlServiceTest.canWatchMovie(parentalControlPreference, movieId);
	}

	@Test
	public void whenTechnicalErrorOccurThenCannotWatchMovie() throws Exception {
		parentalControlPreference = "15";
		when(movieService.getParentalControlLevel(movieId)).thenThrow(TechnicalFailureException.class);
		boolean allowed = parentalControlServiceTest.canWatchMovie(parentalControlPreference, movieId);
		assertFalse(allowed);
	}
}
