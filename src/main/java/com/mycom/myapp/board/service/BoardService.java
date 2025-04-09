package com.mycom.myapp.board.service;



import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;


public interface BoardService {
	BoardResultDto listBoard(BoardParamDto boardParamDto); // limit, offset
	BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
	
	BoardResultDto detailBoard(BoardParamDto boardParamDto); // boardId 
	
	BoardResultDto insertBoard(BoardDto boardDto);
	BoardResultDto updateBoard(BoardDto boardDto);
	BoardResultDto deleteBoard(int boardId);
}
