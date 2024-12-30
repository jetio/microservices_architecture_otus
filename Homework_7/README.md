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

### Как посмотреть OpenApi
Run the application using your IDE or mvn spring-boot:run.
Access the H2 console at http://localhost:8081/h2-console.
Use the OpenAPI UI at http://localhost:8081/swagger-ui.html

### Собираем образы
cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/BillingService
docker build -t hw7billing:3 .
docker build -t hw7billing:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/NotificationService
docker build -t hw7notification:3 .
docker build -t hw7notification:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/OrderService
docker build -t hw7order:3 .
docker build -t hw7order:latest .

### Загружаем образы в minikube
minikube image load hw7billing:latest
minikube image load hw7notification:latest
minikube image load hw7order:latest

minikube image load hw7billing:3
minikube image load hw7notification:3
minikube image load hw7order:3

### Деплоим сервисы через helm
cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/BillingService/helmChart
helm install hw7billing . --namespace hw7 --create-namespace
helm upgrade --install hw7billing . --namespace hw7
если надо удалить, то 
helm delete hw7billing --namespace hw7


cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/NotificationService/helmChart
helm install hw7notification . --namespace hw7
helm upgrade --install hw7notification . --namespace hw7
если надо удалить, то 
helm delete hw7notification --namespace hw7


cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_7/OrderService/helmChart
helm install hw7order . --namespace hw7
helm upgrade --install hw7order . --namespace hw7
если надо удалить, то 
helm delete hw7order --namespace hw7


### Отладка
minikube image ls
kubectl get pods -n hw7  

kubectl logs hw7billing-deployment-746f8586cf-gfhjc -n hw7

kubectl get ingress -n hw7
kubectl describe ingress hw7order-ingress -n hw7
kubectl describe ingress hw7billing-ingress -n hw7
kubectl describe service hw7order-service -n hw7

kubectl get service -n hw7

kubectl logs hw7order-service -n hw7

kubectl get po -n ingress-nginx

kubectl logs -n ingress-nginx ingress-nginx-controller-768f948f8f-7x67s

kubectl port-forward svc/hw7billing-service -n hw7 8080:80
