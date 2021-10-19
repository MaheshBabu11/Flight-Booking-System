# flight-booking-backend

## swagger ui url:

http://localhost:8080/api/v1.0/swagger-ui/#/

## update mysql connection properties in application.properties file

spring.datasource.url=jdbc:mysql://localhost:3306/{db-schema-name}?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false <br />
spring.datasource.username={mysql user name} <br />
spring.datasource.password={mysql password}

## Operating city add json
[
   {
      "active":true,
      "cityCode":"KOL",
      "cityName":"Kolkata"
   },
   {
      "active":true,
      "cityCode":"BHU",
      "cityName":"Bhubaneswar"
   },
   {
      "active":true,
      "cityCode":"GWH",
      "cityName":"Guwahati"
   },
   {
      "active":true,
      "cityCode":"BOM",
      "cityName":"Mumbai"
   },
   {
      "active":true,
      "cityCode":"DEL",
      "cityName":"Delhi"
   },
   {
      "active":true,
      "cityCode":"CHN",
      "cityName":"Chennai"
   },
   {
      "active":true,
      "cityCode":"BAN",
      "cityName":"Bangalore"
   }
]
