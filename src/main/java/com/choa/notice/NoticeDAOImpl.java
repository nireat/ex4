package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnect;
import com.choa.util.ListInfo;
import com.choa.util.RowMaker;

import oracle.jdbc.proxy.annotation.Pre;



@Repository
//^ 이것이 아이디가 됨 -> 서비스의 @Qualifier 에 동일하게 입력해야함
//NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE="NoticeMapper.";
	//변수 앞 final : 상수처럼 사용해라 , 변수명은 항상 대문자로
/*	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	
	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		
		
		return sqlSession.selectList(NAMESPACE+"list", listInfo);
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		BoardDTO boardDTO=sqlSession.selectOne(NAMESPACE+"view", num);
		
		return boardDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"write", boardDTO);
	
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"delete", num);
	}

	@Override
	public int boardCount(ListInfo listInfo) throws Exception {

		return sqlSession.selectOne(NAMESPACE+"count", listInfo);
	}

	@Override
	public void boardHit(int num) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update notice set hit=hit+1 where num=?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		
	}


}
