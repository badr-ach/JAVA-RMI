# Videos On Demand - RMI Application
###  by Badr Al Achkar and Louis Calas
### This project simulates an online platform that delivers videos on demand, through an RMI client-server JAVA application.

### The project is divided into three distinct parts.
   

#### HTTPServer - which provides the basic functionality of a mini web server.
- The ClassServer creates a thread that listens on a socket
  and accepts HTTP GET requests. The HTTP response contains the
  bytecodes for the class that requested in the GET header.  
   
  
- The ClassFileServer implements a ClassServer that
  reads class files from the file system.
  

- For loading remote classes, an RMI application can use a concrete
  subclass of this server in place of an HTTP server.

#### Server - which simulates the server hosting the VOD service
- Obtains a reference to a remote object registry on specified port.
  

- Binds the remote reference from the specified name in this registry.
  

- Specifies device and port for the server.

#### Client - which simulates the client connecting to the server to access the VOD service.

### Data management
- Done on server side within the "db" package and use a json file.
This one contains a DAO class, used to get data from JSON file.


- A file reader is associated to the json file storing data
File reader is parsed to get a JSONObject.


- Movies manager class to manage movies data from JSONObject generated by DAO class.


- Users manager class to manage users data from JSONObject generated by DAO class.


- Model classes Movie and User.


- JSON file "db.json" format example : `{"movies":[{"ISBN": ..., "movie": ... }], "catalog":[{"trailer: ..., "ISBN":..., "name": ... , "synopsis": ...}], "users":[{"password": ..., "email": ...}]`

### How the application works

In this order :

- Launch the HTTP server (HTTPServer) from the main method of the CLassFileServer class


- Launch the server (Server) from the main method of the Main class
  You must first modify the machine and the port specified in the main method in the following function
  the following function: `System.setProperty("java.rmi.server.codebase", "http://yourdevice:port/");"`
  They are specified when launching HTTPServer.


- Launch the client from the main method of the Main class.
  You can then access the VOD service from the terminal that launched the client.
