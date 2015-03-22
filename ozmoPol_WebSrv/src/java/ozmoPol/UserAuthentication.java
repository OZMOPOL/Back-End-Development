import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginAuthentication {

	static Connection connection;
	static Statement stmt;

	public void LoginAuthenticator(){

		String databaseUserStatus = "";
		String activeStatus = "ACTIVE";
		String deactiveStatus = "DEACTIVE";

		// Check Username and Password

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username: ");
		String name = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();

		// Create SQL Query
		try {
			stmt = connection.createStatement();
			String queryUserSearch = "SELECT * FROM Users WHERE user_name='" +name+ "' && user_pass='" +password+ "'";
			ResultSet rs_userSearch = stmt.executeQuery(queryUserSearch);

			//Check query works and find ACTIVE account
			if(rs_userSearch.next()) {
				databaseUserStatus = rs_userSearch.getString("user_status");
				if(databaseUserStatus.equals(activeStatus)){
					System.out.println("Successful Login!\n----");
				}
				else if(databaseUserStatus.equals(deactiveStatus)){
					System.out.println("This account is DEACTIVE do you want to ACTIVE it?\n----");
				}	
			}else{
				System.out.println("Incorrect Username or Password\n----");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}