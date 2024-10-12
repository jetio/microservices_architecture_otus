## ДЗ №3 - Основы работы с K8smini
COMMAND_MODE=unix2003
DBUS_LAUNCHD_SESSION_BUS_SOCKET=/private/tmp/com.apple.launchd.JitXLafDfo/unix_domain_listener
HOME=/Users/andrey_smirnov
JETBRAINS_INTELLIJ_ZSH_DIR='/Applications/IntelliJ IDEA CE.app/Contents/plugins/terminal/shell-integrations/zsh'
LC_CTYPE=UTF-8
LOGNAME=andrey_smirnov
OLDPWD=/Users/andrey_smirnov/Documents/Study/microservices_architecture_otus/Homework_2
PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin
PWD=/Users/andrey_smirnov/Documents/Study/microservices_architecture_otus/Homework_2
SHELL=/bin/zsh
SHLVL=1
SSH_AUTH_SOCK=/private/tmp/com.apple.launchd.heHKo3GqcD/Listeners
TERM=xterm-256color
TERMINAL_EMULATOR=JetBrains-JediTerm
TERM_SESSION_ID=a49c16aa-7bf2-4d93-ac0e-7ebe59b000fe
TMPDIR=/var/folders/87/jbvg003s2dvgxs305rqpv35h0000gn/T/
USER=andrey_smirnov
XPC_FLAGS=0x0
XPC_SERVICE_NAME=application.com.jetbrains.intellij.ce.6297960.6298918
__CFBundleIdentifier=com.jetbrains.intellij.ce
__CF_USER_TEXT_ENCODING=0x1F5:0x7:0x31
(base) andrey_smirnov@mbp-andrej Homework_2 % docker --help

Usage:  docker [OPTIONS] COMMAND

A self-sufficient runtime for containers

Common Commands:
run         Create and run a new container from an image
exec        Execute a command in a running container
ps          List containers
build       Build an image from a Dockerfile
pull        Download an image from a registry
push        Upload an image to a registry
images      List images
login       Log in to a registry
logout      Log out from a registry
search      Search Docker Hub for images
version     Show the Docker version information
info        Display system-wide information

Management Commands:
builder     Manage builds
buildx*     Docker Buildx
compose*    Docker Compose
container   Manage containers
context     Manage contexts
debug*      Get a shell into any image or container
desktop*    Docker Desktop commands (Alpha)
dev*        Docker Dev Environments
extension*  Manages Docker extensions
feedback*   Provide feedback, right in your terminal!
image       Manage images
init*       Creates Docker-related starter files for your project
manifest    Manage Docker image manifests and manifest lists
network     Manage networks
plugin      Manage plugins
sbom*       View the packaged-based Software Bill Of Materials (SBOM) for an image
scout*      Docker Scout
system      Manage Docker
trust       Manage trust on Docker images
volume      Manage volumes

Swarm Commands:
swarm       Manage Swarm

Commands:
attach      Attach local standard input, output, and error streams to a running container
commit      Create a new image from a container's changes
cp          Copy files/folders between a container and the local filesystem
create      Create a new container
diff        Inspect changes to files or directories on a container's filesystem
events      Get real time events from the server
export      Export a container's filesystem as a tar archive
history     Show the history of an image
import      Import the contents from a tarball to create a filesystem image
inspect     Return low-level information on Docker objects
kill        Kill one or more running containers
load        Load an image from a tar archive or STDIN
logs        Fetch the logs of a container
pause       Pause all processes within one or more containers
port        List port mappings or a specific mapping for the container
rename      Rename a container
restart     Restart one or more containers
rm          Remove one or more containers
rmi         Remove one or more images
save        Save one or more images to a tar archive (streamed to STDOUT by default)
start       Start one or more stopped containers
stats       Display a live stream of container(s) resource usage statistics
stop        Stop one or more running containers
tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
top         Display the running processes of a container
unpause     Unpause all processes within one or more containers
update      Update configuration of one or more containers
wait        Block until one or more containers stop, then print their exit codes

Global Options:
--config string      Location of client config files (default "/Users/andrey_smirnov/.docker")
-c, --context string     Name of the context to use to connect to the daemon (overrides DOCKER_HOST env var and default context set with "docker context use")
-D, --debug              Enable debug mode
-H, --host list          Daemon socket to connect to
-l, --log-level string   Set the logging level ("debug", "info", "warn", "error", "fatal") (default "info")
--tls                Use TLS; implied by --tlsverify
--tlscacert string   Trust certs signed only by this CA (default "/Users/andrey_smirnov/.docker/ca.pem")
--tlscert string     Path to TLS certificate file (default "/Users/andrey_smirnov/.docker/cert.pem")
--tlskey string      Path to TLS key file (default "/Users/andrey_smirnov/.docker/key.pem")
--tlsverify          Use TLS and verify the remote
-v, --version            Print version information and quit

Run 'docker COMMAND --help' for more information on a command.

For more help on how to use Docker, head to https://docs.docker.com/go/guides/
(base) andrey_smirnov@mbp-andrej Homework_2 % ls                   
Dockerfile      main.py
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build .
[+] Building 57.5s (10/10) FINISHED                                                                                                               docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                              0.0s
=> => transferring dockerfile: 142B                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/python:3.9-slim                                                                                                2.3s
=> [auth] library/python:pull token for registry-1.docker.io                                                                                                     0.0s
=> [internal] load .dockerignore                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                   0.0s
=> [1/4] FROM docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                         17.3s
=> => resolve docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                          0.0s
=> => sha256:4c0965d3919510b506d8856ebc050a96e996c7dae96e4fb420882dbe7e037e67 3.51MB / 3.51MB                                                                    1.9s
=> => sha256:fdeeec85abbad3878f2008f9445f15a19a5a224d1b7e7715ac6b923072333e57 14.74MB / 14.74MB                                                                  3.6s
=> => sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc 10.41kB / 10.41kB                                                                  0.0s
=> => sha256:93ab151da4e5310ea79c4ecf306ece628262b86a4d7a49cc601664f19fe44e36 1.75kB / 1.75kB                                                                    0.0s
=> => sha256:9d8cb7037cd8e90893e5f430ce4c048a872511e414580c7641675f2dad0a0351 5.20kB / 5.20kB                                                                    0.0s
=> => sha256:302e3ee498053a7b5332ac79e8efebec16e900289fc1ecd1c754ce8fa047fcab 29.13MB / 29.13MB                                                                  3.8s
=> => sha256:62a08b8dd4f53ad5493dabf2af00ccde91abb3771fb2187040bcf2fe94a7ced7 248B / 248B                                                                        2.3s
=> => extracting sha256:302e3ee498053a7b5332ac79e8efebec16e900289fc1ecd1c754ce8fa047fcab                                                                         6.5s
=> => extracting sha256:4c0965d3919510b506d8856ebc050a96e996c7dae96e4fb420882dbe7e037e67                                                                         0.9s
=> => extracting sha256:fdeeec85abbad3878f2008f9445f15a19a5a224d1b7e7715ac6b923072333e57                                                                         5.3s
=> => extracting sha256:62a08b8dd4f53ad5493dabf2af00ccde91abb3771fb2187040bcf2fe94a7ced7                                                                         0.0s
=> [internal] load build context                                                                                                                                 0.1s
=> => transferring context: 2.15kB                                                                                                                               0.1s
=> [2/4] WORKDIR /app                                                                                                                                            0.4s
=> [3/4] RUN pip install "fastapi[all]"                                                                                                                         36.0s
=> [4/4] COPY main.py .                                                                                                                                          0.3s
=> exporting to image                                                                                                                                            0.9s
=> => exporting layers                                                                                                                                           0.9s
=> => writing image sha256:d8c4ee452b7fed01d596f53108ca41ccfc57963dc4ed0ce06ea790df7413e3c1                                                                      0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/z6beinwylzdq3o8nl3toctkzt

What's next:
View a summary of image vulnerabilities and recommendations → docker scout quickview
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build .
[+] Building 1.7s (10/10) FINISHED                                                                                                                docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                              0.0s
=> => transferring dockerfile: 160B                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/python:3.9-slim                                                                                                1.5s
=> [auth] library/python:pull token for registry-1.docker.io                                                                                                     0.0s
=> [internal] load .dockerignore                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                   0.0s
=> [1/4] FROM docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                          0.0s
=> [internal] load build context                                                                                                                                 0.0s
=> => transferring context: 161B                                                                                                                                 0.0s
=> CACHED [2/4] WORKDIR /app                                                                                                                                     0.0s
=> CACHED [3/4] RUN pip install "fastapi[all]"                                                                                                                   0.0s
=> CACHED [4/4] COPY main.py .                                                                                                                                   0.0s
=> exporting to image                                                                                                                                            0.0s
=> => exporting layers                                                                                                                                           0.0s
=> => writing image sha256:cdc375cf6523d06ef41f25bb48008b495aeeb09e98ac48aacb1411ced5dda5d2                                                                      0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/nx4p0ryw020kivn4rdps6yll4

What's next:
View a summary of image vulnerabilities and recommendations → docker scout quickview
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build .
[+] Building 1.5s (10/10) FINISHED                                                                                                                docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                              0.0s
=> => transferring dockerfile: 158B                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/python:3.9-slim                                                                                                1.3s
=> [auth] library/python:pull token for registry-1.docker.io                                                                                                     0.0s
=> [internal] load .dockerignore                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                   0.0s
=> [1/4] FROM docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                          0.0s
=> [internal] load build context                                                                                                                                 0.0s
=> => transferring context: 161B                                                                                                                                 0.0s
=> CACHED [2/4] WORKDIR /app                                                                                                                                     0.0s
=> CACHED [3/4] RUN pip install "fastapi[all]"                                                                                                                   0.0s
=> CACHED [4/4] COPY main.py .                                                                                                                                   0.0s
=> exporting to image                                                                                                                                            0.0s
=> => exporting layers                                                                                                                                           0.0s
=> => writing image sha256:47c14207a975d663ff22a1b2b1664e5a4e8478c6bb2d974a43ac71b2364718ff                                                                      0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/th0xnaa1onlo5rej2qgt6yrbb

What's next:
View a summary of image vulnerabilities and recommendations → docker scout quickview
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build . --platform linux/amd64 -t MSC_ARCH_HW2
[+] Building 0.0s (0/0)                                                                                                                           docker:desktop-linux
ERROR: invalid tag "MSC_ARCH_HW2": repository name must be lowercase
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build . --platform linux/amd64 -t msc_arch-hw2
[+] Building 11.9s (10/10) FINISHED                                                                                                               docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                              0.0s
=> => transferring dockerfile: 159B                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/python:3.9-slim                                                                                               11.7s
=> [auth] library/python:pull token for registry-1.docker.io                                                                                                     0.0s
=> [internal] load .dockerignore                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                   0.0s
=> [1/4] FROM docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                          0.0s
=> [internal] load build context                                                                                                                                 0.0s
=> => transferring context: 161B                                                                                                                                 0.0s
=> CACHED [2/4] WORKDIR /app                                                                                                                                     0.0s
=> CACHED [3/4] RUN pip install "fastapi[all]"                                                                                                                   0.0s
=> CACHED [4/4] COPY main.py .                                                                                                                                   0.0s
=> exporting to image                                                                                                                                            0.0s
=> => exporting layers                                                                                                                                           0.0s
=> => writing image sha256:47c14207a975d663ff22a1b2b1664e5a4e8478c6bb2d974a43ac71b2364718ff                                                                      0.0s
=> => naming to docker.io/library/msc_arch-hw2                                                                                                                   0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/1z5tvp355eictqpxtb8ggl2us

What's next:
View a summary of image vulnerabilities and recommendations → docker scout quickview
(base) andrey_smirnov@mbp-andrej Homework_2 % docker buildx build . --platform linux/amd64 -t msc-arch-hw2      
[+] Building 1.4s (10/10) FINISHED                                                                                                                docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                              0.0s
=> => transferring dockerfile: 159B                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/python:3.9-slim                                                                                                1.2s
=> [auth] library/python:pull token for registry-1.docker.io                                                                                                     0.0s
=> [internal] load .dockerignore                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                   0.0s
=> [1/4] FROM docker.io/library/python:3.9-slim@sha256:49f94609e5a997dc16086a66ac9664591854031d48e375945a9dbf4d1d53abbc                                          0.0s
=> [internal] load build context                                                                                                                                 0.0s
=> => transferring context: 161B                                                                                                                                 0.0s
=> CACHED [2/4] WORKDIR /app                                                                                                                                     0.0s
=> CACHED [3/4] RUN pip install "fastapi[all]"                                                                                                                   0.0s
=> CACHED [4/4] COPY main.py .                                                                                                                                   0.0s
=> exporting to image                                                                                                                                            0.0s
=> => exporting layers                                                                                                                                           0.0s
=> => writing image sha256:47c14207a975d663ff22a1b2b1664e5a4e8478c6bb2d974a43ac71b2364718ff                                                                      0.0s
=> => naming to docker.io/library/msc-arch-hw2                                                                                                                   0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/0mtc735spondx2vesh0er2zaf

What's next:
View a summary of image vulnerabilities and recommendations → docker scout quickview
(base) andrey_smirnov@mbp-andrej Homework_2 % minikube status
minikube
type: Control Plane
host: Stopped
kubelet: Stopped
apiserver: Stopped
kubeconfig: Stopped

(base) andrey_smirnov@mbp-andrej Homework_2 % minikube start --driver=hyperkit
😄  minikube v1.33.1 на Darwin 12.3.1
✨  Используется драйвер hyperkit на основе существующего профиля

🤷  Exiting due to PROVIDER_HYPERKIT_NOT_FOUND: The 'hyperkit' provider was not found: exec: "hyperkit": executable file not found in $PATH
💡  Предложение: Run 'brew install hyperkit'
📘  Documentation: https://minikube.sigs.k8s.io/docs/reference/drivers/hyperkit/

(base) andrey_smirnov@mbp-andrej Homework_2 % ls /Applications
Anaconda-Navigator.app          IntelliJ IDEA CE.app            Obsidian.app                    Sublime Text.app                Visual Studio Code.app
Android File Transfer.app       IntelliJ IDEA.app               Papyrus.app                     Surfshark.app                   WebPomodoro.app
Cisco                           MTS Link.app                    Safari.app                      Telegram.app                    apache-maven-3.9.9
Docker.app                      MacPorts                        SafeNet                         Turbo VPN.app                   draw.io.app
Firefox.app                     Microsoft Remote Desktop.app    Skype Meetings App.app          Utilities                       zoom.us.app
GIGA IDE CE 2024.1.1.app        OBS.app                         Skype.app                       VirtualBox.app
(base) andrey_smirnov@mbp-andrej Homework_2 % brew install hyperkit
shell-init: error retrieving current directory: getcwd: cannot access parent directories: No such file or directory
Error: The current working directory must exist to run brew.
(base) andrey_smirnov@mbp-andrej Homework_2 % brew
shell-init: error retrieving current directory: getcwd: cannot access parent directories: No such file or directory
Error: The current working directory must exist to run brew.
(base) andrey_smirnov@mbp-andrej Homework_2 % minikube ststus
Error: unknown command "ststus" for "minikube"

Did you mean this?
status

Run 'minikube --help' for usage.
(base) andrey_smirnov@mbp-andrej Homework_2 % minikube status
minikube
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured

(base) andrey_smirnov@mbp-andrej Homework_2 % kubectl apply -f deployment.yaml
error: the path "deployment.yaml" does not exist
(base) andrey_smirnov@mbp-andrej Homework_2 % ls
(base) andrey_smirnov@mbp-andrej Homework_2 % pwd
/Users/andrey_smirnov/Documents/Study/microservices_architecture_otus/Homework_2
(base) andrey_smirnov@mbp-andrej Homework_2 % ls     
(base) andrey_smirnov@mbp-andrej Homework_2 % pwd
/Users/andrey_smirnov/Documents/Study/microservices_architecture_otus/Homework_2
(base) andrey_smirnov@mbp-andrej Homework_2 % cd ../
(base) andrey_smirnov@mbp-andrej microservices_architecture_otus % ls
Homework_3                              README.md                               microservices_architecture_otus.iml
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
(base) andrey_smirnov@mbp-andrej Homework_3 % 

