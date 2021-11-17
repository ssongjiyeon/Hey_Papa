# 포팅 메뉴얼

## :one: 개발 환경

```
1. 서버 및 DevOps
AWS EC2, Nginx, Jenkins

2. 프론트엔드
vue, Quasar

3. 백엔드
기본 API
- springboot : 2.5.5
- Gradle : 7.2

4. DB
MySQL
```

- IDE

```
Spring Tool Suite3

Visual Studio Code : 1.59.0
```

<br>

## :two: 빌드 및 배포 작업 문서

- 포트 설정

```
프론트 : 3000
백엔드(스프링) : 8080
MySQL : 3306
Redis : 6379
Jenkins : 9999
```

- 데이터베이스 접속 정보

```
MySQL
계정 : ssafy
Database : k5b206
```

- 빌드 방법

```
1. 백엔드 빌드
    a) 백엔드 폴더 이동 및 .jar 파일 생성
        cd backend
        chmod 777 ./gradlew
        ./gradlew build
        
2. 프론트엔드 빌드
    a) 프론트엔드 폴더 이동 및 빌드
        cd frontend
        npm install
        sudo quasar build

    b) 빌드 폴더 이동
        cd /var/www/html
        sudo rm -rf dist
        cd frontend
        sudo mv dist /var/www/html
```

- 실행 방법

```
1. 서버 접속 : ssh -i cert.pem ubuntu@k5b206.p.ssafy.io
2. Nginx 실행 : sudo systemctl start nginx
3. 실행 : java -jar 실행파일.jar
4. 페이지 접속 : https://k5b206.p.ssafy.io/

▫ Swagger 주소 : https://k5b206.p.ssafy.io:8080/api/swagger-ui.html
▫ Jnekins 주소 : http://k5b206.p.ssafy.io:9999
```

<br>

## :three: 외부 서비스 정보 문서

- 카카오 맵 API

```
https://apis.map.kakao.com/android/
```

