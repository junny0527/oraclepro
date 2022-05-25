--테이블 생성
CREATE TABLE person (
    person_id   number(5) ,
    name        VARCHAR2(30) NOT NULL,
    hp          VARCHAR2(20),
    company     VARCHAR2(20),
    PRIMARY KEY(person_id)
);
--시퀀스
CREATE SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1 
nocache;
--인서트
insert into person
values (seq_person_id.nextval, '이효리', '010-1111-1111','02-1111-1111');
insert into person
values (seq_person_id.nextval, '정우성', '010-2222-2222','02-2222-2222');
insert into person
values (seq_person_id.nextval, '유재석', '010-3333-3333','02-3333-3333');
insert into person
values (seq_person_id.nextval, '이정재', '010-4444-4444','02-4444-4444');
insert into person
values (seq_person_id.nextval, '서장훈', '010-5555-5555','02-5555-5555');
--삭제
DELETE FROM person
WHERE person_id = 5 ;
--수정
UPDATE person SET hp = '010-9999-9999', company ='02-9999-9999'
WHERE person_id = 4 ;
--검사
SELECT
    * FROM person;
--테이블 삭제
DROP TABLE person;
--시퀀스 삭제
DROP SEQUENCE seq_person_id;