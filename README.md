# Videos On Demand - RMI Application
## By Badr Al Achkar and Louis Calas


### This project simulates an online platform that delivers videos on demand, through an RMI client-server JAVA application.

### The project uses maven with a pom.xml included in the Client and Server to make use of the JSONPath package in the creation of a local document database (json based) to store the data (users and movies).<br>

### The project is divided into three distinct parts <br>

#### HTTPServer - which provides the basic functionality of a mini web server.

It is used to automatically send back requested .class by JVMs. 

- It takes 2 arguments at start which are : the port to be started on, 
and the path to the directory containing the .class files <br>
An example of input for our case is : 2001 "..\Server\target\classes" <br>
To access the .class contained in the server using the relative path.

#### Server - which simulates the server hosting the VOD service

- Must be started with the JVM Option codebase bound to our HTTPServer domain name and port which is printed on the console of the HTTPServer upon startup for ease of copy. 

- Creates an RMIRegistry and binds the distant objects to it

#### Client - which simulates the client connecting to the server to access the VOD service.

### Data management
- Done on server side within the "db" package and uses a json file.
This one contains a DAO class, used to get data from JSON file.

- A file reader is associated to the json file storing data.
File reader is parsed to get a JSONObject.

- Movies dao class used to manage movies data from JSONObject generated by DAO class.

- Users dao class used to manage users data from JSONObject generated by DAO class.

- Model classes Movie and User.

- JSON file representing the document database : "db.json" format example : `{"movies":[{"ISBN": ..., "movie": ... }], "catalog":[{"trailer: ..., "ISBN":..., "name": ... , "synopsis": ...}], "users":[{"password": ..., "email": ...}]`

### How the application works

In this order :

- Launch the HTTP server (HTTPServer) from the main method of the CLassFileServer class with the port and the path as specified earlier.


- Launch the server (Server) from the main method of the Main class, with the JVM Option of the code base, for example : -Djava.rmi.server.codebase=http://DESKTOP-U4R1V9R:2001/


- Launch the client from the main method of the Main class.
  You can then access the VOD service from the terminal that launched the client.

