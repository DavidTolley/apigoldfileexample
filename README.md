## API Contract Testing: Dynamic API Contract tests based on API Goldfiles
API Goldfiles are a way to ensure API contracts are honored at all times:
 - Goldfiles live in the directory:
   - /src/main/resources/goldfiles
 - Goldfiles are defined in yml format, example:
```
tests: [
    {
        name: Verify querying by city name matches 'name' attribute in response
        api_endpoint: "/data/2.5/weather?q=London"
        method: GET
        response: {
            name: "London"
        }
    }
]
```
To run API Contract Testing you need to do the following:
- Docker must be installed
- Clone this repo
- Set ENV variables:
  - a. HOST_URL: The URL of the app you want to test, example: https://api.openweathermap.org
  - b. API_KEY={YOUR_API_KEY}
- Run the following command: 
  - ./run_tests.sh
- Test results will be available in the directory with junit format:
  - results.xml
- Test HTML report will be available in:
  - report
