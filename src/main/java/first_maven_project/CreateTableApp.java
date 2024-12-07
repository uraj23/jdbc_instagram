package first_maven_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableApp { 
	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "root");

			st = con.createStatement();
			boolean b = st.execute(
					"create table payment (id integer not null primary key,amount decimal(10,2) not null,status varchar(50))");

			if (!b) {
				int cnt = st.getUpdateCount();
				System.out.println(cnt + " row affected..");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
