cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_6/AuthApp/Docker
docker build -t hw6auth:latest .

minikube image load hw6auth:latest

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_6/AuthApp/Manifest
kubectl apply -f deployment.yaml
kubectl get po                  

kubectl logs hw6auth-deployment-b794db7b6-9fnnz

kubectl apply -f service.yaml
kubectl get svc

kubectl describe svc hw6auth-service
kubectl port-forward svc/hw6auth-service 9000:80
kubectl logs hw6auth-service

minikube addons enable ingress
minikube ip -выводит внешний ip миникуба, который идет на ингресс

kubectl apply -f ingress.yaml
kubectl get ingress
kubectl logs -n ingress-nginx ingress-nginx-controller-768f948f8f-7x67s

helm upgrade --install hw6auth . -n app

------------------------------------

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_6/UserApp/Docker
docker build -t hw6user:latest .

minikube image load hw6user:latest

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Homework_6/UserApp/Manifest
kubectl apply -f deployment.yaml
kubectl get pods
kubectl logs hw6user-deployment-6d9fbbf77d-qt2tp

kubectl apply -f service.yaml
kubectl describe svc hw6user-service
kubectl port-forward svc/hw6user-service 9000:80
kubectl logs hw6user-service

kubectl apply -f ingress.yaml
kubectl get ingress
kubectl logs -n ingress-nginx ingress-nginx-controller-768f948f8f-7x67s

helm upgrade --install hw6user . -n app

-- Database ----------------

helm install hw6db oci://registry-1.docker.io/bitnamicharts/mysql

echo Primary: hw6db-mysql.default.svc.cluster.local:3306

echo Username: root
MYSQL_ROOT_PASSWORD=$(kubectl get secret --namespace default hw6db-mysql -o jsonpath="{.data.mysql-root-password}" | base64 -d)

To connect to your database:

1. Run a pod that you can use as a client:

   kubectl run hw6db-mysql-client --rm --tty -i --restart='Never' --image  docker.io/bitnami/mysql:8.4.3-debian-12-r4 --namespace default --env MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD --command -- bash

2. To connect to primary service (read/write):

   mysql -h hw6db-mysql.default.svc.cluster.local -uroot -p"$MYSQL_ROOT_PASSWORD"



