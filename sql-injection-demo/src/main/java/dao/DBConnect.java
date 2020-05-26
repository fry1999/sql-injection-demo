package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static Connection getConnecttion() {
		Connection cons = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cons = DriverManager.getConnection(
					"jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_2b4b7a67e105418?sslmode = require", "b4bfae45df5bd3", "67b62b4f");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cons;
	}
	
	public static void main(String[] args) {
		System.out.println(getConnecttion());
	}

}
