/*CAREER
 * EDUCATION_STATUS
 * RESUME
 * RESUME_EDUCATION
 * EDUCATION
 * RESUME_LOCATION
 * DOSI
 * SIGUNGU
 * DONG
 * WORK_TYPE
 * SHOP
 * HIRE_PERIOD
 * SCRAP
 * HIRE
 * HIRE_PERIOD
 * PAY_TYPE
 */

CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"AUTHORITY_NO"	CHAR(1)		NOT NULL,
	"MEMBER_EMAIL"	VARCHAR2(300)		NOT NULL,
	"MEMBER_PW"	VARCHAR2(300)		NOT NULL,
	"MEMBER_PHONE_NUMBER"	VARCHAR2(300)		NOT NULL,
	"ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MEMBER_NAME"	VARCHAR2(300)		NOT NULL,
	"MEMBER_STATUS"	NUMBER	DEFAULT 4	NOT NULL,
	"MEMBER_GENDER"	CHAR(1)		NOT NULL,
	"MEMBER_ADDRESS"	VARCHAR2(200)		NOT NULL
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "MEMBER"."AUTHORITY_NO" IS '권한 번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_PHONE_NUMBER" IS '회원 전화번호';

COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '회원 가입일';

COMMENT ON COLUMN "MEMBER"."MEMBER_NAME" IS '회원 이름';

COMMENT ON COLUMN "MEMBER"."MEMBER_STATUS" IS '회원 상태(1:1회신고,2:2회신고,3:정지회원,4:정상회원, 5:탈퇴회원)';

COMMENT ON COLUMN "MEMBER"."MEMBER_GENDER" IS '회원 성별 (남자:1, 여자:2)';

COMMENT ON COLUMN "MEMBER"."MEMBER_ADDRESS" IS '회원 주소(직접 입력 받음)';

CREATE TABLE "AUTHORITY" (
	"AUTHORITY_NO"	CHAR(1)		NOT NULL,
	"AUTHORITY_NAME"	VARCHAR2(10)		NOT NULL
);

COMMENT ON COLUMN "AUTHORITY"."AUTHORITY_NO" IS '권한 번호(1 : 관리자, 2: 기업, 3:일반)';

COMMENT ON COLUMN "AUTHORITY"."AUTHORITY_NAME" IS '권한명';

CREATE TABLE "RESUME" (
	"RESUME_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"CAREER"	NUMBER		NOT NULL,
	"WORK_LOCATION"	VARCHAR(255)		NULL,
	"RESUME_TITLE"	VARCHAR2(200)		NOT NULL,
	"Field"	VARCHAR(255)		NULL
);

COMMENT ON COLUMN "RESUME"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "RESUME"."CAREER" IS '1:신입, 2:경력';

COMMENT ON COLUMN "RESUME"."WORK_LOCATION" IS '근무 희망 지역';

COMMENT ON COLUMN "RESUME"."RESUME_TITLE" IS '이력서 제목';

CREATE TABLE "EDUCATION" (
	"EDUCATION_NO"	CHAR(1)		NOT NULL,
	"EDUCATION_NAME"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "EDUCATION"."EDUCATION_NO" IS '학력 번호(1: 초, 2: 중, 3:고, 4: 2,3년제 대학, 5: 4년제 대학, 6: 대학원';

COMMENT ON COLUMN "EDUCATION"."EDUCATION_NAME" IS '학력 구분';

CREATE TABLE "RESUME_EDUCATION" (
	"RESUME_EDUCATION_NO"	NUMBER		NOT NULL,
	"RESUME_NO"	NUMBER		NOT NULL,
	"EDUCATION_STATUS_NO"	CHAR(1)		NOT NULL,
	"EDUCATION_SCHOOL"	CHAR(1)		NOT NULL,
	"SCHOOL_NAME"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "RESUME_EDUCATION"."RESUME_EDUCATION_NO" IS '이력서별 학력 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."EDUCATION_STATUS_NO" IS '학력 상태 번호(1:졸업, 2: 재학, 3: 휴학, 4: 중퇴, 5: 수료)';

COMMENT ON COLUMN "RESUME_EDUCATION"."EDUCATION_SCHOOL" IS '학력 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."SCHOOL_NAME" IS '학교/기관명';

CREATE TABLE "EDUCATION_STATUS" (
	"EDUCATION_STATUS_NO"	NUMBER		NOT NULL,
	"EDUCATION_STATUS_NAME"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "EDUCATION_STATUS"."EDUCATION_STATUS_NO" IS '학력 상태 번호(1:졸업, 2: 재학, 3: 휴학, 4: 중퇴, 5: 수료)';

COMMENT ON COLUMN "EDUCATION_STATUS"."EDUCATION_STATUS_NAME" IS '상태명(졸업, 중퇴, 수료, 휴학)';

CREATE TABLE "HIRE" (
	"HIRE_NO"	NUMBER		NOT NULL,
	"SHOP_NO"	NUMBER		NOT NULL,
	"TYPE_NO"	NUMBER		NOT NULL,
	"PAY_NO"	NUMBER		NOT NULL,
	"HIRE_TITLE"	VARCHAR2(200)		NOT NULL,
	"HIRE_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"HIRE_START"	DATE	DEFAULT SYSDATE	NOT NULL,
	"HIRE_END"	DATE		NOT NULL,
	"HIRE_COUNT"	NUMBER	DEFAULT 0	NULL,
	"HIRE_TERM"	NUMBER		NOT NULL,
	"HIRE_DAY"	VARCHAR2()	DEFAULT '요일 협의'	NOT NULL,
	"HIRE_TIME"	VARCHAR2(50)	DEFAULT '협의 가능'	NULL,
	"HIRE_GENDER"	CHAR(1)	DEFAULT 'A'	NULL,
	"APPLY_COUNT"	NUMBER	DEFAULT 0	NULL,
	"HIRE_STATUS"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "HIRE"."HIRE_NO" IS '공고문 번호';

COMMENT ON COLUMN "HIRE"."SHOP_NO" IS '상점 번호';

COMMENT ON COLUMN "HIRE"."TYPE_NO" IS '직종 번호';

COMMENT ON COLUMN "HIRE"."PAY_NO" IS '급여 유형 번호(시급/일급/주급/월급/연봉)';

COMMENT ON COLUMN "HIRE"."HIRE_TITLE" IS '공고 제목';

COMMENT ON COLUMN "HIRE"."HIRE_CONTENT" IS '주요 근무 내용';

COMMENT ON COLUMN "HIRE"."HIRE_START" IS '모집 시작 날짜';

COMMENT ON COLUMN "HIRE"."HIRE_END" IS '모집 마감 날짜';

COMMENT ON COLUMN "HIRE"."HIRE_COUNT" IS '모집 인원';

COMMENT ON COLUMN "HIRE"."HIRE_TERM" IS '근무기간(일 단위)';

COMMENT ON COLUMN "HIRE"."HIRE_DAY" IS '근무 요일';

COMMENT ON COLUMN "HIRE"."HIRE_TIME" IS '근무시간';

COMMENT ON COLUMN "HIRE"."HIRE_GENDER" IS '모집 성별(남M,여F,무관A))';

COMMENT ON COLUMN "HIRE"."APPLY_COUNT" IS '지원자 수';

COMMENT ON COLUMN "HIRE"."HIRE_STATUS" IS '공고 상태(1:저장, 2: 임시저장)';

CREATE TABLE "WORK_TYPE" (
	"TYPE_NO"	NUMBER		NOT NULL,
	"TYPE_NAME"	VARCHAR2(50)		NOT NULL
);

COMMENT ON COLUMN "WORK_TYPE"."TYPE_NO" IS '직종 번호';

COMMENT ON COLUMN "WORK_TYPE"."TYPE_NAME" IS '직종 이름';

CREATE TABLE "SHOP" (
	"SHOP_NO"	NUMBER		NOT NULL,
	"DONG_NO"	NUMBER		NOT NULL,
	"SHOP_NAME"	VARCHAR2(50)		NOT NULL,
	"SHOP_EMAIL"	VARCHAR2(100)		NOT NULL,
	"SHOP_PW"	VARCHAR2(100)		NOT NULL,
	"SHOP_TEL"	VARCHAR2(300)		NOT NULL,
	"SHOP_BRN"	CHAR(10)		NOT NULL,
	"SHOP_OWNER"	VARCHAR2(50)		NOT NULL,
	"SHOP_ADDRESS"	VARCHAR2(200)		NOT NULL
);

COMMENT ON COLUMN "SHOP"."SHOP_NO" IS '상점 번호';

COMMENT ON COLUMN "SHOP"."DONG_NO" IS '동면읍 번호';

COMMENT ON COLUMN "SHOP"."SHOP_NAME" IS '상점 이름';

COMMENT ON COLUMN "SHOP"."SHOP_EMAIL" IS '이메일';

COMMENT ON COLUMN "SHOP"."SHOP_PW" IS '비밀번호';

COMMENT ON COLUMN "SHOP"."SHOP_TEL" IS '전화번호';

COMMENT ON COLUMN "SHOP"."SHOP_BRN" IS '사엽자등록번호';

COMMENT ON COLUMN "SHOP"."SHOP_OWNER" IS '대표자명';

COMMENT ON COLUMN "SHOP"."SHOP_ADDRESS" IS '세부주소';

CREATE TABLE "CAREER" (
	"CAREER_NO"	NUMBER		NOT NULL,
	"RESUME_NO"	NUMBER		NOT NULL,
	"COMPANY_NAME"	VARCHAR2(100)		NOT NULL,
	"START_DATE"	DATE		NOT NULL,
	"END_DATE"	DATE		NOT NULL,
	"CHARGE_OF"	VARCHAR2(500)		NOT NULL
);

COMMENT ON COLUMN "CAREER"."CAREER_NO" IS '경력 번호';

COMMENT ON COLUMN "CAREER"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "CAREER"."COMPANY_NAME" IS '회사명';

COMMENT ON COLUMN "CAREER"."START_DATE" IS '근무 시작 날짜';

COMMENT ON COLUMN "CAREER"."END_DATE" IS '근무 종료 날짜';

COMMENT ON COLUMN "CAREER"."CHARGE_OF" IS '담당 업무';

CREATE TABLE "DOSI" (
	"DOSI_NO"	NUMBER		NOT NULL,
	"DOSI_NAME"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "DOSI"."DOSI_NO" IS '도/시 번호';

COMMENT ON COLUMN "DOSI"."DOSI_NAME" IS '도/시 이름';

CREATE TABLE "SIGUNGU" (
	"SIGUNGU_NO"	NUMBER		NOT NULL,
	"DOSI_NO"	NUMBER		NOT NULL,
	"SIGUNGU_NAME"	VARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN "SIGUNGU"."SIGUNGU_NO" IS '시군구 번호';

COMMENT ON COLUMN "SIGUNGU"."DOSI_NO" IS '도/시 번호';

COMMENT ON COLUMN "SIGUNGU"."SIGUNGU_NAME" IS '시군구 이름';

CREATE TABLE "DONG" (
	"DONG_NO"	NUMBER		NOT NULL,
	"SIGUNGU_NO"	NUMBER		NOT NULL,
	"DONG_NAME"	VARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN "DONG"."DONG_NO" IS '동면읍 번호';

COMMENT ON COLUMN "DONG"."SIGUNGU_NO" IS '시군구 번호';

COMMENT ON COLUMN "DONG"."DONG_NAME" IS '동면읍 이름';

CREATE TABLE "RESUME_LOCATION" (
	"HOPE_NO"	VARCHAR(255)		NOT NULL,
	"RESUME_NO"	NUMBER		NOT NULL,
	"DONG_NO"	VARCHAR(255)		NOT NULL
);

COMMENT ON COLUMN "RESUME_LOCATION"."HOPE_NO" IS '희망근무지역 번호';

COMMENT ON COLUMN "RESUME_LOCATION"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME_LOCATION"."DONG_NO" IS '동면읍 번호';

CREATE TABLE "REVIEW_BOARD" (
	"REVIEW_BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"REVIEW_TITLE"	NVARCHAR2(100)		NOT NULL,
	"REVIEW_BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"REVIEW_BOARD_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"REVIEW_BOARD_UPDATE_DATE"	DATE		NOT NULL,
	"REVIEW_BOARD_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "REVIEW_BOARD"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_TITLE" IS '게시글제목';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_CONTENT" IS '게시글내용';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_WRITE_DATE" IS '작성일';

COMMENT ON COLUMN "REVIEW_BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "REVIEW_BOARD"."BOARD_DEL_FL" IS '게시글 삭제 여부('Y','N')';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_UPDATE_DATE" IS '마지막 수정일';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_CODE" IS '1:일반 2:공지';

CREATE TABLE "BOARD_DECLARE" (
	"REVIEW_BOARD_DECLARE_NO"	NUMBER		NOT NULL,
	"REVIEW_BOARD_NO"	NUMBER		NOT NULL,
	"REVIEW_BOARD_CONTENT"	VARCHAR2(1500)		NOT NULL,
	"REVIEW_BOARD_CONDITION"	CHAR(1)	DEFAULT 0	NOT NULL
);

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_DECLARE_NO" IS '게시글 신고 번호';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_CONTENT" IS '게시글 신고 사유';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_CONDITION" IS '0:처리제외/1:처리중/2:처리완료';

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(1000)		NOT NULL,
	"COMMENT_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"REVIEW_BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"COMMENT_NO2"	NUMBER		NULL
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호';

COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "COMMENT"."COMMENT_WRITE_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제여부('Y','N')';

COMMENT ON COLUMN "COMMENT"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "COMMENT"."COMMENT_NO2" IS '부모댓글번호';

CREATE TABLE "COMMENT_DECLARE" (
	"COMENT_DECLARE_NO"	NUMBER		NOT NULL,
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_DECLARE_CONTENT"	VARCHAR2(1500)		NOT NULL,
	"COMMENT_DECLARE_CONDITION"	CHAR(1)	DEFAULT 0	NOT NULL
);

COMMENT ON COLUMN "COMMENT_DECLARE"."COMMENT_NO" IS '댓글 번호';

COMMENT ON COLUMN "COMMENT_DECLARE"."COMMENT_DECLARE_CONTENT" IS '댓글 신고 사유';

COMMENT ON COLUMN "COMMENT_DECLARE"."COMMENT_DECLARE_CONDITION" IS '0:처리제외/1:처리중/2:처리완료';

CREATE TABLE "SCRAP" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"HIRE_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "SCRAP"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "SCRAP"."HIRE_NO" IS '스크랩한 공고문 번호';

CREATE TABLE "MEMBER_DECLARE_CONDITION" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"DECLARE_NUMBER"	NUMBER	DEFAULT 0	NOT NULL,
	"SUSPEND_PERIOD"	DATE		NULL
);

COMMENT ON COLUMN "MEMBER_DECLARE_CONDITION"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "MEMBER_DECLARE_CONDITION"."DECLARE_NUMBER" IS '신고 횟수';

COMMENT ON COLUMN "MEMBER_DECLARE_CONDITION"."SUSPEND_PERIOD" IS '정지기간(관리자 처리 시점부터 예시로 7일까지)';

CREATE TABLE "BOARD_TYPE" (
	"REVIEW_BOARD_CODE"	NUMBER		NOT NULL,
	"REVIEW_BOARD_NAME"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "BOARD_TYPE"."REVIEW_BOARD_CODE" IS '1:일반 2:공지';

COMMENT ON COLUMN "BOARD_TYPE"."REVIEW_BOARD_NAME" IS '게시판 이름(일반/공지)';

CREATE TABLE "PAY_TYPE" (
	"PAY_NO"	NUMBER		NOT NULL,
	"PAY_NAME"	VARCHAR2(10)	DEFAULT 시급	NULL
);

COMMENT ON COLUMN "PAY_TYPE"."PAY_NO" IS '급여 유형 번호';

COMMENT ON COLUMN "PAY_TYPE"."PAY_NAME" IS '급여명(시급/일급/주급/월급)';

CREATE TABLE "HIRE_PERIOD" (
	"PERIOD_NO"	NUMBER		NOT NULL,
	"HIRE_NO"	NUMBER		NOT NULL,
	"WORK_DAY"	VARCHAR2(80)		NOT NULL,
	"WORK_START"	VARCHAR2(80)		NOT NULL,
	"WORK_END"	VARCHAR2(80)		NOT NULL
);

COMMENT ON COLUMN "HIRE_PERIOD"."PERIOD_NO" IS '공고 별 근무기간 번호';

COMMENT ON COLUMN "HIRE_PERIOD"."HIRE_NO" IS '공고문 번호';

COMMENT ON COLUMN "HIRE_PERIOD"."WORK_DAY" IS '근무 요일';

COMMENT ON COLUMN "HIRE_PERIOD"."WORK_START" IS '근무 시작 시각';

COMMENT ON COLUMN "HIRE_PERIOD"."WORK_END" IS '근무 종료 시각';

ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY (
	"MEMBER_NO"
);

ALTER TABLE "AUTHORITY" ADD CONSTRAINT "PK_AUTHORITY" PRIMARY KEY (
	"AUTHORITY_NO"
);

ALTER TABLE "RESUME" ADD CONSTRAINT "PK_RESUME" PRIMARY KEY (
	"RESUME_NO"
);

ALTER TABLE "EDUCATION" ADD CONSTRAINT "PK_EDUCATION" PRIMARY KEY (
	"EDUCATION_NO"
);

ALTER TABLE "RESUME_EDUCATION" ADD CONSTRAINT "PK_RESUME_EDUCATION" PRIMARY KEY (
	"RESUME_EDUCATION_NO"
);

ALTER TABLE "EDUCATION_STATUS" ADD CONSTRAINT "PK_EDUCATION_STATUS" PRIMARY KEY (
	"EDUCATION_STATUS_NO"
);

ALTER TABLE "HIRE" ADD CONSTRAINT "PK_HIRE" PRIMARY KEY (
	"HIRE_NO"
);

ALTER TABLE "WORK_TYPE" ADD CONSTRAINT "PK_WORK_TYPE" PRIMARY KEY (
	"TYPE_NO"
);

ALTER TABLE "SHOP" ADD CONSTRAINT "PK_SHOP" PRIMARY KEY (
	"SHOP_NO"
);

ALTER TABLE "CAREER" ADD CONSTRAINT "PK_CAREER" PRIMARY KEY (
	"CAREER_NO"
);

ALTER TABLE "DOSI" ADD CONSTRAINT "PK_DOSI" PRIMARY KEY (
	"DOSI_NO"
);

ALTER TABLE "SIGUNGU" ADD CONSTRAINT "PK_SIGUNGU" PRIMARY KEY (
	"SIGUNGU_NO"
);

ALTER TABLE "DONG" ADD CONSTRAINT "PK_DONG" PRIMARY KEY (
	"DONG_NO"
);

ALTER TABLE "RESUME_LOCATION" ADD CONSTRAINT "PK_RESUME_LOCATION" PRIMARY KEY (
	"HOPE_NO"
);

ALTER TABLE "REVIEW_BOARD" ADD CONSTRAINT "PK_REVIEW_BOARD" PRIMARY KEY (
	"REVIEW_BOARD_NO"
);

ALTER TABLE "BOARD_DECLARE" ADD CONSTRAINT "PK_BOARD_DECLARE" PRIMARY KEY (
	"REVIEW_BOARD_DECLARE_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT_NO"
);

ALTER TABLE "COMMENT_DECLARE" ADD CONSTRAINT "PK_COMMENT_DECLARE" PRIMARY KEY (
	"COMENT_DECLARE_NO"
);

ALTER TABLE "SCRAP" ADD CONSTRAINT "PK_SCRAP" PRIMARY KEY (
	"MEMBER_NO",
	"HIRE_NO"
);

ALTER TABLE "MEMBER_DECLARE_CONDITION" ADD CONSTRAINT "PK_MEMBER_DECLARE_CONDITION" PRIMARY KEY (
	"MEMBER_NO"
);

ALTER TABLE "BOARD_TYPE" ADD CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY (
	"REVIEW_BOARD_CODE"
);

ALTER TABLE "PAY_TYPE" ADD CONSTRAINT "PK_PAY_TYPE" PRIMARY KEY (
	"PAY_NO"
);

ALTER TABLE "HIRE_PERIOD" ADD CONSTRAINT "PK_HIRE_PERIOD" PRIMARY KEY (
	"PERIOD_NO"
);

ALTER TABLE "MEMBER" ADD CONSTRAINT "FK_AUTHORITY_TO_MEMBER_1" FOREIGN KEY (
	"AUTHORITY_NO"
)
REFERENCES "AUTHORITY" (
	"AUTHORITY_NO"
);

ALTER TABLE "RESUME" ADD CONSTRAINT "FK_MEMBER_TO_RESUME_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "RESUME_EDUCATION" ADD CONSTRAINT "FK_RESUME_TO_RESUME_EDUCATION_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);

ALTER TABLE "RESUME_EDUCATION" ADD CONSTRAINT "FK_EDUCATION_STATUS_TO_RESUME_EDUCATION_1" FOREIGN KEY (
	"EDUCATION_STATUS_NO"
)
REFERENCES "EDUCATION_STATUS" (
	"EDUCATION_STATUS_NO"
);

ALTER TABLE "RESUME_EDUCATION" ADD CONSTRAINT "FK_EDUCATION_TO_RESUME_EDUCATION_1" FOREIGN KEY (
	"EDUCATION_SCHOOL"
)
REFERENCES "EDUCATION" (
	"EDUCATION_NO"
);

ALTER TABLE "HIRE" ADD CONSTRAINT "FK_SHOP_TO_HIRE_1" FOREIGN KEY (
	"SHOP_NO"
)
REFERENCES "SHOP" (
	"SHOP_NO"
);

ALTER TABLE "HIRE" ADD CONSTRAINT "FK_WORK_TYPE_TO_HIRE_1" FOREIGN KEY (
	"TYPE_NO"
)
REFERENCES "WORK_TYPE" (
	"TYPE_NO"
);

ALTER TABLE "HIRE" ADD CONSTRAINT "FK_PAY_TYPE_TO_HIRE_1" FOREIGN KEY (
	"PAY_NO"
)
REFERENCES "PAY_TYPE" (
	"PAY_NO"
);

ALTER TABLE "SHOP" ADD CONSTRAINT "FK_DONG_TO_SHOP_1" FOREIGN KEY (
	"DONG_NO"
)
REFERENCES "DONG" (
	"DONG_NO"
);

ALTER TABLE "CAREER" ADD CONSTRAINT "FK_RESUME_TO_CAREER_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);

ALTER TABLE "SIGUNGU" ADD CONSTRAINT "FK_DOSI_TO_SIGUNGU_1" FOREIGN KEY (
	"DOSI_NO"
)
REFERENCES "DOSI" (
	"DOSI_NO"
);

ALTER TABLE "DONG" ADD CONSTRAINT "FK_SIGUNGU_TO_DONG_1" FOREIGN KEY (
	"SIGUNGU_NO"
)
REFERENCES "SIGUNGU" (
	"SIGUNGU_NO"
);

ALTER TABLE "RESUME_LOCATION" ADD CONSTRAINT "FK_RESUME_TO_RESUME_LOCATION_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);

ALTER TABLE "RESUME_LOCATION" ADD CONSTRAINT "FK_DONG_TO_RESUME_LOCATION_1" FOREIGN KEY (
	"DONG_NO"
)
REFERENCES "DONG" (
	"DONG_NO"
);

ALTER TABLE "REVIEW_BOARD" ADD CONSTRAINT "FK_MEMBER_TO_REVIEW_BOARD_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "REVIEW_BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_REVIEW_BOARD_1" FOREIGN KEY (
	"REVIEW_BOARD_CODE"
)
REFERENCES "BOARD_TYPE" (
	"REVIEW_BOARD_CODE"
);

ALTER TABLE "BOARD_DECLARE" ADD CONSTRAINT "FK_REVIEW_BOARD_TO_BOARD_DECLARE_1" FOREIGN KEY (
	"REVIEW_BOARD_NO"
)
REFERENCES "REVIEW_BOARD" (
	"REVIEW_BOARD_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_REVIEW_BOARD_TO_COMMENT_1" FOREIGN KEY (
	"REVIEW_BOARD_NO"
)
REFERENCES "REVIEW_BOARD" (
	"REVIEW_BOARD_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_1" FOREIGN KEY (
	"COMMENT_NO2"
)
REFERENCES "COMMENT" (
	"COMMENT_NO"
);

ALTER TABLE "COMMENT_DECLARE" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_DECLARE_1" FOREIGN KEY (
	"COMMENT_NO"
)
REFERENCES "COMMENT" (
	"COMMENT_NO"
);

ALTER TABLE "SCRAP" ADD CONSTRAINT "FK_MEMBER_TO_SCRAP_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "SCRAP" ADD CONSTRAINT "FK_HIRE_TO_SCRAP_1" FOREIGN KEY (
	"HIRE_NO"
)
REFERENCES "HIRE" (
	"HIRE_NO"
);

ALTER TABLE "MEMBER_DECLARE_CONDITION" ADD CONSTRAINT "FK_MEMBER_TO_MEMBER_DECLARE_CONDITION_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "HIRE_PERIOD" ADD CONSTRAINT "FK_HIRE_TO_HIRE_PERIOD_1" FOREIGN KEY (
	"HIRE_NO"
)
REFERENCES "HIRE" (
	"HIRE_NO"
);


