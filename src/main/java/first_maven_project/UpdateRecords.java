package first_maven_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateRecords {
	public static void main(String[] args) {		
		Connection con=null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","root");
			 
			 st=con.createStatement();
			 boolean b = st.execute("update emp set sal=2000 where id =101");
			 if(!b) {
				 int cnt = st.getUpdateCount();
				 System.out.println(cnt+"Row affected ");
			 }
			 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
