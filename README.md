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
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured
* minikube stop -- останов кластера  
