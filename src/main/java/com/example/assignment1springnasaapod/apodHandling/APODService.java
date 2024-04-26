package com.example.assignment1springnasaapod.apodHandling;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.assignment1springnasaapod.apodHandling.APODObject;

// This service annotation is important; otherwise SpringBoot will not know that this needs
// to be autowired into your main app.
@Service
public class APODService
{
    // API key from https://api.nasa.gov/
    private static final String API_KEY = "Eshc6SGaERpVafvFE71XDMWAVTzecUzPLjgY9IGG";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final RestTemplate restTemplate;

    public APODService(RestTemplateBuilder builder)
    {
        // In-built springboot builder for REST
        this.restTemplate = builder.build();
    }


    // Default, picture of the day
    public APODObject getAPODObject()
    {
        String url = BASE_URL + "?api_key=" + API_KEY;
        // Auto-gets the response object, just need to give it annotated class.
        return restTemplate.getForObject(url, APODObject.class);
    }

    public APODObject getAPODObject(LocalDate date)
    {
        String url = BASE_URL + "?api_key=" + API_KEY + "&date=" + date.format(DATE_FORMAT);
        return restTemplate.getForObject(url, APODObject.class);
    }

    public APODObject[] getAPODObject(int count)
    {
        String url = BASE_URL + "?api_key=" + API_KEY + "&count=" + count;
        return restTemplate.getForObject(url, APODObject[].class);
    }


    public APODObject[] getAPODObject(String start_date, String end_date )
    {
        String url = BASE_URL + "?api_key=" + API_KEY + "&start_date=" + start_date+
                "&end_date=" + end_date;
        return restTemplate.getForObject(url, APODObject[].class);
    }

    public APODObject[] getAPODObjects(String date, String start_date, String end_date, int count, String thumbs) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?api_key=").append(API_KEY);

        // Check whether this is an improper call having both Date and date range.
        if( date != null && (start_date != null || end_date!=null) )
        {
            APODObject[] returnArray = new APODObject[1];
            returnArray[0] = new APODObject() {{setExplanation("Bad query! You cannot use both date and date range! Use either one.");}};
            return  returnArray;
        }

        else if( count > 0 && (start_date != null || end_date!=null) )
        {
            APODObject[] returnArray = new APODObject[1];
            returnArray[0] = new APODObject() {{setExplanation("Bad query! You cannot use both count and date range! Use either one." +
                    "\nCount is for fetching a random count of images from random dates.");}};
            return  returnArray;
        }
        if (date != null) {
            urlBuilder.append("&date=").append(date);
            String url = urlBuilder.toString();
            APODObject[] returnArray = new APODObject[1];
            returnArray[0] = restTemplate.getForObject(url, APODObject.class);
            return  returnArray;
        }
        if (start_date != null) {
            urlBuilder.append("&start_date=").append(start_date);
        }
        if (end_date != null) {
            urlBuilder.append("&end_date=").append(end_date);
        }
        if (count > 0) {
            urlBuilder.append("&count=").append(count);
        }

        if (thumbs != null) {
            urlBuilder.append("&thumbs=").append(thumbs);
        }

        String url = urlBuilder.toString();
        return restTemplate.getForObject(url, APODObject[].class);
    }


    //&start_date=2024-01-01&end_date=2024-02-01

}
