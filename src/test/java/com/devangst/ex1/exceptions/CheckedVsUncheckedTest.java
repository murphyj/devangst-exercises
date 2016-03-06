package com.devangst.ex1.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class CheckedVsUncheckedTest {

    private static final int READ_TIME_OUT = 5000;
    private static final int CONNECTION_TIME_OUT = 10000;

    private static final String PLACE_ID = "ChIJxZPY38BSekgR4KXk5UeCC4s";

    private CheckedVsUncheckedHttpClient checkedVsUnchecked;

    private static RestTemplate createRestTemplate() {
        // is it ok to create a new instance of HttpComponentsClientHttpRequestFactory everytime?
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(READ_TIME_OUT);
        requestFactory.setConnectTimeout(CONNECTION_TIME_OUT);
        return new RestTemplate(requestFactory);
    }

    @Before
    public void setup() {
        checkedVsUnchecked = new CheckedVsUncheckedHttpClient(createRestTemplate());
    }

    @Test
    public void testCheckedException() throws Exception {
        System.out.println(checkedVsUnchecked.googlePlaceChecked(PLACE_ID));
    }

    @Test
    public void testUncheckedException() throws Throwable {
        System.out.println(checkedVsUnchecked.googlePlaceUnchecked(PLACE_ID));
    }
}
