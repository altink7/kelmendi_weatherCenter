Testdata for api:
http://localhost:8080/api/weather/test

Get all weather data:
http://localhost:8080/api/weather/all

Get last weather data:
http://localhost:8080/api/weather/last

Get weather data for city:
http://localhost:8080/api/weather/city/Vienna

Get weather data for country:
http://localhost:8080/api/weather/country/Austria

Add weather data:
http://localhost:8080/api/weather/add with request body:

{
"city": "New York",
"country": "USA",
"temperature": "18",
"windSpeed": "10"
}

Delete all weather data:
http://localhost:8080/api/weather/delete