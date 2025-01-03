# Java-Backend

### Covers. 

Spring Bean is any object that is managed by Spring.

What is Spring IOC ?
Spring IOC is a technique to achieve loose-coupling between objects dependencies. In Spring, instead of having the objects create the dependent objects, the container creates the objects and injects them into the dependent objects.

Any object created in spring has scopes. 

1. Singleton :  A single instance per Spring IoC container.
2. Prototype : A new instance every time a request for that bean is made.
3. Request (web) : A single instance per HTTP request. (Web-aware)
4. Session :  A single instance per HTTP session. (Web-aware)
5. Application : A single instance per ServletContext. (Web-aware)
6. Websocket :  A single instance per WebSocket. (Web-aware)

- What is the difference ? HTTP request vs ServletContext
- - ServletContext: In the context of Spring Framework, the Application scope means that a single bean instance is created for the entire lifecycle of the web application, shared across all HTTP requests and sessions.

- so what happens if an object which has a request scope refers an object of application scope ?
- - In the Spring Framework, if an object with a Request scope refers to an object with an Application scope, the Application scoped object will be shared across all HTTP requests and sessions, while the Request scoped object will be created anew for each HTTP request. The Request scoped object will hold a reference to the same Application scoped object instance for each request.  This means that the Application scoped object will maintain its state across multiple requests, while the Request scoped object will be instantiated afresh for each request, but it will always refer to the same Application scoped object.

@Import annotation - helps in importing bean definitions in one class to other. Beneficial in reusability & maintenance & modularizing. Not good for hidden depdencies & ciruclar ones. 

How to declare the main class in Spring 


Hibernate - 
converts the pojo's into tables and columns and vice versa.
mapping annotations.
in hibernate we would use hql (for fetching details from database)

JPA - standard - all orm's follow jpa.
pros and cons of it own. standardization vs performance, over abstraction leading to loss in highly detailed control over declaring certain parameters. 
jpql - query language of jpa.

In spring we have to manually declare the dependencies and maintain them. 
Hibernate mvc all databases and everything.
in spring boot we can just declare starter-web and web server , mvc would be added for us.

Microservices
[Microservice arch](res/microservices.png)

Resilience patterns in microservices:
1. Circuit Breaker
3. Timeout
4. Bulkhead
5. Fallback

Client side load balancing.
 - each microservice when communcating with other microservices - contacts a discovery service (eureka) and gets the ip address of the microservice it wants to communicate with.
 - but repeatedly contacting the discovery service is not efficient.
 - hence we maintain a some sort of cache on the client side when one request is done.
 - when contacting an instance when is cached, if it fails beyond certain calls, we remove it from the cache and contact the discovery service again.

Circuit breaker
- when a service is down, the client service should not keep on trying to contact the service.

Fallback
- when a service is down, it should switch to some default or other backup service which should be used.

Bulkhead
- when a service is down, it should not affect the other services. isolation of services.

