package first_maven_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertValues {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");

			// DDL -> create drop alter (tables)
			// DML ->Insert delete update
			// DQL -> select type query

			st = con.createStatement();

			boolean b =st.execute("Insert into emp values(103,'martin','martin.com',1022)");
			if(!b) {
				int cnt = st.getUpdateCount();
				System.out.println(cnt+" Rows affected ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
