

	package com.choa.notice;

	import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

	import org.junit.Assert;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choa.util.PageMaker;

	
	public class NoticeDAOTest extends MyAbstractTest{
		
	
		@Inject
		private NoticeDAOImpl noticeDAO;
		
		// unit 케이스로서 작동하려면 annotation으로 반드시 @Test 
		//@Test
		public void test() throws Exception{
			PageMaker pageMaker = new PageMaker(1);
			List<NoticeDTO> ar=noticeDAO.boardList(rowMaker);
			assertEquals(0, ar.size());
			//빨간색: ar의 길이가 0이 아님
		}
		/*	
		@Test
		public void test2() throws Exception{
			//4.delete test
			int result = noticeDAO.noticeDelete(5);
			assertEquals(1, result);
			//5. 성공 시: 삭제성공 실패 : 그 원인이 junit 콘솔에 나옴
		}*/
	

}
