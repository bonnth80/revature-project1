package com.novabank.userBO;

import java.util.List;

import com.novabank.exception.BusinessException;
import com.novabank.to.User;
import com.novabank.userDAO.UserDAO;
import com.novabank.userDAO.UserDaoImp;

public class UserBoImp implements UserBO {
	@Override
	public User getUserByCredentials(String username, String password) throws BusinessException {
		UserDAO dao = new UserDaoImp();

		
		return dao.getUserByCredentials(username, password);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMaxIdUsed() throws BusinessException {
		UserDAO ud = new UserDaoImp();
		return ud.getMaxIdUsed();
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
		UserDAO ud = new UserDaoImp();
		boolean x = ud.addNewUser(user);
		return x;
	}

}
