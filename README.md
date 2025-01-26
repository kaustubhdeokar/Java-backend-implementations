# Java-Backend

- Spring IOC
- Import configuration.
- 


### Covers. 

Spring Bean is any object that is managed by Spring.

What is Spring IOC ?
Spring IOC is a technique to achieve loose-coupling between objects dependencies. In Spring, instead of having the objects create the dependent objects, the container creates the objects and injects them into the dependent objects.

@Import annotation - helps in importing bean definitions in one class to other. Beneficial in reusability & maintenance & modularizing. Can cause hidden/circular dependencies.
```
@Configuration
@Import({PrimaryConfig.class, SecondaryConfig.class})
public class MainConfig {
    // This class will import the beans defined in PrimaryConfig and SecondaryConfig
}
```

How to declare the main class in Spring 


Hibernate - 
converts the pojo's into tables and columns and vice versa.
mapping annotations.
in hibernate we would use hql (for fetching details from database)

JPA - standard - all orm's follow jpa.
pros and cons of it own. standardization vs performance, over abstraction leading to loss in highly detailed control over declaring certain parameters. 
jpql - query language of jpa.


- Auto configuration 
  - In spring we have to manually declare the dependencies and maintain them. 
Hibernate mvc all databases and everything.
in spring boot we can just declare starter-web and web server , mvc would be added for us.
  - Because of the dependencies/libraries in the classpath, some applications are configured for such, for example
  - Because Tomcat is on the classpath (transitively referred to by the web starter dependency), an embedded Tomcat container will be started to listen on port 8080.
  - Developing your first Spring Boot application, because H2 is on the classpath, an embedded H2 database bean will be created. This bean is of type javax.sql.DataSource, which the JPA implementation (Hibernate) will need to access the database

The @SpringBootApplication enables Spring component-scanning and Spring Boot
auto-configuration. In fact, @SpringBootApplication combines three other useful
annotations:

- Spring’s @Configuration—Designates a class as a configuration class using Spring’s Java-based configuration. 
- Spring’s @ComponentScan—Enables component-scanning so that the web controller classes and other components you write will be automatically discovered
and registered as beans in the Spring application context. 
- @EnableAutoConfiguration - 

> How does enable auto configuration work ? 
- Each configuration class has an @Configuration annotation and typically uses @Conditional annotations to apply configuration only when specific conditions are met.
- Example 
```import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@Configuration
@ConditionalOnClass(name = "com.example.MyService")
public class MyAutoConfiguration {

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```
- The @EnableAutoConfiguration annotation (or @SpringBootApplication, which includes it) triggers the import of auto-configuration classes.    

- <b>In simple words, if any dependency is present, all the configuration classes and conditions on that are evaluated, and this is done at runtime when we use @SpringBootApplication annotation.</b>

> Cacheable annotation
- As the word suggests, it caches result for any method annotated with @Cacheable annotation. To enable caching we have @EnableCache annotation on the spring boot app. 
- By default , EhCache is enabled by spring, we can configure external caches with it's own set of pros and cons.

> Actuators.
- endpoint health /actuator/health 
  - shows components in the system (app, db's )
- metrics of the machine hosting the app.

> To visit
- Spring WebFlux is a framework for building reactive web applications.
It supports both annotation-based and functional programming models.
It uses non-blocking I/O and reactive programming principles.
It can be used for both server-side web applications and reactive web clients.