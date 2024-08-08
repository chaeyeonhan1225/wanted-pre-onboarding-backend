INSERT INTO Company (status, createdAt, updatedAt, version, country, name, region)
VALUES (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Apple', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Meta', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Google', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'MicroSoft', 'Seoul'),
       (1, CURRENT_DATE, CURRENT_DATE, 1, 'Korea', 'Amazon', 'Seoul');

INSERT INTO WantedWork.`User` (createdAt, updatedAt, version, nickname, email)
VALUES (CURRENT_DATE, CURRENT_DATE, 1, "tester1", "test1@gmail.com"),
       (CURRENT_DATE, CURRENT_DATE, 1, "tester2", "test2@gmail.com"),
       (CURRENT_DATE, CURRENT_DATE, 1, "tester3", "test3@gmail.com"),
       (CURRENT_DATE, CURRENT_DATE, 1, "tester4", "test4@gmail.com"),
       (CURRENT_DATE, CURRENT_DATE, 1, "tester5", "test5@gmail.com");
