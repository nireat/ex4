

	package com.choa.notice;

	import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
	import org.junit.Test;
	import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;

	
	public class NoticeDAOTest extends MyAbstractTest{
		
		@Autowired
		private NoticeDAOImpl noticeDAOImpl;
		
		//private datasource datasource 사용하지 않음
		
/*		@Inject
		private NoticeDAOImpl noticeDAO;
		
		// unit 케이스로서 작동하려면 annotation으로 반드시 @Test 
		@Test
		public void test() throws Exception{
			PageMaker pageMaker = new PageMaker(1);
			List<NoticeDTO> ar=noticeDAO.boardList(rowMaker);
			assertEquals(0, ar.size());
			//빨간색: ar의 길이가 0이 아님
		}
			
		@Test
		public void test2() throws Exception{
			//4.delete test
			int result = noticeDAO.noticeDelete(5);
			assertEquals(1, result);
			//5. 성공 시: 삭제성공 실패 : 그 원인이 junit 콘솔에 나옴
		}*/
	
/*		@Test
		public void connectionTest() throws Exception{
			PageMaker pageMaker = new PageMaker(1, 20);
	
			List<BoardDTO> ar=noticeDAOImpl.boardList(pageMaker.getRowMaker(), "writer", "");
			System.out.println(ar.get(0).getWriter());
			//ar.get(컬럼) , size : 컬럼 배열의 크기
		}*/
		
/*		@Test
		public void countTest() throws Exception{
			int count = noticeDAOImpl.boardCount();
			assertNotEquals(0, count);
		}*/
		
		@Test
		public void connectionTest() throws Exception{
			ListInfo listInfo = new ListInfo();
			listInfo.setFind("nireat");
			listInfo.setSearch("writer");
			int count = noticeDAOImpl.boardCount(listInfo);
			System.out.println(count);
			assertNotEquals(0, count);
		}
		
}
