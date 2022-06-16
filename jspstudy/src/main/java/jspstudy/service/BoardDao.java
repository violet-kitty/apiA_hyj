package jspstudy.service;

import java.sql.*;
import java.util.ArrayList;

import jspstudy.dbconn.DBconn;
import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.MemberVo;
import jspstudy.domain.SearchCriteria;
	
public class BoardDao {
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDao() {
		DBconn db = new DBconn();
		this.conn = db.getConnection();
	}
	//DB에 글 저장
	public int insertBoard(String subject, String content, String writer, String ip, int midx, String fileName) {
		int value = 0;
		String sql="";
		
		sql="INSERT INTO A_BOARD(BIDX,ORIGINBIDX,DEPTH,LEVEL_,SUBJECT,CONTENT,WRITER,IP,MIDX,FILENAME)"
				+ "VALUES(BIDX_SEQ.NEXTVAL,BIDX_SEQ.NEXTVAL,0,0,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			pstmt.setString(6, fileName);	// 파일 이름만 DB에 들어감
			
			//executeUpdate()는 int형 반환(실행되면 1, 오류나면 0)
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return value;
	}
	// DB의 글 가져오기
	//쿼리안에서 like 써서 검색 기능 쓰기위해 scri 값 받아옴
	//SearchCriteria 안에 검색 키워드와 검색 타입이 들어있음
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri){
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		ResultSet rs = null;
		
		try {
			String str = "";
			//? 안에는 값만 대입 가능 따라서 if 구문을 사용해 줌
			if(scri.getSearchType().equals("subject")) {
				str = "and subject like ?";
			}else {
				str = "and writer like ?";
			}
			
			//전부 다 불러오는 코드
			//String sql="select * from a_board where delyn='N' order by originbidx desc, depth asc";
			//페이징 처리
			String sql = "SELECT * FROM ("
					+ " SELECT ROWNUM AS RNUM, A.* FROM ("
					+ "		SELECT * FROM A_BOARD WHERE DELYN='N' "
					+ str
					+ " ORDER BY ORIGINBIDX DESC, DEPTH ASC)A"
					+ ")B "
					+ "WHERE RNUM BETWEEN ? AND ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setInt(2, (scri.getPage()-1)*15+1);
			pstmt.setInt(3, (scri.getPage()*15));
			
			// executeQuery()는 ResultSet을 반환. 따라서 값을 담을 필요가 있는 경우 executeQuery를 써야함
			rs = pstmt.executeQuery();
			
			//다음 값이 존재하면 true. 그 행으로 커서 이동
			while(rs.next()){
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));			// rs에 복사된 데이터들 bv에 옮겨담기
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLevel_(rs.getInt("level_"));
				alist.add(bv);	// 각각의 bv 객체를 alist에 추가
			}
		} catch (SQLException e) {
			System.out.println("오류!");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return alist;
	}
	// 해당 bidx의 글 내용 보여주기
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;
		
		String sql="select * from a_board where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bv = new BoardVo(); // 가져올 값이 있을 때 객체 생성
				// 글번호, 계층 값들
				bv.setBidx(rs.getInt("bidx"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setDepth(rs.getInt("depth"));
				// 보여줄 내용들
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setFilename(rs.getString("filename"));
			}
			else {
				System.out.println("데이터 없음");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return bv;
	}
	//글 수정
	public int boardModify(int bidx, String subject, String content, String writer) {
		int value = 0;
		
		String sql = "update a_board set subject=?, content=?, writer=? where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, bidx);
			value = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	//글 삭제 // 글을 완전히 삭제하는 것이 아닌, 삭제여부를 나타내주는 delyn을 Y로 바꿔줌
	public int boardDelete(int bidx) {
		int value = 0;
		
		String sql = "update a_board set delyn='Y' where bidx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	// 답변 글 DB에 저장
	public int replyBoard(BoardVo bv) {
		int value = 0;
		
		String sql1 = "update a_board set depth=depth+1 where originbidx=? and depth > ?";
		String sql2 = "INSERT INTO A_BOARD(BIDX,ORIGINBIDX,DEPTH,LEVEL_,SUBJECT,CONTENT,WRITER,IP,MIDX)"
				+ "VALUES(BIDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		
		/*
		 * 아래 두 작업은 두개의 작업이 한쌍으로 같이 일어나야함
		 * 하나만 실행이 되면 데이터 처리가 제대로 되지 않아 데이터의 일관성을 보장할 수 없음
		 * 따라서 트랜잭션을 걸어줌
		 * 트랜잭션 사이에 다른 트랜잭션이 끼어들 수 없고 하나라도 실행되지 않는다면 전부가 다 실행되지 않음
		 */
		try {
			//자바에서는 쿼리쓰면 commit을 자동으로 해줌
			//하지만 트랜잭션 써야하니 commit false로 설정
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			value = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth()+1);
			pstmt.setInt(3, bv.getLevel_()+1);
			pstmt.setString(4, bv.getSubject());
			pstmt.setString(5, bv.getContent());
			pstmt.setString(6, bv.getWriter());
			pstmt.setString(7, bv.getIp());
			pstmt.setInt(8, bv.getMidx());
			value = pstmt.executeUpdate();
			
			// update와 insert 모두 실행 됐을 경우 commit
			conn.commit();
			
		}catch(SQLException e) {
			// 하나라도 에러 발생하면 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return value;
	}
	//글의 총 갯수 출력하는 함수
	//scri를 받아서 검색기능을 사용한 경우 검색된 데이터의 수만 뽑게 만듦
	public int boardTotal(SearchCriteria scri) {
		int cnt = 0;
		ResultSet rs = null;
		
		String str = "";
		//? 안에는 값만 대입 가능 따라서 if 구문을 사용해 줌
		if(scri.getSearchType().equals("subject")) {
			str = "and subject like ?";
		}else {
			str = "and writer like ?";
		}
		
		String sql = "select count(*) as cnt from a_board where delyn='N' "+str+"";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				//conn.close(); //이후 다른 함수도 써야하므로 close 하면 안됨
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
