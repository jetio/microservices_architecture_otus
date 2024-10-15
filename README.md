# Домашние задания по курсу Микросервисная архитектура
## Заметки
* Если при попытке сделать push выдается ошибка Permission denied (publickey), это означает, что SSH-агент "потерял" 
ключ, расположенный по адресу ~/.ssh/ssh_key_github (например, после перезагрузки ПК). 
В этом случае нужно добавить ключ из указанного файла в память SSH-агента, выполнив: 
> ssh-add ~/.ssh/ssh_key_github

* Адрес проекта в GitHub: https://github.com/jetio/microservices_architecture_otus

## Инструкция по установке minikube
* Проверка поддержки виртуализации sysctl -a | grep -E --color 'machdep.cpu.features|VMX'
* brew install kubectl -- установка kubectl
* kubectl version --client -- вывод версии kubectl
* brew install minikube -- установка minikube
* cd /usr/local/Cellar/minikube -- сюда установлен minikube
* minikube start --vm-driver=virtualbox -- так не заработало
* minikube delete -- удаление кластера 
* brew install hyperkit -- установка гипервизора для macOs
* brew --prefix hyperkit -- покажет, куда установлен hyperkit (/usr/local/opt/hyperkit). Нужно добавить в PATH в файле .zprofile (аналог .bashrc)
* minikube start или minikube start --driver=hyperkit -- так заработало
* minikube status -- выводит статус кластера: 
* minikube stop -- останов кластера

## ДЗ №3 - Основы работы с K8
(base) andrey_smirnov@mbp-andrej microservices_architecture_otus % cd Homework_3
(base) andrey_smirnov@mbp-andrej Homework_3 % ls
Examples        deployment.yaml ingress.yaml    service.yaml
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl apply -f deployment.yaml
deployment.apps/hw3app-deployment created
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po                                              
NAME                                 READY   STATUS         RESTARTS   AGE
hw3app-deployment-667cbf99fd-dvdtf   0/1     ErrImagePull   0          46s
hw3app-deployment-667cbf99fd-t4mrn   0/1     ErrImagePull   0          46s
hw3app-deployment-667cbf99fd-thx9k   0/1     ErrImagePull   0          46s
(base) andrey_smirnov@mbp-andrej Homework_3 % minikube docker-env
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.64.2:2376"
export DOCKER_CERT_PATH="/Users/andrey_smirnov/.minikube/certs"
export MINIKUBE_ACTIVE_DOCKERD="minikube"

# To point your shell to minikube's docker-daemon, run:
# eval $(minikube -p minikube docker-env)
(base) andrey_smirnov@mbp-andrej Homework_3 % minikube image ls --format table
|-----------------------------------------|----------|---------------|--------|
|                  Image                  |   Tag    |   Image ID    |  Size  |
|-----------------------------------------|----------|---------------|--------|
| registry.k8s.io/pause                   | 3.9      | e6f1816883972 | 744kB  |
| gcr.io/k8s-minikube/storage-provisioner | v5       | 6e38f40d628db | 31.5MB |
| registry.k8s.io/kube-apiserver          | v1.30.0  | c42f13656d0b2 | 117MB  |
| registry.k8s.io/kube-controller-manager | v1.30.0  | c7aad43836fa5 | 111MB  |
| registry.k8s.io/kube-scheduler          | v1.30.0  | 259c8277fcbbc | 62MB   |
| registry.k8s.io/kube-proxy              | v1.30.0  | a0bf559e280cf | 84.7MB |
| registry.k8s.io/etcd                    | 3.5.12-0 | 3861cfcd7c04c | 149MB  |
| registry.k8s.io/coredns/coredns         | v1.11.1  | cbb01a7bd410d | 59.8MB |
|-----------------------------------------|----------|---------------|--------|
(base) andrey_smirnov@mbp-andrej Homework_3 % minikube image load msc-arch-hw2:latest
(base) andrey_smirnov@mbp-andrej Homework_3 % minikube image ls --format table       
|-----------------------------------------|----------|---------------|--------|
|                  Image                  |   Tag    |   Image ID    |  Size  |
|-----------------------------------------|----------|---------------|--------|
| gcr.io/k8s-minikube/storage-provisioner | v5       | 6e38f40d628db | 31.5MB |
| registry.k8s.io/kube-scheduler          | v1.30.0  | 259c8277fcbbc | 62MB   |
| registry.k8s.io/kube-controller-manager | v1.30.0  | c7aad43836fa5 | 111MB  |
| registry.k8s.io/kube-proxy              | v1.30.0  | a0bf559e280cf | 84.7MB |
| registry.k8s.io/pause                   | 3.9      | e6f1816883972 | 744kB  |
| docker.io/library/msc-arch-hw2          | latest   | 47c14207a975d | 192MB  |
| registry.k8s.io/kube-apiserver          | v1.30.0  | c42f13656d0b2 | 117MB  |
| registry.k8s.io/etcd                    | 3.5.12-0 | 3861cfcd7c04c | 149MB  |
| registry.k8s.io/coredns/coredns         | v1.11.1  | cbb01a7bd410d | 59.8MB |
|-----------------------------------------|----------|---------------|--------|
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl apply -f deployment.yaml       
deployment.apps/hw3app-deployment unchanged
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl apply -f deployment.yaml
deployment.apps/hw3app-deployment configured
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po                         
NAME                                 READY   STATUS             RESTARTS   AGE
hw3app-deployment-667cbf99fd-dvdtf   0/1     ImagePullBackOff   0          23m
hw3app-deployment-667cbf99fd-qb9wt   0/1     ErrImagePull       0          11s
hw3app-deployment-667cbf99fd-t4mrn   0/1     ImagePullBackOff   0          23m
hw3app-deployment-667cbf99fd-thx9k   0/1     ImagePullBackOff   0          23m
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po
NAME                                 READY   STATUS             RESTARTS   AGE
hw3app-deployment-667cbf99fd-dvdtf   0/1     ImagePullBackOff   0          23m
hw3app-deployment-667cbf99fd-qb9wt   0/1     ImagePullBackOff   0          24s
hw3app-deployment-667cbf99fd-t4mrn   0/1     ImagePullBackOff   0          23m
hw3app-deployment-667cbf99fd-thx9k   0/1     ImagePullBackOff   0          23m
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po
NAME                                 READY   STATUS             RESTARTS   AGE
hw3app-deployment-667cbf99fd-dvdtf   0/1     ImagePullBackOff   0          24m
hw3app-deployment-667cbf99fd-qb9wt   0/1     ImagePullBackOff   0          90s
hw3app-deployment-667cbf99fd-t4mrn   0/1     ImagePullBackOff   0          24m
hw3app-deployment-667cbf99fd-thx9k   0/1     ImagePullBackOff   0          24m
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl apply -f deployment.yaml
deployment.apps/hw3app-deployment configured
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po                  
NAME                                 READY   STATUS              RESTARTS   AGE
hw3app-deployment-667cbf99fd-dvdtf   0/1     Terminating         0          29m
hw3app-deployment-667cbf99fd-t4mrn   0/1     ImagePullBackOff    0          29m
hw3app-deployment-667cbf99fd-thx9k   0/1     Terminating         0          29m
hw3app-deployment-79b647f84d-8fbww   0/1     ContainerCreating   0          1s
hw3app-deployment-79b647f84d-l6tkk   1/1     Running             0          4s
hw3app-deployment-79b647f84d-pk9q2   1/1     Running             0          4s
(base) andrey_smirnov@mbp-andrej Homework_3 % kubectl get po
NAME                                 READY   STATUS    RESTARTS   AGE
hw3app-deployment-79b647f84d-8fbww   1/1     Running   0          8s
hw3app-deployment-79b647f84d-l6tkk   1/1     Running   0          11s
hw3app-deployment-79b647f84d-pk9q2   1/1     Running   0          11s
hw3app-deployment-79b647f84d-qrx5p   1/1     Running   0          7s