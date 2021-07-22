import java.sql.*;
import java.util.Scanner;
public class P06OjdbcDelete {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement stmt = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("num에 들어갈 숫자를 입력해주세요.");
		int num = scan.nextInt();
		System.out.println("str에 들어갈 문자를 입력해주세요.");
		String str = scan.next();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			con = DriverManager.getConnection(url, "mytest", "mytest");
			String sql = "INSERT INTO ojdbcTest (num, str) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, str);
			pstmt.executeUpdate();
			System.out.println("입력이 완료되었습니다.");
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
