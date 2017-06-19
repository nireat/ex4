package com.choa.freeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;

@Service
public class FreeboardServiceImpl implements BoardService{
	
	@Autowired
	private FreeboardDAOImpl freeboardDAOImpl;
	
	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		int totalCount = freeboardDAOImpl.boardCount();
		PageMaker pageMaker = new PageMaker(curPage);
		MakePage makePage = pageMaker.getMakePage(totalCount);
		
		return freeboardDAOImpl.boardList(pageMaker.getRowMaker());
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		
		return freeboardDAOImpl.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		
		return freeboardDAOImpl.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return freeboardDAOImpl.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		
		return freeboardDAOImpl.boardDelete(num);
	}
	
	
	
}
