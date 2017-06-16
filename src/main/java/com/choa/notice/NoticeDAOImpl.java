package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnect;
import com.choa.util.RowMaker;

import oracle.jdbc.proxy.annotation.Pre;



@Repository
//^ 이것이 아이디가 됨 -> 서비스의 @Qualifier 에 동일하게 입력해야함
//NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO{
	
	@Autowired
	private DataSource dataSource;
	
/*	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		String sql = "select * from "
				+ " (select rownum R, N.* from "
				+ " (select * from notice order by num desc) N)"
				+ " where R between ? and ?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		
		rs = st.executeQuery();
		
		while(rs.next()){
			NoticeDTO noticeDTO = new NoticeDTO();
			
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		DBConnect.disConnect(rs, st, con);
		
		return ar;
		
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		NoticeDTO noticeDTO = null;
		String sql = "select * from notice where num=?";
	
		st = con.prepareStatement(sql);
		st.setInt(1, num);	
		rs = st.executeQuery();
		
		if(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnect.disConnect(rs, st, con);
		
		return noticeDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		String sql = "insert into freeboard values (freeboard_seq.nextval, ?, ?, ?, sysdate, 0)";
		
		st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		String sql = "update notice set title=?, contents=?, reg_date=sysdate where num=?";
		
		st = con.prepareStatement(sql);
		boardDTO = new BoardDTO();
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}

	@Override
	public int boardDelete(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		int result = 0;
		String sql = "delete from notice where num=?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		return result;
	}

	@Override
	public int boardCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "select nvl(count(num), 0) from notice";
						//num에 null이 오면 0으로 처리해주자
		st = con.prepareStatement(sql);
	
		rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		DBConnect.disConnect(rs, st, con);
		return result;
	}

	@Override
	public void boardHit(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		String sql = "update notice set hit=hit+1 where num=?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		
	}


}
