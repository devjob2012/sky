package com.bskyb.tv;

public interface MovieService {
        String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException;
}
