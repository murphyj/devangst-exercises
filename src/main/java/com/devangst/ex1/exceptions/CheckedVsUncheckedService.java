package com.devangst.ex1.exceptions;

import com.devangst.ex1.google.GooglePlace;
import com.devangst.ex1.http.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
public class CheckedVsUncheckedService {

    public CheckedVsUncheckedHttpClient client;

    public CheckedVsUncheckedService() {
        client = new CheckedVsUncheckedHttpClient(new RestTemplate());
    }

    public AppResponse<GooglePlace> checkedService(String placeId) throws Exception, ServiceFailedException {
        try {
            GooglePlace results = client.googlePlaceChecked(placeId).getResults();
            return new AppResponse<GooglePlace>(results);
        } catch (ServiceUnavailableException sue) {
            throw sue;
        } catch (IOException ioe) {
            throw new ServiceFailedException(String.format("Service [%s] failed to get placeId %s. Reason: %s", this.getClass().getName(), placeId, ioe.getMessage()));
        } catch (Exception ex) {
            System.out.println("Generic unchecked exceptions");
            throw ex;
        }
    }

    public AppResponse<GooglePlace> uncheckedService(String placeId) throws Exception {
        try {
            GooglePlace results = client.googlePlaceUnchecked(placeId).getResults();
            return new AppResponse<GooglePlace>(results);
        } catch (Exception ex) {
            System.out.println("Generic unchecked exception - could be anything...");
            throw ex;
        }
    }
}
