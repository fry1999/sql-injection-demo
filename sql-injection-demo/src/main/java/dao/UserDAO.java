package dao;

import model.User;

public interface UserDAO {
	
	public void addUser(User u);
	
	public boolean checkUser(String username);
	
	public boolean login(String username, String password);
	
	public void updateUser(User u);
	
	public void updatePasswordUser(String username, String password, String newPassword);
	
	public User getUser(String username);

}
