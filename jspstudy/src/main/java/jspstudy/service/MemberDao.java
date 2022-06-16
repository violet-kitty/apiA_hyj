package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.DBconn;
import jspstudy.domain.MemberVo;

//메소드만 있는 클래스
public class MemberDao {
	
	//어디든 사용해야 하므로 전역변수로 만들어 어떤 메소드든 접근 가능하게함
	//연결객체
	private Connection conn;
	//구문객체
	private PreparedStatement pstmt;
	
	public MemberDao() {
		DBconn db = new DBconn();
		this.conn = db.getConnection();
	}
	
	public int insertMember(String memberid, String memberpwd, String membername, String memberemail, String membergender, String memberaddr, String memberphone, String memberjumin, String hobby, String ip){
		int value=0;
		//statement에서 진화된 구문객체 선언 및 초기화
		//변수를 직접 대입하지 않고 메소드를 통해서 집어넣음

		/*
		 * String
		 * sql="insert into a_member(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBEREMAIL,MEMBERGENDER,MEMBERADDR,MEMBERPHONE,MEMBERJUMIN,MEMBERHOBBY,MEMBERIP)"
		 * +"values(a_midx_seq.nextval,'"+memberid+"','"+memberpwd+"','"+membername+
		 * "','"+memberemail+"','"+membergender+"','"+memberaddr+"','"+memberphone+"','"
		 * +memberjumin+"','"+hobby+"','"+ip+"')";
		 */
		
		String sqlp = "insert into a_member(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBEREMAIL,MEMBERGENDER,MEMBERADDR,MEMBERPHONE,MEMBERJUMIN,MEMBERHOBBY,MEMBERIP)"
				+"values(a_midx_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		
		//쿼리를 실행하기 위한 쿼리실행 클래스를 생성한다.
		//연결하는 명령문의 경우 예외가 발생할 수 있으니 try-catch 구문에 넣어줘야함.(안그러면 오류)
		try{
	/* 		Statement stmt = conn.createStatement();
			value = stmt.executeUpdate(sql); */
			
			
			pstmt = conn.prepareStatement(sqlp);
			//PreparedStatement객체.setString(물음표 순서, 들어갈 값)
			//메소드 안에서 ''는 거르기 때문에 해킹 위험 적어짐
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberpwd);
			pstmt.setString(3, membername);
			pstmt.setString(4, memberemail);
			pstmt.setString(5, membergender);
			pstmt.setString(6, memberaddr);
			pstmt.setString(7, memberphone);
			pstmt.setString(8, memberjumin);
			pstmt.setString(9, hobby);
			pstmt.setString(10, ip);
			
			value = pstmt.executeUpdate();
			
		}catch(Exception e){
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

	public ArrayList<MemberVo> memberSelectAll(){
		
		//객체 생성
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		ResultSet rs = null;
		
		String sql="SELECT * FROM A_MEMBER WHERE DELYN='N' ORDER BY MIDX DESC";
		
		
		try{
			//Connection 객체를 통해서 문자열을 쿼리화 시킨다.
			pstmt = conn.prepareStatement(sql);
			
			//내부에서 데이터를 담기 위한 전용 클래스 executeQuery()
			//리턴값으로 resultset이 있음
			//여러 데이터들을 그대로 복사해서 담는 클래스 ResultSet
			rs = pstmt.executeQuery();
			
			//반복문 실행, rs.next() 다음행에 값이 있으면 true, 없으면 false 리턴 후 있는 경우 그 행으로 이동
			while(rs.next()){
				
				//반복 할때마다 객체 생성
				MemberVo mv = new MemberVo();
				//옮겨담기
				mv.setMidx(rs.getInt("midx"));
				mv.setMembername(rs.getString("membername"));
				mv.setMemberphone(rs.getString("memberphone"));
				mv.setWriteday(rs.getDate("writeday"));
				//담은 mv를 alist에 추가
				alist.add(mv);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return alist;
	}
	//아이디와 패스워드가 같은 사람이 존재하면 해당하는 사람의 정보를 리턴
	public MemberVo memberLogin(String id, String pwd) {
		ResultSet rs = null;
		MemberVo mv = null;
		
		String sql = "select * from a_member where delyn='N' and memberid=? and memberpwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			//rs로 받아 id를 세션변수에 담을 것
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMembername(rs.getString("membername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return mv;
	}
}
