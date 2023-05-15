<!-- TODO:  -->
k8s 와 docker가 설치되어 있어야 함
gradle 몇 버전 이상
jdk 17 이상 
CNI 와 loadballencer 설치가 되어 있어야 함


DB pod 고정을 위한 k8s node label  ex) name=db
nginx ingress controller 설치

cd spring-petclinic-data-jdbc
./gradlew build
docker build -t petclinic-jdbc:v1.0.1

cd ..
kubectl apply -f k8s-manifests/

