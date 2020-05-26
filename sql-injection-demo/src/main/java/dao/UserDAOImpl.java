package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.Category;
import model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User u) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into user value(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setDate(4, u.getNgaysinh());
			ps.setString(5, u.getGioitinh());
			ps.setString(6, u.getEmail());
			ps.setString(7, u.getSdt());
			ps.setString(8, u.getDiachi());
			ps.setString(9, u.getRole());
			System.out.println(ps);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkUser(String username) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where username= ?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, username);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		UserDAOImpl dao = new UserDAOImpl();
		// dao.addUser(new User(0, "admin", "12345", "admin", "1"));
//		System.out.println(dao.checkUser("admin"));
		System.out.println(dao.login("admin", "12345"));
	}

	@Override
	public boolean login(String username, String password) {
		Connection con = DBConnect.getConnecttion();
		/*
		 * String sql = "select * from user where username= '"+username+"'" +
		 * " and password = '"+password+"';";
		 */

		String sql = "select * from user where username= ? and password= ?"; //
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (
			SQLException e) {
			e.printStackTrace();
		}

		/*
		 * try { Statement st = (Statement) con.createStatement();
		 * System.out.println(sql); System.out.println("Hehe"); ResultSet rs =
		 * st.executeQuery(sql);
		 * 
		 * if (rs.next()) { con.close(); return true; } } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

		return false;
	}

	@Override
	public void updateUser(User u) {
		Connection con = DBConnect.getConnecttion();
		String sql = "update user set user_id=?, password=?, ngaysinh=?, gioitinh=?, email=?, sdt=?, diachi=?, role=? where username=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getPassword());
			ps.setDate(3, u.getNgaysinh());
			ps.setString(4, u.getGioitinh());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getSdt());
			ps.setString(7, u.getDiachi());
			ps.setString(8, u.getRole());
			ps.setString(9, u.getUsername());
			System.out.println(ps);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updatePasswordUser(String username, String password, String newPassword) {
		Connection con = DBConnect.getConnecttion();
		String sql = "update user set password = '" + newPassword + "' where username = '" + username
				+ "' and password = '" + password + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String name) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where username='" + name + "'";
		User u = new User();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Date ngaysinh = rs.getDate("ngaysinh");
				String gioitinh = rs.getString("gioitinh");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				String diachi = rs.getString("diachi");
				String role = rs.getString("role");
				u = new User(user_id, username, password, ngaysinh, gioitinh, email, sdt, diachi, role);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

}
