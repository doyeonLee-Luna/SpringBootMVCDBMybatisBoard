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
}
