# Web Engineering Assignment 1 REDONE

## How to use the API (example):
http://localhost:8080/apod?start_date=1996-1-1&end_date=1996-1-3

Query parameters:
- date: The date of the APOD image to retrieve
- start_date: The start of a date range, when requesting date for a range of dates. Cannot be used with date.
- end_date: The end of the date range, when used with start_date.
- count (default=0): If this is specified then count randomly chosen images will be returned. Cannot be used with date or start_date and end_date.
- thumbs: Return the URL of video thumbnail. If an APOD is not a video, this parameter is ignored.


This Spring application auto-handles bad queries by responding with an error message.
 
