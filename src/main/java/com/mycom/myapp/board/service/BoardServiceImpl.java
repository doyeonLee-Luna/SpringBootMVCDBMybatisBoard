package com.mycom.myapp.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDao boardDao;
	
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public BoardResultDto listBoard(BoardParamDto boardParamDto) { // limit, offset
		BoardResultDto boardResultDto = new BoardResultDto();
		
		// 예외 처리
		// 처리 과정 중 오류 발생 하는 경우  해결하는 방법은?
		// 	1. 직접 제어 ( 사용 )
		// 	2. Spring Framework 처리 의뢰 /error mapping
		try {
			// BoardController 는 BoardService 의 listBoard() 1개 호출
			// BoardService 는 BoardDao 의 listBoard() 의 listBoardTotalCount() 2개 호출
			List<BoardDto> list = boardDao.listBoard(boardParamDto);
			int count = boardDao.listBoardTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult("success");
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// BoardController 는 BoardService 의 listBoard() 1개 호출
			// BoardService 는 BoardDao 의 listBoard() 의 listBoardTotalCount() 2개 호출
			List<BoardDto> list = boardDao.listBoardSearchWord(boardParamDto);
			int count = boardDao.listBoardSearchWordTotalCount(boardParamDto); // searchWord
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult("success");
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}
		return boardResultDto;
	}

	// 게시글 상세 정보 + 조회수 처리 
	// transaction test
	// 1. @Transactional 사용하지 않은 상태 ( Spring 이 Transaction 을 관리 하는 AOP 가 관려하지 않는다.)
	//		insert 는 되지만 update 는 실패하게 된다. 
	// 2. @Transactionl 사용하는 경우 ( Spring Transaction 관리 AOP 가 관여하게 된다. PointCut 에 추가된다.)
	//		2-1. RuntimeExcpetion 계열의 객체가 throw 되어서 Transaction 관리 AOP 에게 전달이 되면 rollback이 된다.
	//		2-2.예외가 발생하지 않으면 => commit 이 발생한다.
	// 		3. RuntimeExcpetion 계열 객체 throw 가 되어도 try-catch 로 묶어버리면 Transaction 관리 AOP 가 관여로 전달되지 않는다.
	//			catch block 에서 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 을 통해 rollback 을 처리하도록 한다.
	@Override
	@Transactional
	public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
//			
//			// 조회수 처리
//			// 현재 게시글에 대한 현재 사용자의 조회 여부 확인
			int userReadCnt = boardDao.countBoardUserRead(boardParamDto);
//			
			
			
			System.out.println("boardId : " + boardParamDto.getBoardId());
			System.out.println("userSeq : " + boardParamDto.getUserSeq());
			System.out.println("userReadCnt : " + userReadCnt);
			
			if( userReadCnt == 0 ) { // 현재 게시글을 처음 읽는 상황
				boardDao.insertBoardUserRead(boardParamDto); // 현재 게시글을 현재 사용자가 읽었다. 표시 등록 
				
				// transaction test 
				String s = null;
				s.length();
				
				boardDao.updateBoardReadCount(boardParamDto.getBoardId()); // 현재 게시글 조회수 증가 처 리 
			}
			
			//게시글 상세 정보
			BoardDto boardDto = boardDao.detailBoard(boardParamDto);
			// sameUser 	
			if( boardDto.getUserSeq() == boardParamDto.getUserSeq() ) {
				boardDto.setSameUser(true);
			} else {
				boardDto.setSameUser(false);
			}
			
			boardResultDto.setDto(boardDto);
			boardResultDto.setResult("success");
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
//			
//			// spring 제안 방법
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			// RuntimeException 객체 생성 & Throw
//			throw new RuntimeException("!!");
//			throw new IllegalStateException("!!");
		}
		
		return boardResultDto;
	

	}

	@Override
	public BoardResultDto insertBoard(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.insertBoard(boardDto);
			
			if( ret == 1 ) { boardResultDto.setResult("success"); }
			else boardResultDto.setResult("fail");
				
			
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");		
		}
		
		return boardResultDto;
	
	}

	@Override
	public BoardResultDto updateBoard(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.updateBoard(boardDto);
			
			if( ret == 1 ) { boardResultDto.setResult("success"); }
			else boardResultDto.setResult("fail");
				
			
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");		
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto deleteBoard(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.deleteBoard(boardId);
			
			if( ret == 1 ) { boardResultDto.setResult("success"); }
			else boardResultDto.setResult("fail");
				
			
		} catch(Exception e ) {
			e.printStackTrace();
			boardResultDto.setResult("fail");		
		}
		
		return boardResultDto;
	}

}
