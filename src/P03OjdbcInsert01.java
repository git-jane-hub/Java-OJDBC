import java.sql.*;

public class P03OjdbcInsert01 {
	public static void main(String[] args) {
		Connection con = null;
		/* Statement를 대신할 수 있는 PreparedStatement 선언
		 * 기존 Statement는 SQL 구문과 자바 구문의 경계가 불분명함
		 * 자바 코드와 SQL 구문이 섞여 문장을 알아보기가 힘듦
		 * PreparedStatement 는 SQL 구문만 먼저 작성하고 
		 * 필요한 부분에 자바 변수를 대입하는 방법으로 작성하여 작성이 용이하고 가독성에 좋음, 표준처럼 사용
		 */
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			con = DriverManager.getConnection(url, "mytest", "mytest");
			/* 1. PreparedStatement 구문은 쿼리문을 먼저 작성
			 * 변수를 삽입할 부분을 (?)물음표로 작성
			 * 문자열에도 홑따옴표를 작성하지 않고 물음표를 작성
			 * 기존에 쿼리문 사이에 자바 코드를 넣었다면,
			 * 쿼리문을 완전하게 작성할 필요없이 물음표를 작성
			 */
			String sql = "INSERT INTO ojdbcTest (num, str) VALUES (?, ?)";
			/* 2. 작성한 쿼리문(sql)의 물음표 자리에 적용할 자바 변수를 작성
			 * pstmt 변수를 생성하고 set 자료형 (물음표 자리 번호, 삽입할 변수)를 이용해 물음표 작성된 자리를 채워줌
			 */
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 100);				// 첫번째 물음표 자리에 num을 100으로 입력
			pstmt.setString(2, "test");			// 두번째 물음표 자리에 str을 test로 입력
			pstmt.setInt(1, 200);
			pstmt.setString(2, "test2");
			// 3. 입력이 끝난 쿼리문을 실행
			pstmt.executeUpdate();
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
