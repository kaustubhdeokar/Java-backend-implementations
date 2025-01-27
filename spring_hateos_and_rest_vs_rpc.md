### Spring HATEOS 

- https://spring.io/guides/tutorials/rest

> Question to co-pilot 
- The site described a crud employee application, over spring boot, it was built using spring mvc, after the example it had these lines written by rob holding. ""I am getting frustrated by the number of people calling any HTTP-based interface a REST API. Today’s example is the SocialSite REST API. That is RPC. It screams RPC. There is so much coupling on display that it should be given an X rating.

- What needs to be done to make the REST architectural style clear on the notion that hypertext is a constraint? In other words, if the engine of application state (and hence the API) is not being driven by hypertext, then it cannot be RESTful and cannot be a REST API. Period. Is there some broken manual somewhere that needs to be fixed? "" In these quote the SocialSite rest api is the rest api i described earlier, (Employee CRUD).. having that in context can you please explain me what exactly is difference rpc and rest ?


> Response
- The quote you provided highlights a common misunderstanding between REST (Representational State Transfer) and RPC (Remote Procedure Call). The key point made by Rob Holding is that many so-called "REST APIs" are actually RPC in disguise because they do not adhere to the true principles of REST, particularly the use of hypertext as the engine of application state (HATEOAS).

Key Differences Between REST and RPC:
Resource Orientation vs. Action Orientation:

- REST: Focuses on resources and their representations. Each resource is identified by a URI, and standard HTTP methods (GET, POST, PUT, DELETE) are used to perform operations on these resources.
RPC: Focuses on actions or procedures. It involves calling methods or functions on a remote server, often with a specific protocol like JSON-RPC or XML-RPC.
Hypermedia as the Engine of Application State (HATEOAS):

REST: A true RESTful API uses hypermedia (hyperlinks) to guide the client through the application. The client discovers actions and resources dynamically through links provided in the responses.
RPC: Does not use hypermedia. The client knows the exact methods to call and their parameters, leading to tighter coupling between client and server.
Statelessness:

REST: Each request from the client to the server must contain all the information needed to understand and process the request. The server does not store any client context between requests.
RPC: Can be either stateful or stateless, depending on the implementation.
Uniform Interface:

REST: Uses a uniform interface with standard HTTP methods and status codes. The operations are defined by the HTTP methods (e.g., GET for reading, POST for creating).
RPC: Uses a specific protocol and method names. The operations are defined by the method names (e.g., getUser, createOrder).
Example of RESTful API:
In this example:

The client can discover the available actions (update, delete) through the _links section.
The API is resource-oriented, focusing on the employee resource.
Example of RPC-style API:
In this example:

The client calls a specific method (getEmployee) with parameters.
The API is action-oriented, focusing on the method call.
Summary:
REST: Resource-oriented, uses standard HTTP methods, stateless, and employs hypermedia (HATEOAS) to guide the client.
RPC: Action-oriented, uses specific methods and protocols, can be stateful or stateless, and does not use hypermedia.
Rob Holding's frustration is that many APIs labeled as "REST" do not adhere to these principles, particularly the use of hypermedia, and are more accurately described as RPC. To be truly RESTful, an API must use hypermedia to drive the application state and provide a uniform interface for interacting with resources.

So spring provides us a library to develop rest-oriented architectures - HATEOS.
