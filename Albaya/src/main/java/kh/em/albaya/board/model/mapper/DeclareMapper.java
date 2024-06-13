package kh.em.albaya.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.member.model.dto.Member;

@Mapper
public interface DeclareMapper {
	
	//------ 게시글 신고 목록 조회 ------

	/**게시글 신고 목록 조회
	 * @param reviewBoardNo
	 * @param loginMember
	 * @return
	 */
//	List<Declare> selectDeclare(int reviewBoardNo,RowBounds rowBounds);

	/**
	 * 처리중인 신고 게시물 수
	 * @param declareBoardCode
	 * @return
	 */
	int getDeclareCount(int declareBoardCode);

	/**
	 * 신고 게시판의 내용 출력	
	 * @param declareBoardCode
	 * @param rowBounds
	 * @return
	 */
	List<Declare> selectDeclareList(int declareBoardCode, RowBounds rowBounds);

	/** 비동기 게시글 신고 목록 조회
	 * @return
	 */
	List<Declare> selectDeclareList();

	
	//------ 게시글 신고 하기 ------
	
	/** 게시글 신고
	 * @param memberNo
	 * @param boardDeclareContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);

	/**
	 * 신고 게시판 타입 조회
	 * @return
	 */
	List<Map<String, Object>> selectDeclareTypeList();
	
	
	/** 게시글 신고 중복
	 * @param inputDeclare
	 * @return
	 */
	int duplicateDeclare(Declare inputDeclare);
	
	
	/** 신고 당한 회원 중복검사...
	 * @param inputDeclare
	 * @return
	 */
	int duplicateMember(Declare inputDeclare);


	/**
	 * 게시글 중복 신고
	 * @param reviewBoardCondition
	 * @return
	 */
//	int duplicateDeclare(String reviewBoardCondition);
	
	
	//------ 신고 당한 게시물 처리 ------


	/** 신고 반려 처리
	 * @param reviewBoardDeclareNo
	 * @return
	 */
	int rejectDeclare(int reviewBoardDeclareNo);


	/** 신고 확정 처리
	 * @param reviewBoardDeclareNo
	 * @return
	 */
	int completeDeclare(int reviewBoardDeclareNo);


	/** 신고 당한 멤버 상태 변경
	 * @param reviewBoardDeclareNo
	 */
	int changeMemberCondition(int reviewBoardDeclareNo);

	
	/** 신고 확정 게시물 삭제
	 * @return
	 */
	int updateReviewBoard(int reviewBoardDeclareNo);

	int stopMember(int reviewBoardDeclareNo);

	int selectManager(int memberNo);
	




	

}
