# Software Engineering API Integration Test

## What would you change
1. Would potentially add exponential backoff to http client
2. Rename OkHttpUserResource to something else.
3. Map ServiceExceptions to JSON responses with a mapper.
4. Change the Error thrown on line 144 to be a 502 rather than a 500. It happens because of timeouts or connectivity problems, so 502 makes more sense. Add the url to the log message?
5. Figure out the "Spring way" to do paginated list stuff (Page object?)
6. Use some sort of HATEOS client in spring (https://spring.io/projects/spring-hateoas#overview)
  - Could also use WebClient (https://www.baeldung.com/spring-5-webclient). That required updating the Spring dependency in the sample project and doing a couple of other things.
7. Add health checks?

## Questions I have
1. Can we figure out a better name than /compositeUsers?
2. Should the underlying api have filters on it?
