package com.novabank.userDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.novabank.dbutil.OracleConnection;
import com.novabank.exception.BusinessException;
import com.novabank.to.User;

public class UserDaoImp implements UserDAO {
	@Override
	public User getUserByCredentials(String username, String password) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()){
			String sql = "SELECT user_id, first_name, last_name, archetype, ssn, "
						+ " home_phone, mobile_phone, email, street_address, "
						+ "city, state, country, zip, username, password, date_created"
						+ " FROM bank_user"
						+ " WHERE username = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2,  password);
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next()) {
				User user = new User(
						result.getInt("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getInt("archetype"),
						result.getString("ssn"),
						result.getString("home_phone"),
						result.getString("mobile_phone"),
						result.getString("email"),
						result.getString("street_address"),
						result.getString("city"),
						result.getString("state"),
						result.getString("country"),
						result.getString("zip"),
						username,
						password,
						result.getDate("date_created"));
				return user;
			} else {
				throw new BusinessException("Sign-in failed: User / password combination not found."
						+ "\nIf you believe this has occurred by mistake, please contact an employee.\n\n");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... \n" + e);
		}
	}
	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMaxIdUsed() throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(user_id) FROM bank_user");
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... \n" + e);
		}
	}

	@Override
	public List<User> getUsersByFullName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByArchetype(int archetype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserBySSN(String ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByHomePhone(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByMobilePhone(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByCityState(String city, String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByState(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByZip(String zip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByDateCreated() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean addNewUser(User user) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "INSERT INTO bank_user "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, user.getUserId());	// id
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setInt(4, user.getArchetype());
			ps.setString(5,  user.getSsn());
			ps.setString(6,  user.getHomePhone());
			ps.setString(7, user.getMobilePhone());
			ps.setString(8,  user.getEmail());
			ps.setString(9, user.getStreetAddress());
			ps.setString(10, user.getCity());
			ps.setString(11,  user.getState());
			ps.setString(12,  user.getCountry());
			ps.setString(13, user.getZip());
			ps.setString(14, user.getUserName());
			ps.setString(15, user.getPassword());
			Date today = user.getDateCreated();
			ps.setDate(16,  new java.sql.Date(today.getTime()));
			
			boolean x = ps.execute();

			return x;
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error: " + e);
		}
	}

}
