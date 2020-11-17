select *
from not_exists_in_prd;

/*DELETE users;*/
TRUNCATE TABLE users;

Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('cony','코니','conyPass',to_date('2020/10/21','YYYY/MM/DD'),'토끼',null,null,null,'D:\profile\cony.png','cony.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('sally','샐리','sallyPass',to_date('2020/10/21','YYYY/MM/DD'),'병아리',null,null,null,'D:\profile\sally.png','sally.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('james','제임스','jamesPass',to_date('2020/10/21','YYYY/MM/DD'),'사람',null,null,null,'D:\profile\james.png','james.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('moon','문','moonPass',null,'달',null,null,null,'D:\profile\moon.png','moon.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('leonard','레너드','leonardPass',to_date('2020/10/15','YYYY/MM/DD'),'개구리',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('edward','에드워드','edwardPass',to_date('2020/10/15','YYYY/MM/DD'),'애벌레',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('jessica','제시카','jessicaPass',to_date('2020/10/15','YYYY/MM/DD'),'고양이',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('boss','보스','bossPass',to_date('2020/10/15','YYYY/MM/DD'),'사람',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('choco','초코','chocoPass',to_date('2020/10/15','YYYY/MM/DD'),'곰2',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('pangyo','팡요','pangyoPass',to_date('2020/10/15','YYYY/MM/DD'),'판다',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('muzi','무지','muziPass',to_date('2020/10/15','YYYY/MM/DD'),'토끼',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('con','콘','conPass',to_date('2020/10/15','YYYY/MM/DD'),'악어',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('apeach','어피치','apeachPass',to_date('2020/10/15','YYYY/MM/DD'),'복숭아',null,null,null,null,null);
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('ryan','라이언 ','ryanPass',to_date('2020/10/15','YYYY/MM/DD'),'사자',null,null,null,'D:\profile\ryan.png','ryan.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('brown','브라운','brownPass',to_date('2020/11/05','YYYY/MM/DD'),'곰',null,null,null,'d:\upload\34bbe664-d860-417d-850e-a4547f75eaa2.png','brown.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('ddit','대덕인재','dditpass',to_date('2020/11/11','YYYY/MM/DD'),'개발원',null,null,null,null,null);

COMMIT;

TRUNCATE TABLE board;

INSERT INTO BOARD (BOARD_STATUS,BOARD_NAME) values (1, '테스트게시판');

COMMIT;

TRUNCATE TABLE post;

INSERT INTO POST (POST_SEQ, POST_TITLE, POST_DATE, BOARD_NAME, USERID, POST_STATUS,  POST_CONTENT) values (POST_SEQ.NEXTVAL, '테스트제목1', SYSDATE, '테스트게시판', 'brown', 1,  '테스트내용');
INSERT INTO POST (POST_SEQ, POST_TITLE, POST_DATE, BOARD_NAME, USERID, POST_STATUS,  POST_CONTENT) values (POST_SEQ.NEXTVAL, '테스트제목2', SYSDATE, '테스트게시판', 'brown', 1,  '테스트내용');
INSERT INTO POST (POST_SEQ, POST_TITLE, POST_DATE, BOARD_NAME, USERID, POST_STATUS,  POST_CONTENT) values (POST_SEQ.NEXTVAL, '테스트제목3', SYSDATE, '테스트게시판', 'brown', 1,  '테스트내용');
INSERT INTO POST (POST_SEQ, POST_TITLE, POST_DATE, BOARD_NAME, USERID, POST_STATUS,  POST_CONTENT) values (POST_SEQ.NEXTVAL, '테스트제목4', SYSDATE, '테스트게시판', 'brown', 1,  '테스트내용');
INSERT INTO POST (POST_SEQ, POST_TITLE, POST_DATE, BOARD_NAME, USERID, POST_STATUS,  POST_CONTENT) values (POST_SEQ.NEXTVAL, '테스트제목5', SYSDATE, '테스트게시판', 'brown', 1,  '테스트내용');

COMMIT;

TRUNCATE TABLE ATTACHMENT;

INSERT INTO ATTACHMENT (ATC_SEQ, ATC_FNAME, ATC_RFNAME, POST_SEQ) values (ATTACHMENT_SEQ.NEXTVAL, 'D:\profile\test1.jpg', 'test1.jpg',88);
INSERT INTO ATTACHMENT (ATC_SEQ, ATC_FNAME, ATC_RFNAME, POST_SEQ) values (ATTACHMENT_SEQ.NEXTVAL, 'D:\profile\test2.jpg', 'test1.jpg',88);
INSERT INTO ATTACHMENT (ATC_SEQ, ATC_FNAME, ATC_RFNAME, POST_SEQ) values (ATTACHMENT_SEQ.NEXTVAL, 'D:\profile\test3.jpg', 'test1.jpg',88);

COMMIT;

TRUNCATE TABLE REPLY;

INSERT INTO REPLY (REPLY_CONTENT, REPLY_STATUS, REPLY_SEQ, POST_SEQ, USERID, REPLY_DATE) values ('테스트 리플 내용1', 1,  REPLY_SEQ.NEXTVAL, 88, 'brown', SYSDATE);
INSERT INTO REPLY (REPLY_CONTENT, REPLY_STATUS, REPLY_SEQ, POST_SEQ, USERID, REPLY_DATE) values ('테스트 리플 내용2', 1,  REPLY_SEQ.NEXTVAL, 88, 'brown', SYSDATE);
INSERT INTO REPLY (REPLY_CONTENT, REPLY_STATUS, REPLY_SEQ, POST_SEQ, USERID, REPLY_DATE) values ('테스트 리플 내용3', 1,  REPLY_SEQ.NEXTVAL, 88, 'brown', SYSDATE);

COMMIT;
