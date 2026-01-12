# Price Selector Tool

This tool serves as an example of a REST server implemented using Spring Boot.

## Test

Instead of running the tool, you may consult the [tests](price-selector-infrastructure/src/test/java/com/test/bcnc/infrastructure/price/rest/PriceRequestedExampleTests.java)
for detailed examples illustrating the tool’s functionality with different requests.

## Installation

This example has been developed using Maven. To compile the project, please execute the following command:

```
mvn clean install
```

To run the tool, navigate to the `price-selector-infrastructure` directory and enter the following command:

```
mvn spring-boot:run
```

The tool will be available shortly.


## Accessing the REST Services

Upon running the tool, navigate to the URL `http://localhost:8080/price-selector`.
A Swagger interface will be displayed, presenting all available endpoints.

### Searching for a Price

A basic search can be performed via the endpoint `GET /prices`, which requires three parameters: `product`, `brand`, and `on`.

An example using CURL is provided below:

```
curl -X 'GET' \
  'http://localhost:8080/price-selector/prices?product=35455&brand=1&on=2020-06-15T16%3A00%3A00.00Z' \
  -H 'accept: application/json'
``` 

Alternatively, if you prefer to utilize POST services, use the endpoint `POST /prices` with a payload structured as follows:

```
{
  "product": 35455,
  "brand": 1,
  "on": "2020-06-14T18:00:00.0Z"
}
```

n this context, `product` denotes the product ID being queried, `brand` represents the brand ID,
and `on` specifies the date and time for which the price is requested, formatted according to ISO-8601 in UTC.

> Note: For simplicity, no security measures have been incorporated into this project; therefore, all REST endpoints remain unprotected.
