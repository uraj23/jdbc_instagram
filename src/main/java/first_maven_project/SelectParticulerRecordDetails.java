package first_maven_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectParticulerRecordDetails {

	public static void main(String[] args) {   
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
			ps = con.prepareStatement("select * from emp where id=?");

			System.out.println("Enter id to fetch record");
			ps.setInt(1, sc.nextInt());

			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.print("[ id : " + rs.getInt(1) + ", ");
				System.out.print("name : " + rs.getString(2) + ", ");
				System.out.print("email : " + rs.getString(3) + ", ");
				System.out.println("sal : " + rs.getDouble(4) + "]");
			} else {
				System.out.println("Enter valied id ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
			sc.close();
		}
 	}
}
