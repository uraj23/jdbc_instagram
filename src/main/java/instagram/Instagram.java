package instagram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//JDBC → Servlets → JSP → MVC → Filters/Listeners → Hibernate → Spring → RESTful Web Services

public class Instagram {
	static Scanner sc = null;
	static Connection con = null;
	static boolean flag = false;
	static {
		try {
			flag = true;
			sc = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram", "root", "root");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// createAccount();
//		deletreRecord();
		// viewAllRecords();
		// deleteAcbyUsernameAndPassword();
		// viewAllAcByName();
		// updateNameByUsernameAndPassword();
//		updateEmailByUsernameAndPassword();
		// updatePhoneNoByUsernameAndPassword();
		// exit();
		do {
			System.out.println("1.<<CREATE ACCOUNT>>");
			System.out.println("2.<<DELETE ACCOUNT>>");
			System.out.println("3.<<UPDATE NAME BY USERNAME AND PASSWORD>>");
			System.out.println("4.<<UPDATE EMAIL BY USERNAME AND PASSWORD>>");
			System.out.println("5.<<UPDATE PHONE0-NO BY USERNAME AND PASSWORD>>");
			System.out.println("6.<<VIEW ALL ACCOUNTS>>");
			System.out.println("7.<<VIEW ALL ACCOUNTS BY NAME>>");
			System.out.println("8.<<DELETE ACCOUNT BY USERNAME AND PASSWORD>>");
			System.out.println("9.<<EXIT>>");
			System.out.println("CHOOSE ANY OPTION");
			int choise = sc.nextInt();
			switch (choise) {
			case 1:
				createAccount();
				break;
			case 2:
				deleteRecordById();
				break;
			case 3:
				updateNameByUsernameAndPassword();
				break;
			case 4:
				updateEmailByUsernameAndPassword();
				break;
			case 5:
				updatePhoneNoByUsernameAndPassword();
				break;
			case 6:
				viewAllRecords();
				break;
			case 7:
				viewAllAcByName();
				break;
			case 8:
				deleteAcbyUsernameAndPassword();
				break;
			case 9:
				exit();
				break;
			default:
				System.err.println("Invalied option you choise");
			}

		} while (flag);
		System.out.println("Thanks for visiting ");

	}

	public static void exit() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sc.close();
		flag = false;

	}

	public static void createAccount() {
		System.out.println("Enter id ");
		int id = sc.nextInt();
		PreparedStatement ps = null;
		if (!check(id)) {

			try {
				ps = con.prepareStatement("insert into user value(?,?,?,?,?,?)");
				ps.setInt(1, id);
				System.out.println("Enter name");
				ps.setString(2, sc.next());
				System.out.println("Enter email id");
				ps.setString(3, sc.next());
				System.out.println("Enter username");
				ps.setString(4, sc.next());
				System.out.println("Enter phone no");
				ps.setLong(5, sc.nextLong());
				System.out.println("Enter password");
				ps.setString(6, sc.next());
				int update = ps.executeUpdate();
				if (update != 0) {
					System.out.println("Query excute (record inserted into the table)");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			System.out.println("Already id exists ...");
		}

	}

	public static boolean check(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean next = false;
		try {
			ps = con.prepareStatement("select * from user where id=?");

			ps.setInt(1, id);
			rs = ps.executeQuery();
			next = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {

				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return next;
	}

	public static void deleteRecordById() {
		System.out.println("Enter id to delete");
		int id = sc.nextInt();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (check(id)) {
			try {
				ps = con.prepareStatement("delete from user where id = ?");
				ps.setInt(1, id);
				int update = ps.executeUpdate();
				if (update != 0) {
					System.out.println("Record deleted sucessfully...");
				}

			} catch (SQLException e) {
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
			}

		} else {
			System.out.println("you entered id is not exist Table");
		}
	}

	public static void deleteRecordByUsernameAndPassword() {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("delete from user where username =? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			int update = ps.executeUpdate();
			if (update != 0) {
				System.out.println("RECORD DELETED SUCCESFULLY....");
			} else {
				System.err.println("<<<<RECORD NOT EXIST>>>>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void viewAllRecords() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("[id : " + rs.getInt(1) + ","+"	"+"Name : " + rs.getString(2) + ","+"	"+"email : " + rs.getString(3) + ","+"	"+"username :" + rs.getString(4) + ","+"	"+"phone-no :" + rs.getLong(5) + ","+"	"+"password :" + rs.getString(6) + "]");
//				System.out.print("Name : " + rs.getString(2) + ","+"	");
//				System.out.print("email : " + rs.getString(3) + ","+"	");
//				System.out.print("username :" + rs.getString(4) + ","+"	");
//				System.out.print("phone-no :" + rs.getLong(5) + ","+"	");
//				System.out.println("password :" + rs.getString(6) + "]");
			}
		} catch (SQLException e) {
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
		}
	}

	public static void viewAllAcByName() {
		System.out.println("Enter name to display records");
		String name = sc.next();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from user where name = ?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("[id : " + rs.getInt(1) + ", ");
				System.out.print("Name : " + rs.getString(2) + ", ");
				System.out.print("email : " + rs.getString(3) + ", ");
				System.out.print("username : " + rs.getString(4) + ", ");
				System.out.print("phone-no : " + rs.getLong(5) + ", ");
				System.out.println("password : " + rs.getString(6) + "]");
			}
		} catch (SQLException e) {
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
		}

	}

	public static void viewAllAcByUsername() {
		System.out.println("Enter username to display records");
		String username = sc.next();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("select * fro user where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("[id : " + rs.getInt(1) + ", ");
				System.out.print("Name : " + rs.getString(2) + ", ");
				System.out.print("email : " + rs.getString(3) + ", ");
				System.out.print("username : " + rs.getString(4) + ", ");
				System.out.print("phone-no : " + rs.getLong(5) + ", ");
				System.out.println("password : " + rs.getString(6) + "]");
			}
		} catch (SQLException e) {
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
		}
	}

	public static void deleteAcbyUsernameAndPassword() {
		System.out.println("Enter username to delete record");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("delete from user where username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			int update = ps.executeUpdate();
			if (update != 0) {
				System.out.println("Sucessfully deleted record");
			} else {
				System.out.println("NOT MATCHING RECORDS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void updateNameByUsernameAndPassword() {
		System.out.println("Enter username ");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("update user set name =?  where username=? and password =?");
			System.out.println("Enter new name to update");
			String name = sc.next();
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			int update = ps.executeUpdate();
			if (update != 0) {
				System.out.println("record updated succesfully..");
			} else {
				System.out.println("record not exist...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void updateEmailByUsernameAndPassword() {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update user set email=? where username=? AND password=?");
			System.out.println("Enter new email to update");
			ps.setString(1, sc.next());
			ps.setString(2, username);
			ps.setString(3, password);
			int update = ps.executeUpdate();
			if (update != 0) {
				System.out.println("RECORD SUCCESSFULLY UPDATED");
			} else {
				System.out.println("RECORD NOT EXIST..");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void updatePhoneNoByUsernameAndPassword() {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("update user set phone=? where username =? AND password =?");
			System.out.println("Enter new phone no to update ");
			long phone = sc.nextLong();
			ps.setLong(1, phone);
			ps.setString(2, username);
			ps.setString(3, password);
			int update = ps.executeUpdate();
			if (update != 0) {
				System.out.println("RECORD UPDATED SUCESSFULLY..");
			} else {
				System.out.println("RECORD NOT EXISTED..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
