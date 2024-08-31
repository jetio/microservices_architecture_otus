# Домашние задания по курсу Микросервисная архитектура
## Заметки
* Если при попытке сделать push выдается ошибка Permission denied (publickey), это означает, что SSH-агент "потерял" 
ключ, расположенный по адресу ~/.ssh/ssh_key_github (например, после перезагрузки ПК). 
В этом случае нужно добавить ключ из указанного файла в память SSH-агента, выполнив: 
> ssh-add ~/.ssh/ssh_key_github

* Адрес проекта в GitHub: https://github.com/jetio/microservices_architecture_otus