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

### mysql database 초기 생성 과정
`kubectl get pod`    
위 명령어로 db pod name 저장  
`kubectl exec -it <db pod name> /bin/bash`  
위 명령어로 db 접속  
`mysql -uroot -p`  
접속 (초기 비밀번호: passw0rd!@#)  
mysql-initdb 라는 configmap 의 값을 query 문 또는 pring-petclinic-data-jdbc/src/main/resources/db/migration/ 안에 있는 sql 파일을 사용해서 db 생성 및 데이터 입력  


### 접속 방법
`kubectl get ingress`  
위 명령어로 조회된 address 값으로 접속 ex) http://<조회된 주소>:80  
application.properties 를 수정하고 싶으면 properties.yaml 에서 properties-file CM 을 수정하면 된다.

