package com.novabank.transactionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.novabank.dbutil.OracleConnection;
import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;

public class TransactionDaoImp implements TransactionDAO {

	@Override
	public Transaction getTransactionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMaxTransactionId() throws BusinessException{
		try (Connection connection = OracleConnection.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(transaction_id) FROM transaction_history");
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}
	
	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		List<Transaction> transactions = new ArrayList<>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "SELECT transaction_id, account_number, acting_party, credit, debit, transaction_date, NVL(transfer_id, -1)"
					+ "FROM transaction_history "
					+ "ORDER BY transaction_id";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				transactions.add(new Transaction(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getFloat(4),
						rs.getFloat(5),
						rs.getDate(6),
						rs.getInt(7)
						));
			}
			
			return transactions;			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByActingParty(String actingParty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransactionByTransferId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTransaction(Transaction transaction) throws BusinessException {
		// Set transaction.transferId to -1 if you want a null value
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "INSERT INTO transaction_history "
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, transaction.getTransactionId());
			ps.setInt(2, transaction.getAccountId());
			ps.setString(3, transaction.getActingParty());
			ps.setFloat(4, transaction.getCredit());
			ps.setFloat(5,  transaction.getDebit());
			ps.setDate(6, new java.sql.Date(transaction.getTransactionDate().getTime()));
			if (transaction.getTransferId() == -1) {
				ps.setInt(7, transaction.getTransferId());				
			} else {
				ps.setNull(7,  Types.INTEGER);
			}
			return ps.execute();			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}

}
