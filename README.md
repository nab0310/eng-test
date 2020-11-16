# Software Engineering API Integration Test

## What would you change
1. Would potentially add exponential backoff to http client
2. Rename OkHttpUserResource to something else.
3. Rename UserInfo to something else with more meaning.
4. Map ServiceExceptions to JSON responses with a mapper.
5. Change the Error thrown on line 144 to be a 502 rather than a 500. It happens because of timeouts or connectivity problems, so 502 makes more sense. Add the url to the log message?
6. Figure out the "Spring way" to do paginated list stuff (Page object?)
7. Use some sort of HATEOS client in spring (https://spring.io/projects/spring-hateoas#overview)
  - Could also use WebClient (https://www.baeldung.com/spring-5-webclient). That required updating the Spring dependency in the sample project and doing a couple of other things.
8. Add health checks?

## Questions I have
1. Can we figure out a better name than /compositeUsers?
2. Should the underlying api have filters on it?
3. What are the valid values for state of creditCards and devices? I would like to make my filters an enum.

## Questions I'm going to get
1. Why did you declare all those XXXPaginatedListDtos?
    - When doing the object mapping, you cant pass generics unless you pass a type reference and I thought it was better to have the extra classes than to do type references.
2. Why didn't you use Spring?
    - Because I wasn't comfortable with it and I was trying to get this done fairly quickly. I wasn't rushing but I didn't want to spend a bunch of time on it.
