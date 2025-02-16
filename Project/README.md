cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Docker
docker compose up -d
docker compose down

### Собираем образы
cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_Auth
docker build -t prj-auth:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_Order
docker build -t prj-order:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_Trade
docker build -t prj-trade:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_Security
docker build -t prj-security:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_User
docker build -t prj-user:latest .

cd /Users/andrey_smirnov/IdeaProjects/microservices_architecture_otus/Project/Ex_GW
docker build -t prj-gw:latest .

### Презентация
https://fokus.am/p/ryLdzUDaE5x6