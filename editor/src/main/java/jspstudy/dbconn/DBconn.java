package jspstudy.dbconn;
import java.sql.Connection;
import java.sql.DriverManager;

//연결시키는 기능 쓰려면 객체 생성을 시켜야함
public class DBconn {
	//접속정보
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "system";
	String password = "1234";
		
	public Connection getConnection() {
		Connection conn = null;
		try {
			//오라클 jdbc 사용을 위한 코드
			//해당 패키지에 있는 클래스를 가져온다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
			//오라클 접속을 위한 주소, 아이디, 비밀번호
			//접속 정보를 활용해서 연결객체를 만든다.
			conn = java.sql.DriverManager.getConnection(url, user, password);
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
		
}
