package com.example.assignment1springnasaapod.apodHandling;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Assignment1Controller
{

    private APODService apodService = null;

    public Assignment1Controller(APODService apodService)
    {
        this.apodService = apodService;
    }

    /*
    @GetMapping("/apod/getByDate")
    public APODObject[] getApod(@RequestParam(value = "start_date", required = false) String start_date,
                                @RequestParam(value = "end_date", required = false) String end_date)
    {
        return apodService.getAPODObject(start_date,end_date);
    }
    */

    @GetMapping("/apod")
    public APODObject[] getApod(@RequestParam(value = "count", required = false, defaultValue = "0") Integer count,
                        @RequestParam(value = "date", required = false) String date,
                        @RequestParam(value = "start_date", required = false) String start_date,
                        @RequestParam(value = "end_date", required = false) String end_date,
                        @RequestParam(value = "thumbs", required = false) String thumbs
                              )


    {
        return apodService.getAPODObjects(date, start_date, end_date, count, thumbs);
    }


    @GetMapping("**")
    public String everythingElse()
    {
        return "Bad request: Please use the apod API endpoint for this springboot application.\n" +
                "<br>Example: http://localhost:8080/apod?start_date=1996-1-1&end_date=1996-1-3";
    }
}
