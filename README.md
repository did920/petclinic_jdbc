<!-- TODO:  -->
# 사전준비 사항
### k8s 와 docker가 설치되어 있어야 함
### CNI 와 loadballencer 설치가 되어 있어야 함
### gradle : 7.5.1 
### java : jre-17.0.7 (JAVA_HOME 환경변수 필수)  


  
  
      
      
      
  
# 사전작업
### DB pod 의 Node 고정을 위한 k8s node label 작업
`kubectl label nodes <고정할 node> name=db`
### nginx ingress controller 설치
`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.7.1/deploy/static/provider/cloud/deploy.yaml`  
(customize 가 필요하면 wget 으로 받은 후, deploy 의 annotation 수정)

### build 작업
`cd spring-petclinic-data-jdbc`  
`./gradlew clean build`  
`docker build -t petclinic-jdbc:v1.0.1`

### k8s 배포 작업
`cd ..`  
`kubectl apply -f k8s-manifests/`

