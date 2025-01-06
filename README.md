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

5.3.2 Different scopes
Instances may not be destroyed if the fields have different scopes. This is a pitfall caused by the Java specification. For example, if an instance of singleton scope has an instance of prototype scope in a field.
[Bad example sample code]
```
@Component
@Scope ("prototype" )
public class PrototypeComponent {
…
}
```
[Bad example sample code]
```
@Component
public class SingletonComponent {
@Autowired
private PrototypeComponent component ;
…
}
```
If you do this, the Bean with the prototype scope (PrototypeComponent above) will become the singleton scope. The same thing happens if you have a bean with request scope set in a bean with session scope.
Be careful when having beans with different scopes as fields.

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

- Because of the dependencies/libraries in the classpath, some applications are configured for such, for example
  - Because Tomcat is on the classpath (transitively referred to by the web starter dependency), an embedded Tomcat container will be started to listen on port 8080.
  - Developing your first Spring Boot application, because H2 is on the classpath, an embedded H2 database bean will be created. This bean is of type javax.sql.DataSource, which the JPA implementation (Hibernate) will need to access the database

