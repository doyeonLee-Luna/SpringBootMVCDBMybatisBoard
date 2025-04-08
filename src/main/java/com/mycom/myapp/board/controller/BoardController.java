package com.mycom.myapp.board.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.board.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/list") // limit, offset, searchWord 모두 한꺼번에 처리 ( service ,dao, layer 는 분리되어 있다. )
	@ResponseBody
	public BoardResultDto listBoard(BoardParamDto boardParamDto) { 
		
		BoardResultDto boardResultDto = null;
		
		if( Strings.isEmpty(boardParamDto.getSearchWord()) ) { // 검색어가 없는 경우, ( null, "" )
			boardResultDto = boardService.listBoard(boardParamDto); // limit, offset
		} else {
			boardResultDto = boardService.listBoardSearchWord(boardParamDto); // limit, offset, searchword
		}
		
		return boardResultDto;
		
	}
}
