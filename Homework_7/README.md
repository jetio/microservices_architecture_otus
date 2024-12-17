chat.deepseek.com query:

give me java spring boot code for rest service for billing with user registration 
method that creates account, money withdrawal method, money refill method. 
Use in-memory database H2. 
Root package must be "ru.otus.hw7.billing". 
Supply it with pom.xml, OpenApi specification, application configuration file in yaml format. 
Thankyou!

give me java spring boot code for rest service for notification with two methods. 
First one POST method for saving message into in-memory database. 
Second one GET method for retrieving all posted messages.
Use in-memory database H2.
Root java package must be "ru.otus.hw7.notification". 
Supply it with pom.xml file, dockerfile, OpenApi specification for this service, application configuration file in yaml format.
Thankyou!

give me java spring boot code for rest service for user order processing with two methods. 
First one - POST method for creating order with two query parameters: username, amount. 
Method should call another rest-service by POST-request to URL from application config - /api/billing/withdraw to withdraw money. 
If withdrawal is successful, method calls another rest-service by POST-request to URL from application config - /api/notifications to notify user about an order.  
Second one - GET method for retrieving all user orders.
Use in-memory database H2.
Root java package must be "ru.otus.hw7.order".
Supply it with OpenApi specification for this service, application configuration file in yaml format, pom.xml file
Thankyou!

Run the application using your IDE or mvn spring-boot:run.
Access the H2 console at http://localhost:8081/h2-console.
Use the OpenAPI UI at http://localhost:8081/swagger-ui.html