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
	
	//Controller 가 바라보는 BoardService 는 별도의 조회수 관련 메소드를 가지지 않는다. 
	// detailBoard() 에서 모두 처리한다
	// Controller 는 Business Logic 인 조회수 처리에 관여하지 않는다. 
}
