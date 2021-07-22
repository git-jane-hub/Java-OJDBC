import java.sql.*;

public class P01OjbdConnection {
	public static void main(String[] args) {
		/* MySQL과 마찬가지로 Oracle SQL도 .jar파일을 제공함
		 * 먼저 .jar파일을 import해야 연결이 가능
		 * .jar 파일 위치
		 * C:\Program Files\Oracle SQL Developer\sqldeveloper\jdbc\lib - ojdbc8.jar
		 *
		 * 프로젝트에 임포트하면 연동가능
		 *  
		 */
		Connection con = null;
		// Class.forname 과 String url 을 다르게 작성하니 주의
		try {
			// Oracle SQL과 연동할 것을 나타냄
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 접속 url도 Oracle SQL에 맞춰 작성
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			System.out.println("실행완료");
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: " + e);
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
