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


### Отладка Service/Ingress
Убедитесь, что Ingress-контроллер правильно настроен и работает:
kubectl get pods -n ingress-nginx
Убедитесь, что под ingress-nginx-controller запущен и в статусе Running.
Проверьте, что ваш Ingress-ресурс правильно создан:
kubectl get ingress
Убедитесь, что ваш Ingress-ресурс присутствует в списке.
Проверьте конфигурацию Ingress-ресурса:
kubectl describe ingress <имя-вашего-ingress>
Убедитесь, что правила маршрутизации настроены правильно.
Проверьте, что DNS-запись arch.homework указывает на IP-адрес Ingress-контроллера:
kubectl get svc -n ingress-nginx
Найдите внешний IP-адрес сервиса ingress-nginx-controller.
Добавьте запись в файл /etc/hosts (если вы еще не сделали это):
<IP-адрес-ingress-контроллера> arch.homework
Проверьте логи Ingress-контроллера:
kubectl logs -n ingress-nginx <pod-name-ingress-nginx-controller>
Ищите любые ошибки или предупреждения, связанные с вашим запросом.
Убедитесь, что ваш сервис правильно настроен и доступен:
kubectl get svc
kubectl describe svc <имя-сервиса>
Проверьте, что поды вашего приложения работают корректно:
kubectl get pods
kubectl describe pod <имя-пода>
Попробуйте сделать запрос непосредственно к сервису, минуя Ingress:
kubectl port-forward svc/<имя-сервиса> 8080:80
Затем в другом терминале:
curl http://localhost:8080/health

## ДЗ №4 - Основы работы с HELM
brew install helm - установка
==> Summary
🍺  /usr/local/Cellar/helm/3.16.2

### Добавляем репозиторий Heml:
(base) andrey_smirnov@mbp-andrej ~ % helm repo list
Error: no repositories to show
(base) andrey_smirnov@mbp-andrej ~ % helm repo add stable https://charts.helm.sh/stable
"stable" has been added to your repositories
(base) andrey_smirnov@mbp-andrej ~ % helm repo list
NAME  	URL                          
stable	https://charts.helm.sh/stable
(base) andrey_smirnov@mbp-andrej ~ % 

### Обновляем список приложений в добавленном репозитории:
(base) andrey_smirnov@mbp-andrej ~ % helm repo update
Hang tight while we grab the latest from your chart repositories...
...Successfully got an update from the "stable" chart repository
Update Complete. ⎈Happy Helming!⎈

Вывод всех приложений в репозитории
helm search repo stable

### Устанавливаем mysql
helm install hw4-1 oci://registry-1.docker.io/bitnamicharts/mysql

Pulled: registry-1.docker.io/bitnamicharts/mysql:11.1.19
Digest: sha256:e27f7f7721cf72908bbc7b15ce2f172cffeb18282b1c5217310e295d71cac6a0
NAME: hw4-1
LAST DEPLOYED: Sat Oct 19 23:21:07 2024
NAMESPACE: app
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
CHART NAME: mysql
CHART VERSION: 11.1.19
APP VERSION: 8.4.3

** Please be patient while the chart is being deployed **

Tip:

Watch the deployment status using the command: kubectl get pods -w --namespace app

Services:

echo Primary: hw4-1-mysql.app.svc.cluster.local:3306

Execute the following to get the administrator credentials:

echo Username: root
MYSQL_ROOT_PASSWORD=$(kubectl get secret --namespace app hw4-1-mysql -o jsonpath="{.data.mysql-root-password}" | base64 -d)

To connect to your database:

1. Run a pod that you can use as a client:

   kubectl run hw4-1-mysql-client --rm --tty -i --restart='Never' --image  docker.io/bitnami/mysql:8.4.3-debian-12-r0 --namespace app --env MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD --command -- bash

2. To connect to primary service (read/write):

   mysql -h hw4-1-mysql.app.svc.cluster.local -uroot -p"$MYSQL_ROOT_PASSWORD"


WARNING: There are "resources" sections in the chart not set. Using "resourcesPreset" is not recommended for production. For production installations, please set the following values according to your workload needs:
- primary.resources
- secondary.resources
  +info https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/

Проверяем поды
(base) andrey_smirnov@mbp-andrej ~ % kubectl get po
NAME                                 READY   STATUS    RESTARTS        AGE
hw3app-deployment-79b647f84d-8fbww   1/1     Running   1 (7d ago)      7d6h
hw3app-deployment-79b647f84d-l6tkk   1/1     Running   3 (3m31s ago)   7d6h
hw3app-deployment-79b647f84d-pk9q2   1/1     Running   2               7d6h
hw3app-deployment-79b647f84d-qrx5p   1/1     Running   2 (8m20s ago)   7d6h
hw4-1-mysql-0                        0/1     Running   4 (40s ago)     9m39s
(base) andrey_smirnov@mbp-andrej ~ % kubectl get po
Unable to connect to the server: net/http: TLS handshake timeout

(base) andrey_smirnov@mbp-andrej ~ % kubectl get po
NAME                                 READY   STATUS             RESTARTS        AGE
hw3app-deployment-79b647f84d-8fbww   1/1     Running            2 (6m13s ago)   7d6h
hw3app-deployment-79b647f84d-l6tkk   1/1     Running            3 (10m ago)     7d6h
hw3app-deployment-79b647f84d-pk9q2   1/1     Running            2               7d6h
hw3app-deployment-79b647f84d-qrx5p   1/1     Running            2 (14m ago)     7d6h
hw4-1-mysql-0                        0/1     CrashLoopBackOff   6 (48s ago)     16m

(base) andrey_smirnov@mbp-andrej ~ % kubectl describe pod hw4-1-mysql-0
Name:             hw4-1-mysql-0
Namespace:        app
Priority:         0
Service Account:  hw4-1-mysql
Node:             minikube/192.168.64.2
Start Time:       Sat, 19 Oct 2024 23:21:11 +0300
Labels:           app.kubernetes.io/component=primary
app.kubernetes.io/instance=hw4-1
app.kubernetes.io/managed-by=Helm
app.kubernetes.io/name=mysql
app.kubernetes.io/version=8.4.3
apps.kubernetes.io/pod-index=0
controller-revision-hash=hw4-1-mysql-5586559549
helm.sh/chart=mysql-11.1.19
statefulset.kubernetes.io/pod-name=hw4-1-mysql-0
Annotations:      checksum/configuration: 3eb0cca79d46951970857bf49657b60b07267e749d6da6662527da2d18229e42
Status:           Running
IP:               10.244.0.21
IPs:
IP:           10.244.0.21
Controlled By:  StatefulSet/hw4-1-mysql
Init Containers:
preserve-logs-symlinks:
Container ID:    docker://50ace727ad6e8d9b315579696ed0a142bcbe28701b9b3a42f909ddb9f8826214
Image:           docker.io/bitnami/mysql:8.4.3-debian-12-r0
Image ID:        docker-pullable://bitnami/mysql@sha256:a78fa42d3af20c19bb295e473f5cfa231a7f9643072e602e6e3229285465e173
Port:            <none>
Host Port:       <none>
SeccompProfile:  RuntimeDefault
Command:
/bin/bash
Args:
-ec
#!/bin/bash

      . /opt/bitnami/scripts/libfs.sh
      # We copy the logs folder because it has symlinks to stdout and stderr
      if ! is_dir_empty /opt/bitnami/mysql/logs; then
        cp -r /opt/bitnami/mysql/logs /emptydir/app-logs-dir
      fi
      
    State:          Terminated
      Reason:       Completed
      Exit Code:    0
      Started:      Sat, 19 Oct 2024 23:22:09 +0300
      Finished:     Sat, 19 Oct 2024 23:22:09 +0300
    Ready:          True
    Restart Count:  0
    Limits:
      cpu:                750m
      ephemeral-storage:  2Gi
      memory:             768Mi
    Requests:
      cpu:                500m
      ephemeral-storage:  50Mi
      memory:             512Mi
    Environment:          <none>
    Mounts:
      /emptydir from empty-dir (rw)
Containers:
mysql:
Container ID:    docker://e4ab9f44889411c68557a1fe2e87993bd9518419f3756ef3f5017e5ef22ce614
Image:           docker.io/bitnami/mysql:8.4.3-debian-12-r0
Image ID:        docker-pullable://bitnami/mysql@sha256:a78fa42d3af20c19bb295e473f5cfa231a7f9643072e602e6e3229285465e173
Port:            3306/TCP
Host Port:       0/TCP
SeccompProfile:  RuntimeDefault
State:           Waiting
Reason:        CrashLoopBackOff
Last State:      Terminated
Reason:        Completed
Exit Code:     0
Started:       Sun, 20 Oct 2024 20:45:54 +0300
Finished:      Sun, 20 Oct 2024 20:47:48 +0300
Ready:           False
Restart Count:   139
Limits:
cpu:                750m
ephemeral-storage:  2Gi
memory:             768Mi
Requests:
cpu:                500m
ephemeral-storage:  50Mi
memory:             512Mi
Liveness:             exec [/bin/bash -ec password_aux="${MYSQL_ROOT_PASSWORD:-}"
if [[ -f "${MYSQL_ROOT_PASSWORD_FILE:-}" ]]; then
password_aux=$(cat "$MYSQL_ROOT_PASSWORD_FILE")
fi
mysqladmin status -uroot -p"${password_aux}"
] delay=5s timeout=1s period=10s #success=1 #failure=3
Readiness:  exec [/bin/bash -ec password_aux="${MYSQL_ROOT_PASSWORD:-}"
if [[ -f "${MYSQL_ROOT_PASSWORD_FILE:-}" ]]; then
password_aux=$(cat "$MYSQL_ROOT_PASSWORD_FILE")
fi
mysqladmin ping -uroot -p"${password_aux}" | grep "mysqld is alive"
] delay=5s timeout=1s period=10s #success=1 #failure=3
Startup:  exec [/bin/bash -ec password_aux="${MYSQL_ROOT_PASSWORD:-}"
if [[ -f "${MYSQL_ROOT_PASSWORD_FILE:-}" ]]; then
password_aux=$(cat "$MYSQL_ROOT_PASSWORD_FILE")
fi
mysqladmin ping -uroot -p"${password_aux}" | grep "mysqld is alive"
] delay=15s timeout=1s period=10s #success=1 #failure=10
Environment:
BITNAMI_DEBUG:        false
MYSQL_ROOT_PASSWORD:  <set to the key 'mysql-root-password' in secret 'hw4-1-mysql'>  Optional: false
MYSQL_PORT:           3306
MYSQL_DATABASE:       my_database
Mounts:
/bitnami/mysql from data (rw)
/opt/bitnami/mysql/conf from empty-dir (rw,path="app-conf-dir")
/opt/bitnami/mysql/conf/my.cnf from config (rw,path="my.cnf")
/opt/bitnami/mysql/logs from empty-dir (rw,path="app-logs-dir")
/opt/bitnami/mysql/tmp from empty-dir (rw,path="app-tmp-dir")
/tmp from empty-dir (rw,path="tmp-dir")
Conditions:
Type                        Status
PodReadyToStartContainers   True
Initialized                 True
Ready                       False
ContainersReady             False
PodScheduled                True
Volumes:
data:
Type:       PersistentVolumeClaim (a reference to a PersistentVolumeClaim in the same namespace)
ClaimName:  data-hw4-1-mysql-0
ReadOnly:   false
config:
Type:      ConfigMap (a volume populated by a ConfigMap)
Name:      hw4-1-mysql
Optional:  false
empty-dir:
Type:        EmptyDir (a temporary directory that shares a pod's lifetime)
Medium:      
SizeLimit:   <unset>
QoS Class:       Burstable
Node-Selectors:  <none>
Tolerations:     node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
Type     Reason     Age                     From     Message
  ----     ------     ----                    ----     -------
Normal   Pulled     125m (x140 over 21h)    kubelet  Container image "docker.io/bitnami/mysql:8.4.3-debian-12-r0" already present on machine
Warning  Failed     125m (x5 over 173m)     kubelet  Error: failed to sync secret cache: timed out waiting for the condition
Warning  BackOff    9m39s (x1603 over 21h)  kubelet  Back-off restarting failed container mysql in pod hw4-1-mysql-0_app(d1b76dc7-eaca-4e19-9b3f-f45a087bd9db)
Warning  Unhealthy  6m1s (x873 over 21h)    kubelet  Startup probe failed: command "/bin/bash -ec password_aux=\"${MYSQL_ROOT_PASSWORD:-}\"\nif [[ -f \"${MYSQL_ROOT_PASSWORD_FILE:-}\" ]]; then\n    password_aux=$(cat \"$MYSQL_ROOT_PASSWORD_FILE\")\nfi\nmysqladmin ping -uroot -p\"${password_aux}\" | grep \"mysqld is alive\"\n" timed out
(base) andrey_smirnov@mbp-andrej ~ %

### Ошибка
(base) andrey_smirnov@mbp-andrej ~ % kubectl logs pods hw4-1-mysql-0        
Unable to connect to the server: net/http: TLS handshake timeout

### Устанавливаем дефолтный NS
kubectl config set-context --current --namespace=app

### Удаляем установку mysql
helm delete hw4-1

---------------- mysql ------------------

sh-5.1# mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 9.0.1 MySQL Community Server - GPL

Copyright (c) 2000, 2024, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| testdb             |
+--------------------+
5 rows in set (0.15 sec)

mysql> use testdb;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed

mysql> CREATE TABLE users (id int, username varchar(255), firstName varchar(255), lastName varchar(255), email varchar(255), phone varchar(255));


minikube image load hw4app:latest
helm upgrade --install hw4 . -n app

