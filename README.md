# wanted-pre-onboarding-backend
## 사용기술
- Java17
- Spring Boot 3.3.2
- Gradle
- Spring MVC
- JPA
- mysql 5.7
## 실행
1. 로컬 mysql에 `WantedWork` 데이터베이스를 만듭니다.
2. `application.yml`에서 로컬 DB 정보를 입력합니다.
   ```yaml
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/WantedWork?serverTimezone=UTC&useCursors=false&sendStringParametersAsUnicode=false&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false
      username: <USERNAME>
      password: <PASSWORD>
   ```
3. 해당 프로젝트 폴더에서 터미널을 열어서 아래의 명령어를 입력합니다.
    ```shell
    ./gradlew bootJar
    ```
4. jar 파일이 생성되면 build/lib로 이동한 후, 아래의 명령어로 실행합니다.(profile은 생략해도됩니다.)
    ```shell
    java -jar sample.jar --spring.profiles.active=<환경>
    ```
   
5. 환경에 맞는 포트 번호에서 서버가 구동되는것을 확인할 수 있습니다.

6. spring-docs에서 테스트할 수 있습니다.
    ```text
    localhost:<port>/swagger-ui.html
    ```

## API Endpoint
1. 채용공고 등록
   ```http request
   POST /jobdescriptions 
   ```
2. 채용공고 수정
   ```http request
   PATCH /jobdescriptions/{id}
   ```
3. 채용공고 삭제
   ```http request
   DELETE /jobdescriptions/{id}
   ```
4. 채용공고 목록 조회
   ```http request
   GET /jobdescriptions?search={searchText}?page=1&size=10
   ```
   > search는 optional입니다. 회사명, 포지션명, 기술스택 LIKE 검색을 할 수 있습니다.
   > 페이지네이션이 구현되어있습니다. 기본 값은 page=1&size=10입니다.
5. 채용공고 상세 조회
   ```http request
   GET /jobdescriptions/{id} 
   ```
6. 채용공고 지원
    ```http request
   POST /jobdescriptions/{id}/apply
   ```
## 초기 데이터
초기 데이터가 세팅되어있습니다. 테스트를 위해 기본 설정으로 API를 실행하자마자 Table이 create-drop 되며, 초기 데이터가 생성됩니다.
```sql
INSERT INTO Company (status, createdAt, updatedAt, version, country, name, region)
VALUES (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Apple', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Meta', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Google', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'MicroSoft', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Amazon', 'Seoul');

INSERT INTO WantedWork.`User` (createdAt, updatedAt, version, nickname, email)
VALUES (CURRENT_DATE, CURRENT_DATE, 1, 'tester1', 'test1@gmail.com'),
       (CURRENT_DATE, CURRENT_DATE, 1, 'tester2', 'test2@gmail.com'),
       (CURRENT_DATE, CURRENT_DATE, 1, 'tester3', 'test3@gmail.com'),
       (CURRENT_DATE, CURRENT_DATE, 1, 'tester4', 'test4@gmail.com'),
       (CURRENT_DATE, CURRENT_DATE, 1, 'tester5', 'test5@gmail.com');
```