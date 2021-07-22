import java.sql.*;
import java.util.Scanner;
public class P05OjdbcUpdate {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement stmt = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("변경할 행번호를 입력해주세요.");
		int num = scan.nextInt();
		System.out.println("변경될 문자를 입력해주세요.");
		String str = scan.next();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			con = DriverManager.getConnection(url, "mytest", "mytest");
			String sql = "UPDATE ojdbcTest SET str = ? WHERE num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setInt(2, num);
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
