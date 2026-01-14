# Price Selector

[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=softwaremagico_PriceSelector&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=kendo-tournament-frontend)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=softwaremagico_PriceSelector&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=kendo-tournament-frontend)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=softwaremagico_PriceSelector&metric=bugs)](https://sonarcloud.io/summary/new_code?id=kendo-tournament-frontend)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=softwaremagico_PriceSelector&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=kendo-tournament-frontend)

This project was developed as a technical assessment exercise, focusing on clean design, correctness, performance, and maintainability.

PriceSelector is a RESTful service built with Spring Boot that allows querying the final applicable price of a product for a given brand at a specific
application date.

When multiple prices overlap in time, the service applies the business rule of selecting the price with the highest priority.

The project uses an in-memory H2 database, is initialized with example data, and follows a Hexagonal Architecture (Ports & Adapters) to ensure clean separation
of concerns, testability, and maintainability.

## Technologies Used

* Java 17
* Spring Boot
* Spring Web (REST API)
* Spring Data JPA
* Spring Validation
* Hibernate / JPA
* H2 In-Memory Database
* Maven
* TestNG for testing
* Spring Boot Test / MockMvc
* Swagger / OpenAPI (API documentation)
* Checkstyle (code style enforcement)

# Installation

To compile the project, please execute the following command:

```
mvn clean install
```

To run the tool, navigate to the `price-selector-infrastructure` directory and enter the following command:

```
mvn spring-boot:run
```

The tool will be available shortly.

# Architecture

The project follows a Hexagonal Architecture (also known as Ports & Adapters).
The main goal is to keep the business domain independent of technical details such as persistence or web frameworks.

## Architectural Principles

* Domain isolation: The domain model contains no Spring or JPA annotations.
* Explicit use cases: Business logic is implemented in GetApplicablePriceUseCase.
* Ports for persistence: The domain defines what it needs, not how it is implemented.
* Adapters for infrastructure: REST adapter (incoming).
* JPA/H2 adapter (outgoing).
* High testability and low coupling. 

# Technical Decisions

Main rule:

> If multiple prices apply for the same product, brand, and date, the price with the highest priority is selected.

Each price contains:
- brandId
- productId
- priceList
- priority
- startDate
- endDate
- price
- currency

### Persistence and Efficiency

The application uses an H2 in-memory database, automatically initialized at startup using SQL scripts.

The query strategy used is based on JPA. `findTopByProductAndBrandAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc`.
Where:
 - Filters by `prduct`, `brandId` and a filter between `startDate` and `endDate`.
 - `PriorityDesc` ensures that the highest priority is selected.
 - `findTop` ensures that only one price is obtained.

This strategy ensures:
 - No unnecessary data loading.
 - No in-memory sorting.
 - Clear and efficient business logic.

### Date and Time Handling

- All dates are handled in UTC.
- Instant is used internally to avoid timezone ambiguities.
- Clients must send dates in ISO-8601 format with Z.

Valid example:

```
2020-06-14T10:00:00Z
```
### REST API

Upon running the tool, navigate to the URL `http://localhost:8080/price-selector`.
A Swagger interface will be displayed, presenting all available endpoints.

> Note: For simplicity, no security measures have been incorporated into this project; therefore, all REST endpoints remain unprotected.

Main Endpoint

```
GET /prices
```

| Parameter       | Type     | Description                      |
|-----------------| -------- | -------------------------------- |
| productId       | number   | Product identifier               |
| brandId         | number   | Brand identifier                 |
| applicationDate | datetime | Application date (ISO-8601, UTC) |

#### Example

```
GET /prices?productId=35455&brandId=1&applicationDate=2020-06-15T16%3A00%3A00.00Z'
```

Successful Response (200 OK)

```
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00Z",
  "endDate": "2020-12-31T23:59:59Z",
  "price": 35.50,
  "currency": "EUR"
}
```

As the price with higher priority is always selected, only one price is returned. 

Error Responses

| HTTP Code | Description                   |
| --------- | ----------------------------- |
| 400       | Invalid or missing parameters |
| 404       | No applicable price found     |


Errors are handled centrally using `@PriceSelectorExceptionControllerAdvice`.

### Integration Tests

The project includes integration tests using MockMvc and the full Spring context.

Covered Scenarios

| Test | Application Date    | Expected Price |
| ---: | ------------------- | -------------- |
|    1 | 2020-06-14 10:00:00 | 35.50 EUR      |
|    2 | 2020-06-14 16:00:00 | 25.45 EUR      |
|    3 | 2020-06-14 21:00:00 | 35.50 EUR      |
|    4 | 2020-06-15 10:00:00 | 30.50 EUR      |
|    5 | 2020-06-16 21:00:00 | 38.95 EUR      |


Each test validates:

- Final price.
- Product.
- Applied price list.
- Brand.
- Currency.

Additional negative tests:

- No applicable price (Expected 404)
- Invalid parameters (Expected 400)