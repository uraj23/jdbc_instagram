package first_maven_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class DynamicInsertRecords {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");

			ps = con.prepareStatement("insert into emp values(?,?,?,?)");
			System.out.println("Enter id ");
			ps.setInt(1, sc.nextInt());
			System.out.println("Enter name");
			ps.setString(2, sc.next());
			System.out.println("Enter email");
			ps.setString(3, sc.next());
			System.out.println("Enter salry");
			ps.setDouble(4, sc.nextDouble());

			int update = ps.executeUpdate();
			System.out.println(update + " row affected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
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
