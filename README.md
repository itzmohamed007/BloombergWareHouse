# BloombergWareHouse API Documentation
a simple data warehouse for Bloomberg platform to analyze FX deals.

## API Endpoint

### Endpoint:
- **[POST] http://localhost:9000/api/v1/deals**

### Request JSON Example:
```json
{
  "id": 1,
  "toCurrencyIsoCode": "MAD",
  "fromCurrencyIsoCode": "USA",
  "dealAmount": 2000
}
```


## Request Validation

The endpoint performs field validation using Jakarta Validation annotations. Any invalid field triggers a structured error message handled by the `GlobalExceptionsHandler` REST Controller Advice, responding with HTTP status `BAD_REQUEST`.


## Database Interaction

- If the FX deal with the specified `id` does not exist in the Postgres database:
    - The object is inserted.
    - The inserted object is returned.

- If the FX deal with the specified `id` already exists in the database:
    - An exception is thrown.
    - The exception is caught and handled by the `GlobalExceptionsHandler` class.
    - The response includes a structured error message with HTTP status `BAD_REQUEST`.


## Testing

- **JUnit 5:**
    - The project employs JUnit 5 for unit testing.
    - Extensive test coverage ensures robustness for both controller and service, achieving 100% coverage.

- **Mockito:**
    - Mockito is utilized for efficient mocking in unit tests.


## Dockerization

The application is Dockerized using a multi-stage Dockerfile, optimizing the Docker image size and ensuring efficient deployment.


### Docker Compose

Docker Compose is employed to orchestrate the deployment of two services:
- The Spring Boot application [PORT 9000].
- Postgres 13, serving as the database [PORT 5432].