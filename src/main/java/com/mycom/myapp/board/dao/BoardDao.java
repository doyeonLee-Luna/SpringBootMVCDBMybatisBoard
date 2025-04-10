package com.mycom.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	// 목록
	List<BoardDto> listBoard(BoardParamDto boardParamDto); // limit, offset
	int listBoardTotalCount(); // 아무것도 x
	
	List<BoardDto> listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
	int listBoardSearchWordTotalCount(BoardParamDto boardParamDto); // searchWord
	
	// 상세
	BoardDto detailBoard(BoardParamDto boardParamDto); // boardId 가 중요
	
	// 등록, 수정, 삭제
	int insertBoard(BoardDto boardDto);
	int updateBoard(BoardDto boardDto);
	int deleteBoard(int boardId);
	
	// 상세 - 조회수 처리 부분
	// 현재 사용자가 현재 게시글을 읽었는지 판단하는 것이 필요하다 
	int countBoardUserRead(BoardParamDto boardParamDto); // boardId, userSeq
	// 현재 사용자가 현재 게시글을 읽었다는 표시 추가
	int insertBoardUserRead(BoardParamDto boardParamDto); // boardId, userSeq 를 받아서 처리한다
	// 현재 게시글의 조회수 증가
	int updateBoardReadCount(int boardId);
}
