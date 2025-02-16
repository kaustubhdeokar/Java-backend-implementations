### Filters

- In Spring, the DispatcherServlet is the main servlet, which handles all incoming requests to the application.
- A filter sits before a Servlet and intercepts the
request–response. It has the ability to modify the request–response objects. One or more filters can be configured through a FilterChain, and all filters that are part of the chain can intercept and modify the request–response objects.

![res/filter.png](res/filters.png)

- Depending upon the authentication that spring uses - that specific filter is invoked. Like UsernamePasswordAuthentication for when user uses username/password or Basic-AuthenticationFilter when using basic http spring authentication.

Spring Authentication
- Filter is a concept of the servlet container, from the servlet container (tomcat) it comes to the spring context to the DelegatingProxyFilter.
- DelegatingProxyFilter receives the authentication request.
- it passes on the authentication request to the filter chain.
- These filter chains are maintained by Spring, we can add or remove filters , also adjust their order.
- filter chain - declares the authentication manager which gives us the authentication provider we query to authenticate the user.
- While logging in - we take the authentication provider from the authentication manager and call authenticate method in the provider. 
- The result of this method is set as SecurityContextHolder.getContext().setAuthentication(authentication) - which is the current user.

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


Login flow-
-   Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
-   ProviderManager.authenticate
-   AuthenticationProvider.authenticate
    - Depending on the provider class
    -   retrieveUser 
    -   getUserDetailsService().loadUserByUsername(username);
    -   returns tokens.

- Depending on the provider class, spring will inject appropriate filters.
- We can also inject custom filters

Session management
- stateless
- never
- always

- Authentication using JWTs 
  - Using tokens - access and refresh tokens.
  - access tokens - are short lived token, refresh tokens are long lived.
  - when user logs in, he gets both the tokens. Refresh tokens are stored in the database.
  - access tokens are used to access the resources, if access tokens are expired, refresh tokens are used to get new access tokens.

JWT Token
[header -> (alg, type) ]
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
[payload -> (claims)]
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
[signature - signed with private key]
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

JWE - are encrypted. as JWT are encoded.

There are three types of JWT claims:
  - Registered Claims
    - iss - issuer
    - sub - subject
    - aud - audience
    - exp - expiration
  - Public Claims
  - Private Claims

HMAC - SHA256 
- base 64 encoded secret key - creates the third part - signature by signing the (header and the payload).

ECDSA-256
generates two keys - 
- private key - used to sign & verify the jwt signature
- public key - used to verify the jwt signature.


- Potential issues
  - If an attacker gains access to a JWT's payload, what kind of security risks arise, and how can you mitigate them without encrypting the JWT?
    - Short-lived access tokens (your answer is correct!) – minimizes the window of exploitation.
    - Use refresh tokens stored in HTTPOnly Secure Cookies - HttpOnly is a flag which means Javascript cannot access/modify the cookies.
    - JWT should not contain sensitive information.
  - Explain a real-world attack scenario where an attacker exploits a JWT signed with an insecure algorithm. How would they do it, and how can it be prevented?
    - Attacker can change the algorithm to none and then sign the token with the public key.
    - Use a strong algorithm like RS256.


  - Why don't we directly use the refresh tokens to access the resources ?
    - Because refresh tokens are long lived, if they are compromised, the attacker can use them to get access tokens and access the resources.
    - Access tokens are short lived, so even if they are compromised, they are of no use to the attacker.
- Symmetric
  - User logs in, jwt token is generated and user gets that token, uses it in subsequent requests and then backend authenticates token and gives the resource access to the user
- Asymmetric
  - As above process but jwt using the private key and verified using the public key.

-To revoke JWTs in microservices - we can use a centralized key value store for the same.
- At the microservice level, we can have an asymmetric authentication when we can give the public key to other services to verify the jwt signature.

- If refresh token is compromised, what is the best strategy ? 
  - As refresh tokens are meant to be around for a longer period, the user can create n number of access token with it. 
  - To counter this, we can use the concept of rotating refresh tokens - when the actual user requests for new access tokens, we generate a new refresh token as well and invalidate the older refresh toekn. 
n
  - Hence the compromised refresh token is now of no use.

OAuth authentication
- different types to implement OAuth2. 
  - Authorization code grant
    - Client app redirects user to the Authorization server.
      - The request includes the client ID, redirect URI, requested scopes, and a state parameter for CSRF protection.
      -     ```json
                GET /authorize
                Request Parameters:
                - response_type: "code" (required)
                - client_id: Unique identifier of the client (required)
                - redirect_uri: URL to return to after authorization (required)
                - scope: Space-separated list of permissions (optional)
                - state: Random string for CSRF protection (recommended)
                - prompt: Can be "none", "login", "consent" (optional)
            ```
    - User logs in, authorization server gives the authorization code.
      - Authorization response:
      -     ```json
              HTTP/1.1 302 Found
              Location: {redirect_uri}?
              Response Parameters:
              - code: The authorization code (required)
              - state: Same state value sent in request (required if state was in request)

              Error Response (if authorization fails):
              - error: Error code (e.g., "access_denied", "invalid_request")
              - error_description: Human-readable error message
              - state: Same state value from request
            ```
    - User uses the authorization code to query authorization server for access token.
      - ```json
          POST /token
            Headers:
            - Content-Type: application/x-www-form-urlencoded
            - Authorization: Basic {base64(client_id:client_secret)} (if client authentication is required)

          Request Parameters:
          - grant_type: "authorization_code" (required)
          - code: Authorization code received (required)
          - redirect_uri: Same URI used in authorization request (required)
          - client_id: Client identifier (required if not using Authorization header)
          - client_secret: Client secret (required if not using Authorization header)
        ```
    - Authorization server grants access token after verifying the credentials.
      - ```json
            {
            "access_token": "ACCESS_TOKEN",
            "token_type": "Bearer",
            "expires_in": 3600,
            "refresh_token": "REFRESH_TOKEN"
            }
        ```
    - User sends the access token to the resource server.

+--------+                               +-------------------+
|        |--(A)- Authorization Request ->|   Authorization   |
|        |                               |       Server      |
|        |<-(B)-- Authorization Code ----|                   |
|        |                               |                   |
|        |                               |                   |
|        |--(C)-- Authorization Code --->|                   |
| Client |                               |                   |
|        |<-(D)----- Access Token -------|                   |
|        |                               +-------------------+
|        |                               |                   |
|        |--(E)----- Access Token ------>|                   |
|        |                               |   Resource Server |
|        |<-(F)--- Protected Resource ---|                   |
+--------+                               +-------------------+


#### State
- state helps in csrf protection. Even if any attacker gets hold of the state, he cannot use to it make request to the authorization server.
- the state is created dynamically, by the user session, this is passed on to the authorization server and then authorization server returns it when user is authenticated.
- when the state is returned from the authorization server, we match it to the local state and only proceed if matches.
- Even if the attacker gets hold of the state, he cannot match it to the local state as he would have to get a hold of the user session.
- redirect_uri : registered redirect uris.
- scope : permissions

### Password Grant (Resource Owner Password Credentials)
- This is like giving your house keys directly to someone you trust completely. The application gets the username and password directly from the user. This should only be used when there's a high level of trust, typically for first-party applications:
- In the Password Grant flow, the application directly collects the username and password from the user and exchanges them for an access token. 
- Think of it like giving your email and password directly to a trusted app instead of being redirected to the email provider's login page.
- First, your application collects credentials from the user, typically through a login form. Then it makes a direct POST request to the authorization server
- Then the auth server returns the credentials - access token, permissions(scope), refresh token


Implicit grant techique
- step (A) is same, but after logging in to the server the user directly gets hold of the access token (E).

+--------+                               +-------------------+
|        |--(A)- Authorization Request ->|   Authorization   |
|        |                               |       Server      |
|        |<-(B)---- Access Token --------|                   |
|        |                               +-------------------+
|        |                               |                   |
|        |--(C)----- Access Token ------>|                   |
| Client |                               |                   |
|        |<-(D)--- Protected Resource ---|                   |
+--------+                               +-------------------+


- Disadvantages:
- Access Token Exposure: The access token is returned in the URL fragment, which is accessible to the user agent (e.g., web browser). This makes it more susceptible to interception by malicious scripts or browser extensions.
- no refresh tokens - short lived tokens.

1. Client Credentials Flow: This flow is used for machine-to-machine communication where the client authenticates directly with the authorization server using its credentials to obtain an access token.

2. Hybrid Flow: This flow is a combination of the Authorization Code Flow and the Implicit Flow. It allows the client to receive some tokens directly from the authorization endpoint and others from the token endpoint.

> With PKCE authorization + Authorization Code grant. 

- in the first request we send, the code challenge verifier (base 64 string) + the code challenge (sha256 of code challenge)
- the authorization server response is the unchanged..
- in the third request, we send the code challenge verifier as an additional parameter.

> Symmetric key encryption
- same key exchanged between auth & resource server (in terms in oauth).
- you don't want your resource server to act as it can create keys. just verify it.
![symmetric-key-jwt.png](res/symmetric-key-jwt.png)