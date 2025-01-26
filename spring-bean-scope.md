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