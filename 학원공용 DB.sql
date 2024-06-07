CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"AUTHORITY_NO"	CHAR(1)		NOT NULL,
	"MEMBER_EMAIL"	VARCHAR2(300)		NOT NULL,
	"MEMBER_PW"	VARCHAR2(300)		NOT NULL,
	"MEMBER_PHONE_NUMBER"	VARCHAR2(300)		NOT NULL,
	"ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MEMBER_NAME"	VARCHAR2(300)		NOT NULL,
	"MEMBER_STATUS"	NUMBER	DEFAULT 4	NOT NULL,
	"MEMBER_GENDER"	NUMBER		NOT NULL,
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
	"RESUME_TITLE"	VARCHAR2(200)		NOT NULL,
	"OPEN_RANGE"	NUMBER		NOT NULL,
	"INTRODUCE"	CLOB	DEFAULT '없음'	NULL,
	"IMAGE"	VARCHAR2(300)		NULL
);

COMMENT ON COLUMN "RESUME"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "RESUME"."CAREER" IS '1:신입, 2:경력';

COMMENT ON COLUMN "RESUME"."RESUME_TITLE" IS '이력서 제목';

COMMENT ON COLUMN "RESUME"."OPEN_RANGE" IS '공개 범위(1:지원회사만, 2:전체회사에)';

COMMENT ON COLUMN "RESUME"."INTRODUCE" IS '자기소개 직접 입력';

COMMENT ON COLUMN "RESUME"."IMAGE" IS '증명사진';

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
	"SCHOOL_NAME"	VARCHAR2(100)		NOT NULL,
	"SCHOOL_PERIOD"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "RESUME_EDUCATION"."RESUME_EDUCATION_NO" IS '이력서별 학력 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."EDUCATION_STATUS_NO" IS '학력 상태 번호(1:졸업, 2: 재학, 3: 휴학, 4: 중퇴, 5: 수료)';

COMMENT ON COLUMN "RESUME_EDUCATION"."EDUCATION_SCHOOL" IS '학력 번호';

COMMENT ON COLUMN "RESUME_EDUCATION"."SCHOOL_NAME" IS '학교/기관명';

COMMENT ON COLUMN "RESUME_EDUCATION"."SCHOOL_PERIOD" IS '재학 기간(날짜~날짜)';

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

COMMENT ON COLUMN "HIRE"."HIRE_TERM" IS '근무기간(숫자 타입)';

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
	"SHOP_ADDRESS"	VARCHAR2(200)		NOT NULL,
	"SHOP_PROFILE"	VARCHAR2(300)		NULL
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

COMMENT ON COLUMN "SHOP"."SHOP_PROFILE" IS '상점 프로필 이미지';

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
	"REVIEW_BOARD_CODE"	NUMBER		NOT NULL,
	"REVIEW_TITLE"	NVARCHAR2(100)		NOT NULL,
	"REVIEW_BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"REVIEW_BOARD_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"REVIEW_BOARD_UPDATE_DATE"	DATE		NOT NULL
);

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "REVIEW_BOARD"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_CODE" IS '1:공지 2:일반';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_TITLE" IS '게시글제목';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_CONTENT" IS '게시글내용';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_WRITE_DATE" IS '작성일';

COMMENT ON COLUMN "REVIEW_BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "REVIEW_BOARD"."BOARD_DEL_FL" IS '게시글 삭제 여부(Y,N)';

COMMENT ON COLUMN "REVIEW_BOARD"."REVIEW_BOARD_UPDATE_DATE" IS '마지막 수정일';

CREATE TABLE "BOARD_DECLARE" (
	"REVIEW_BOARD_DECLARE_NO"	NUMBER		NOT NULL,
	"REVIEW_BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"REVIEW_BOARD_CONTENT"	VARCHAR2(1500)		NOT NULL,
	"REVIEW_BOARD_CONDITION"	CHAR(1)	DEFAULT 0	NOT NULL
);

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_DECLARE_NO" IS '게시글 신고 번호';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "BOARD_DECLARE"."MEMBER_NO" IS '신고한 회원 번호';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_CONTENT" IS '게시글 신고 사유';

COMMENT ON COLUMN "BOARD_DECLARE"."REVIEW_BOARD_CONDITION" IS '0:처리제외/1:처리중/2:처리완료';

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"REVIEW_BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PARENT_COMMENT_NO"	NUMBER		NULL,
	"COMMENT_CONTENT"	VARCHAR2(1000)		NOT NULL,
	"COMMENT_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NULL
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호';

COMMENT ON COLUMN "COMMENT"."REVIEW_BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '회원 번호';

COMMENT ON COLUMN "COMMENT"."PARENT_COMMENT_NO" IS '부모댓글번호';

COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "COMMENT"."COMMENT_WRITE_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제여부(Y,N)';

CREATE TABLE "COMMENT_DECLARE" (
	"COMENT_DECLARE_NO"	NUMBER		NOT NULL,
	"COMMENT_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"COMMENT_DECLARE_CONTENT"	VARCHAR2(1500)		NOT NULL,
	"COMMENT_DECLARE_CONDITION"	CHAR(1)	DEFAULT 0	NOT NULL
);

COMMENT ON COLUMN "COMMENT_DECLARE"."COMMENT_NO" IS '댓글 번호';

COMMENT ON COLUMN "COMMENT_DECLARE"."MEMBER_NO" IS '신고한 회원 번호';

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

COMMENT ON COLUMN "BOARD_TYPE"."REVIEW_BOARD_CODE" IS '1:공지 2:일반';

COMMENT ON COLUMN "BOARD_TYPE"."REVIEW_BOARD_NAME" IS '게시판 이름(공지/일반)';

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

CREATE TABLE "LICENSE" (
	"LICENSE_NO"	NUMBER		NOT NULL,
	"RESUME_NO"	NUMBER		NOT NULL,
	"LICENSE_FROM"	VARCHAR2(300)		NOT NULL,
	"LICENSE_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"LICENSE_NAME"	VARCHAR2(200)		NOT NULL,
	"LICENSE_SCORE"	VARCHAR2(20)	DEFAULT '수료'	NULL
);

COMMENT ON COLUMN "LICENSE"."LICENSE_NO" IS '자격증 번호';

COMMENT ON COLUMN "LICENSE"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "LICENSE"."LICENSE_FROM" IS '발급기관명';

COMMENT ON COLUMN "LICENSE"."LICENSE_DATE" IS '발급일자';

COMMENT ON COLUMN "LICENSE"."LICENSE_NAME" IS '자격증 이름';

COMMENT ON COLUMN "LICENSE"."LICENSE_SCORE" IS '점수';

CREATE TABLE "PAY_TYPE" (
	"PAY_NO"	NUMBER		NOT NULL,
	"PAY_NAME"	VARCHAR2(10)	DEFAULT '시급'	NULL
);

COMMENT ON COLUMN "PAY_TYPE"."PAY_NO" IS '급여 유형 번호';

COMMENT ON COLUMN "PAY_TYPE"."PAY_NAME" IS '급여명(시급/일급/주급/월급)';

CREATE TABLE "RESUME_WORK" (
	"RESUME_NO"	NUMBER		NOT NULL,
	"TYPE_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "RESUME_WORK"."RESUME_NO" IS '이력서 번호';

COMMENT ON COLUMN "RESUME_WORK"."TYPE_NO" IS '직종 번호';

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

ALTER TABLE "HIRE_PERIOD" ADD CONSTRAINT "PK_HIRE_PERIOD" PRIMARY KEY (
	"PERIOD_NO"
);

ALTER TABLE "LICENSE" ADD CONSTRAINT "PK_LICENSE" PRIMARY KEY (
	"LICENSE_NO"
);

ALTER TABLE "PAY_TYPE" ADD CONSTRAINT "PK_PAY_TYPE" PRIMARY KEY (
	"PAY_NO"
);

ALTER TABLE "RESUME_WORK" ADD CONSTRAINT "PK_RESUME_WORK" PRIMARY KEY (
	"RESUME_NO",
	"TYPE_NO"
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
);--*********************************
--테이블 컬럼 자료형 바꾸는 ALTER구문!!
ALTER TABLE RESUME_EDUCATION  
MODIFY EDUCATION_STATUS_NO NUMBER;

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
); --*************
--테이블 컬럼 자료형 바꾸는 ALTER구문!!
ALTER TABLE RESUME_LOCATION  
MODIFY DONG_NO NUMBER;


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

ALTER TABLE "BOARD_DECLARE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_DECLARE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
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
	"PARENT_COMMENT_NO"
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

ALTER TABLE "COMMENT_DECLARE" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_DECLARE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
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

ALTER TABLE "LICENSE" ADD CONSTRAINT "FK_RESUME_TO_LICENSE_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);

ALTER TABLE "RESUME_WORK" ADD CONSTRAINT "FK_RESUME_TO_RESUME_WORK_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);

ALTER TABLE "RESUME_WORK" ADD CONSTRAINT "FK_WORK_TYPE_TO_RESUME_WORK_1" FOREIGN KEY (
	"TYPE_NO"
)
REFERENCES "WORK_TYPE" (
	"TYPE_NO"
);
--모집 시작 날짜 
ALTER TABLE HIRE 
MODIFY HIRE_STATUS NULL;
-- ALTER TABLE [테이블명] MODIFY [컬럼명] [데이터타입] DEFAULT [값];
ALTER TABLE HIRE MODIFY HIRE_STATUS NUMBER DEFAULT 0;
INSERT INTO WORK_TYPE 
VALUES (9, '교육/강사');
SELECT * FROM WORK_TYPE;

ALTER TABLE SHOP ADD(ADDRESS_DETAIL VARCHAR2(2000));
COMMENT ON COLUMN SHOP.ADDRESS_DETAIL IS '상점 상세주소';
ALTER TABLE SHOP MODIFY ADDRESS NOT NULL;
CREATE SEQUENCE SEQ_HIRE_NO NOCACHE;
INSERT INTO WORK_TYPE 
VALUES (9, '교육/강사');
--SHOP_NO, DONG_NO, SHOP_NAME, SHOP_EMAIL, SHOP_PW
--SHOP_TEL, SHOP_BRN, SHOP_OWNER, SHOP_PROFILE
--ADDRESS, ADDRESS_DETAIL
SELECT * FROM DOSI d ;
CREATE SEQUENCE SEQ_SHOP_NO NOCACHE;
INSERT INTO SHOP 
VALUES( SEQ_SHOP_NO.NEXTVAL,
);
ALTER TABLE DOSI  
MODIFY DOSI_NAME VARCHAR2(30);
SELECT * FROM SHOP;
INSERT INTO SIGUNGU s2 
VALUES (11,2,'SK');
SELECT * FROM DONG;
SELECT * FROM SIGUNGU s ;
ALTER TABLE SIGUNGU  MODIFY SIGUNGU_NAME VISIBLE;
ALTER TABLE "SHOP" MODIFY ADDRESS NULL;
UPDATE SHOP SET ADDRESS = NULL;
ALTER TABLE HIRE ADD DONG_NO NUMBER NOT NULL;
SELECT COUNT(*) FROM "SIGUNGU";
UPDATE SIGUNGU SET DOSI_NO =24 WHERE DOSI_NO =33;
DELETE FROM DOSI WHERE DOSI_NO=33;
SELECT COUNT(*) FROM "DONG";
ALTER TABLE SHOP DROP COLUMN ADDRESS;
SELECT * FROM "DOSI"
JOIN "SIGUNGU" USING (DOSI_NO)
JOIN "DONG" USING (SIGUNGU_NO)
WHERE SIGUNGU_NAME='강남구';
ALTER TABLE HIRE ADD WORK_END VARCHAR2(300) NOT NULL;
SELECT DOSI_NAME, SIGUNGU_NAME FROM "DOSI" JOIN "SIGUNGU"
USING (DOSI_NO) JOIN "DONG" USING (SIGUNGU_NO)
WHERE DONG_NAME='잠실동';
INSERT INTO PAY_TYPE pt 
VALUES (5, '일급');
SELECT DOSI_NAME, SIGUNGU_NAME, DONG_NAME 
FROM "DONG" 
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DONG_NO=102;
DELETE FROM "HIRE"
WHERE HIRE_NO=12;
SELECT SYSDATE FROM "DUAL";
SELECT COUNT(*) , DONG_NAME
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
GROUP BY DONG_NAME 
HAVING COUNT(*)>1;
SELECT * FROM "SHOP";
--hireTerm 1,7,3,6,365
BEGIN
		FOR I IN 1..6 LOOP
			INSERT INTO "HIRE" 
			VALUES (SEQ_HIRE_NO.NEXTVAL,
							100,
							CEIL(DBMS_RANDOM.VALUE(0,9)),
							2,
							SEQ_HIRE_NO.CURRVAL ||'번째 공고',
						SEQ_HIRE_NO.CURRVAL ||'번째 공고 내용입니다.',
						DEFAULT,
						TO_DATE('20240809', 'YYYY-MM-DD'),
						CEIL(DBMS_RANDOM.VALUE(0,50)),
						365,
						'M'
						,DEFAULT,
						'mom, tue, sat, sun',
						CEIL(DBMS_RANDOM.VALUE(550000,1500000)),
						727, 
						'어디어딘가',
						'12:30',
						'20:30',
						'이름',
						'KH@NAVER.COM',
						'01020304050',
						'상점',
						'서울시 강남구 도로명주소'
					);
		END LOOP;
	END; --이거는 ALT X로 실행해야 된다!!
	-- PL/SQL은 별도의 처리 안 하면 무조건 -1이 결과값으로 나온다
SELECT DONG_NO, DONG_NAME
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DOSI_NAME LIKE '%'||'서울'||'%'
AND SIGUNGU_NAME = '강서구';

SELECT DONG_NAME, SIGUNGU_NAME, DOSI_NAME
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DONG_NO=900;
--최신 순 10개 공고만 조회(단, 저장 상태이고, 마감 지나지 않은 것만)
SELECT * FROM (
	SELECT * FROM "HIRE"
	WHERE HIRE_STATUS = 0
	AND (SELECT SYSDATE FROM "DUAL")<HIRE_END
)
WHERE ROWNUM <=10
ORDER BY HIRE_NO DESC;

SELECT COUNT(*), DONG_NAME, SIGUNGU_NAME, DOSI_NAME
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
GROUP BY DONG_NAME, SIGUNGU_NAME, DOSI_NAME
HAVING COUNT(*)>1;
/* SELECT문 해석 순서
  5 : SELECT 컬럼명 AS 별칭, 계산식, 함수식
  1 : FROM 참조할 테이블명
  2 : WHERE 컬럼명 | 함수식 비교연산자 비교값
  3 : GROUP BY 그룹을 묶을 컬럼명
  4 : HAVING 그룹함수식 비교연산자 비교값
  6 : ORDER BY 컬럼명 | 별칭 | 컬럼순번 정렬방식 [NULLS FIRST | LAST];
*/
SELECT DONG_NO, DONG_NAME, SIGUNGU_NAME, DOSI_NAME
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DONG_NAME ='평리동';
SELECT * FROM "HIRE"; --4,100

DELETE FROM ALBAYA6."DONG"
   WHERE DONG_NO IN (
   SELECT MAX(DONG_NO)
   FROM ALBAYA6."DONG"
   JOIN ALBAYA6."SIGUNGU" USING (SIGUNGU_NO)
   JOIN ALBAYA6."DOSI" USING (DOSI_NO)
   GROUP BY DONG_NAME, SIGUNGU_NAME, DOSI_NAME
HAVING COUNT(*)>1);
CREATE SEQUENCE SEQ_SHOP_NO NOCACHE;
SELECT * 
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE SIGUNGU_NAME = 'ㅅ';

SELECT SHOP_NAME, HIRE_TITLE, WORK_START,HIRE_NO,
		WORK_END, DOSI_NAME, SIGUNGU_NAME, PAY_NAME, PAY_INPUT
		 FROM "HIRE" A
		JOIN "SHOP" B ON (B.SHOP_NO=A.SHOP_NO)
		JOIN "DONG" C ON (C.DONG_NO=A.DONG_NO)
		JOIN "SIGUNGU" D ON (D.SIGUNGU_NO=C.SIGUNGU_NO)
		JOIN "DOSI" E ON (E.DOSI_NO=D.DOSI_NO)
		JOIN "PAY_TYPE" F ON (F.PAY_NO=A.PAY_NO)
		WHERE HIRE_STATUS = 0
		ORDER BY HIRE_NO DESC;
	
	/*공고 번호, 공고제목, 공고내용, 업직종 이름, 모집인원, 
	모집종료일(2024-03-23), 근무기간(몇개월), 근무요일(월,수,금)
	근무 시작 시간, 근무 종료 시간, 급여종류명, 급여양, 모집성별,
	근무지 시군구, 근무지 상세주소, 담당자명, 담당자연락처,담당자이메일,
	 * */
--공고 상세조회
	--경기도 고양시덕양구 화정동
	SELECT SUBSTR(SIGUNGU_NAME,1,3)||' '||SUBSTR(SIGUNGU_NAME,4)  FROM SIGUNGU  
WHERE LENGTH(SIGUNGU_NAME)>=5;
	SELECT HIRE_NO, HIRE_TITLE, HIRE_CONTENT, TYPE_NAME, HIRE_COUNT,
	TO_CHAR(HIRE_END, 'YYYY-MM-DD') HIRE_END,
	DECODE(HIRE_TERM, 1, '하루', 7, '일주일', 
	3, '1~3개월', 6, '3~6개월', 365, '1년 이상') HIRE_TERM,
	WORK_DAY,
	WORK_START, WORK_END, PAY_NAME, PAY_INPUT , 
	DECODE(HIRE_GENDER, 'A', '성별무관', 'F', '여성', 'M', '남성') HIRE_GENDER,
    CASE 
    	WHEN LENGTH(SIGUNGU_NAME)>=5 THEN DOSI_NAME||' '||SUBSTR(SIGUNGU_NAME,1,3)||' '||SUBSTR(SIGUNGU_NAME,4)||' '||DONG_NAME
    	ELSE DOSI_NAME||' '||SIGUNGU_NAME||' '||DONG_NAME 
    END
    ADDRESS,
	ADDRESS_DETAIL, SHOP_OWNER, SHOP_TEL, SHOP_EMAIL
	FROM "HIRE" A
	JOIN "WORK_TYPE" B ON (B.TYPE_NO = A.TYPE_NO)
	JOIN PAY_TYPE C ON (C.PAY_NO=A.PAY_NO)
	JOIN "DONG" D ON (D.DONG_NO=A.DONG_NO)
	JOIN "SIGUNGU" E ON (E.SIGUNGU_NO=D.SIGUNGU_NO)
	JOIN "DOSI" F ON (F.DOSI_NO=E.DOSI_NO)
	WHERE HIRE_NO = 13;

UPDATE "HIRE" A SET ROAD_ADDRESS = (SELECT ADDDRESS FROM "HIRE" B WHERE A.HIRE_NO=B.HIRE_NO)
WHERE ROAD_ADDRESS='없음';
DELETE FROM "HIRE";


ALTER TABLE "HIRE" ADD ROAD_ADDRESS VARCHAR2(100) 
DEFAULT '없음' NULL;
SELECT TO_CHAR(SHOP_TEL) FROM "HIRE"; --이렇게 하면 앞의 0 안빠진다.
SELECT SHOP_TEL FROM "HIRE";
/*1:하루
 * 7:일주일
 * 3:1-3개월
 * 6:3-6개월
 * 365:1년 이상
 * */
SELECT 
FROM "DONG"

          (
              SELECT 
                  DOSI_NAME || ' ' || 
                  (CASE 
                      WHEN LENGTH(SIGUNGU_NAME) >= 5 THEN 
                          SUBSTR(SIGUNGU_NAME, 1, 3) || ' ' || SUBSTR(SIGUNGU_NAME, 4) 
                      ELSE 
                          SIGUNGU_NAME 
                  END) || ' ' || 
                  DONG_NAME
              FROM "DONG"
              JOIN "SIGUNGU" USING (SIGUNGU_NO)
              JOIN "DOSI" USING (DOSI_NO)
              WHERE DONG_NO = SHOP.DONG_NO
          ) AS FULL_ADDRESS;

SELECT SIGUNGU_NO 
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
GROUP BY SIGUNGU_NO
HAVING COUNT(DONG_NAME)=0;

SELECT DONG_NO
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
WHERE SIGUNGU_NAME='동구안심출장';

DELETE FROM; 
SELECT  SIGUNGU_NAME, DONG_NO 
FROM SIGUNGU  s
LEFT JOIN DONG d ON (s.SIGUNGU_NO  = d.SIGUNGU_NO)
WHERE d.DONG_NO  IS NULL;
SELECT SIGUNGU_NAME FROM SIGUNGU 
WHERE SIGUNGU_NAME LIKE '%용인%';
SELECT DOSI_NAME, SIGUNGU_NAME
FROM "DOSI"
LEFT JOIN "SIGUNGU" USING (DOSI_NO)
WHERE DOSI_NAME LIKE '%강원%';
DELETE FROM "DONG"
WHERE DONG_NO IN 
(SELECT DONG_NO
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DOSI_NAME = '강원도');
DELETE FROM "SIGUNGU"
WHERE SIGUNGU_NO IN
(SELECT SIGUNGU_NO
FROM "SIGUNGU" 
JOIN "DOSI" USING (DOSI_NO)
WHERE DOSI_NAME = '강원도');
DELETE FROM "DOSI"
WHERE DOSI_NAME='강원도';
SELECT DISTINCT DONG_NAME
FROM "HIRE"
JOIN "DONG" USING (DONG_NO);

SELECT DONG_NAME, DONG_NO,SIGUNGU_NAME, SIGUNGU_NO  
FROM "DONG"
JOIN "SIGUNGU" USING (SIGUNGU_NO)
GROUP BY DONG_NO
HAVING COUNT(*)>1; 
SELECT SIGUNGU_NAME, SIGUNGU_NO , DONG_NO ,DONG_NAME 
FROM "SIGUNGU"
LEFT JOIN "DONG" USING(SIGUNGU_NO)
WHERE SIGUNGU_NAME='가평군';
DELETE FROM "SIGUNGU"
WHERE SIGUNGU_NO=201;
SELECT * FROM SIGUNGU
 WHERE SIGUNGU_NAME LIKE '%포항%'; 

 DELETE FROM "DONG"
WHERE SIGUNGU_NO IN (
	SELECT SIGUNGU_NO
	FROM "SIGUNGU"
	WHERE SIGUNGU_NAME IN ('북구계양출장', '서구검단출장',
	'중구영종출장', '중구용유출장','동구안심출장','북구칠곡출장')
);

DELETE FROM "SIGUNGU"
WHERE SIGUNGU_NAME IN ('북구계양출장', '서구검단출장',
	'중구영종출장', '중구용유출장','동구안심출장','북구칠곡출장');
SELECT * FROM HIRE 
JOIN DONG USING (DONG_NO)
WHERE DONG_NAME='양재동';
SELECT * FROM DONG d 
JOIN "SIGUNGU" USING (SIGUNGU_NO)
JOIN "DOSI" USING (DOSI_NO)
WHERE DONG_NAME='양재동';
-----------------------------------------------
CREATE TABLE "APPLY_HIRE" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"HIRE_NO"	NUMBER		NOT NULL,
	"RESUME_NO"	NUMBER		NULL,
	"APPLY_TITLE"	VARCHAR2(1000)	DEFAULT '없음'	NULL,
	"APPLY_CONTENT"	VARCHAR2(4000)	DEFAULT '없음'	NULL
);

COMMENT ON COLUMN "APPLY_HIRE"."MEMBER_NO" IS '지원한 회원 번호';

COMMENT ON COLUMN "APPLY_HIRE"."HIRE_NO" IS '지원한 공고 번호';

COMMENT ON COLUMN "APPLY_HIRE"."RESUME_NO" IS '첨부한 이력서 번호';

COMMENT ON COLUMN "APPLY_HIRE"."APPLY_TITLE" IS '지원 제목';

COMMENT ON COLUMN "APPLY_HIRE"."APPLY_CONTENT" IS '지원내용';

ALTER TABLE "APPLY_HIRE" ADD CONSTRAINT "PK_APPLY_HIRE" PRIMARY KEY (
	"MEMBER_NO",
	"HIRE_NO"
);
ALTER TABLE "APPLY_HIRE" ADD CONSTRAINT "FK_MEMBER_TO_APPLY_HIRE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "APPLY_HIRE" ADD CONSTRAINT "FK_HIRE_TO_APPLY_HIRE_1" FOREIGN KEY (
	"HIRE_NO"
)
REFERENCES "HIRE" (
	"HIRE_NO"
);

ALTER TABLE "APPLY_HIRE" ADD CONSTRAINT "FK_RESUME_TO_APPLY_HIRE_1" FOREIGN KEY (
	"RESUME_NO"
)
REFERENCES "RESUME" (
	"RESUME_NO"
);
-------------------------------------------
--알림 관련
DROP TABLE "NOTIFICATION";

CREATE TABLE "NOTIFICATION" (
	"NOTIFICATION_NO"	NUMBER		NOT NULL,
	"NOTIFICATION_CONTENT"	NVARCHAR2(500)		NOT NULL,
	"NOTIFICATION_CHECK"	CHAR	DEFAULT 'N'	NOT NULL,
	"NOTIFICATION_DATE"	DATE	DEFAULT CURRENT_DATE	NOT NULL,
	"NOTIFICATION_URL"	NVARCHAR2(500)		NOT NULL,
	"SEND_MEMBER_NO"	NUMBER		NOT NULL,
	"RECEIVE_MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_NO" IS '알림 구분 번호';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CONTENT" IS '알림 내용';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CHECK" IS '알림 읽음 여부(N/Y)';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_DATE" IS '알림 생성 시간';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_URL" IS '알림 클릭 시 연결할 페이지 주소';

COMMENT ON COLUMN "NOTIFICATION"."SEND_MEMBER_NO" IS '알림 보낸 회원 번호';

COMMENT ON COLUMN "NOTIFICATION"."RECEIVE_MEMBER_NO" IS '알림 받는 회원 번호';

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "PK_NOTIFICATION" PRIMARY KEY (
	"NOTIFICATION_NO"
);

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "FK_MEMBER_TO_NOTIFICATION_1" FOREIGN KEY (
	"SEND_MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "FK_MEMBER_TO_NOTIFICATION_2" FOREIGN KEY (
	"RECEIVE_MEMBER_NO"
)
REFERENCES "SHOP" (
	"SHOP_NO"
);
--알림용 시퀀스 생성
CREATE SEQUENCE SEQ_NOTI_NO NOCACHE;
ALTER TABLE "NOTIFICATION" RENAME COLUMN RECEIVE_MEMBER_NO TO RECEIVE_SHOP_NO;
