import java.sql.*;

public class P02OjdbcSelect {
	public static void main(String[] args) {
		/* Oracle SQL과 연동해서 employees테이블의 모든 인원에 대한 정보를 콘솔창에 출력
		 * SELECT employee_id, first_name, hire_date, job_id, salary FROM employees;
		 */
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			con = DriverManager.getConnection(url, "hr", "hr");
			
			stmt = con.createStatement();
			String sql = "SELECT employee_id, first_name, hire_date, job_id, salary FROM employees";
			rs = stmt.executeQuery(sql);
			// 아래 내용을 한번만 작성하면 한 로우만 출력됨, 모든 내용을 출력하려면 while 구문을 사용해서 모든 직원이 출력될 때 까지 실행이 멈추지 않게 작성
			/* rs.next() 
			 * 1. 커서가 넘어가면서 다음 로우로 이동
			 * 2. true, false를 반환
			 * rs.next()를 while내부에 작성하면 커서를 넘기면서 true/false를 판단하기 때문에 while문 내부에만 작성해도 커서가 넘겨지는 효과가 있음
			 */
			 
			while(rs.next()) {
			System.out.println("아이디: " + rs.getInt(1) +
								", 이름: " + rs.getString(2) +
								", 입사일: " + rs.getDate(3) +
								", 직무명: " + rs.getString(4) +
								", 연봉: " + rs.getInt(5));
			}
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
