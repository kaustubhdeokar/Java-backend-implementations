### Filters

- In Spring, the DispatcherServlet is the main servlet, which handles all incoming requests to the application.
- A filter sits before a Servlet and intercepts the
request–response. It has the ability to modify the request–response objects, as shown in
figure 5.6. One or more filters can be configured through a FilterChain, and all filters that are part of the chain can intercept and modify the request–response objects.

![res/filter.png](res/filters.png)

- Depending upon the authentication that spring uses - that specific filter is invoked. Like UsernamePasswordAuthentication for when user uses username/password or Basic-AuthenticationFilter when using basic http spring authentication.

Spring Authentication
- Delegating Filter proxy receives the authentication request.
- it passes on the authentication request to the filter chain.
- These filter chains are maintained by Spring, we can add or remove filters , also adjust their order.
- filter chain - declares the authentication manager which gives us the authentication provider we query to authenticate the user.

- While logging in - we take the authentication provider from the authentication manager and call authenticate method in the provider. the result of this method is set as SecurityContextHolder.getContext().setAuthentication(authentication) - which is the current user.

![simple-auth.png](simple-auth.png)

Filter - Chain
    ^       ^
    |       |
This authentication provider has to be configured in the auth manager method in spring security configuration class.
    ^       ^
    |       |
Authentication Provider - like DaoAuthenticationProvider or JwtAuthenticationProvider
    - UserDetailsService object 
    ^       ^
    |       |
This user has to be formed/returned from the method loadUserByUsername in a class which is implements UserDetailsService 
    ^       ^
    |       |
The user that we return/or create - is an implementation of the class UserDetails


Session management
- stateless
- never
- always