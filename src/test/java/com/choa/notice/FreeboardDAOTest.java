package com.choa.notice;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.freeboard.FreeboardDAOImpl;
import com.choa.freeboard.FreeboardDTO;

public class FreeboardDAOTest extends MyAbstractTest{
	
	@Autowired
	private FreeboardDAOImpl freeboardDAOImpl;
	
	@Test
	public void connectionTest() throws Exception{
		FreeboardDTO freeboardDTO = new FreeboardDTO();
		freeboardDTO.setWriter("nireat");
		freeboardDTO.setTitle("need you");
		freeboardDTO.setContents("ddddddddddddddd miss you");
		int result = freeboardDAOImpl.boardWrite(freeboardDTO);
		
		assertNotNull(1, result);
	}
}
