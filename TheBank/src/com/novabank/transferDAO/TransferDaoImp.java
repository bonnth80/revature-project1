package com.novabank.transferDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.novabank.dbutil.OracleConnection;
import com.novabank.exception.BusinessException;
import com.novabank.to.Transfer;

public class TransferDaoImp implements TransferDAO {

	@Override
	public Transfer getTransferById(int id) throws BusinessException {
		System.out.println("Getting transfer by id: " + id);
		try (Connection connection = OracleConnection.getConnection()) {
			String sql =  "SELECT transfer_id, amount, source_account, destination_account, status, request_Date, response_date "
					+ "FROM transfer_request "
					+ "WHERE transfer_id = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return new Transfer(
						rs.getInt(1),
						rs.getFloat(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getDate(6),
						rs.getDate(6)
						);
		} catch (ClassNotFoundException | SQLException e) {  
			throw new BusinessException("Internal error: " + e);
		}
	}

	@Override
	public int getMaxTransferId() throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(transfer_id) FROM transfer_request");
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}
	
	@Override
	public int getTransferCountByUserId(int userId) throws BusinessException {
		int count = 0;
		try (Connection connection = OracleConnection.getConnection()) {
			String sql =  "SELECT COUNT(*) "
					+ "FROM transfer_request tr "
					+ "INNER JOIN account a "
					+ "ON a.account_number = tr.destination_account "
					+ "WHERE a.user_id = ? AND tr.status = 0"
					+ "ORDER BY tr.transfer_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			count = result.getInt(1);
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	};

	@Override
	public List<Transfer> getTransfersByUserId(int userId) throws BusinessException {
		List<Transfer> transfers = new ArrayList<>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql =  "SELECT tr.transfer_id, tr.amount, tr.source_account, tr.destination_account, tr.status, tr.request_Date, tr.response_date "
					+ "FROM transfer_request tr "
					+ "INNER JOIN account a "
					+ "ON a.account_number = tr.destination_account "
					+ "WHERE a.user_id = ? "
					+ "ORDER BY tr.transfer_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				transfers.add(new Transfer(
						rs.getInt(1),
						rs.getFloat(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getDate(6),
						rs.getDate(6)
						));
			}
			
			return transfers;
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}

	@Override
	public List<Transfer> getTransfersBySourceAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByDestinationAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByRequestDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByApprovalDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewTransfer(Transfer transfer) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "INSERT INTO transfer_request "
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, transfer.getTransferId());
			ps.setFloat(2, transfer.getAmount());
			ps.setInt(3, transfer.getSource());
			ps.setInt(4, transfer.getDestination());
			ps.setInt(5, transfer.getStatus());
			ps.setDate(6,  new java.sql.Date(transfer.getRequestDate().getTime()));
			ps.setDate(7, null);
			
			return ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error: " + e);
		}
	}

	@Override
	public boolean updateTransferStatus(Transfer transfer, int status) throws BusinessException {
		boolean execute;
		System.out.println("updating Transfer status: " + status);
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "UPDATE transfer_request "
					+ "SET status = ?, response_date = ? "
					+ "WHERE transfer_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setDate(2, new java.sql.Date(new Date().getTime()));
			ps.setInt(3, transfer.getTransferId());
			execute = ps.execute();			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error: " + e);
		}
		
		if (status == 1 ) { // Only add a new set of debit / credit transactions if status is approved
		
			int maxTransactionId = 0;
		
			try (Connection connection = OracleConnection.getConnection()) {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT MAX(transaction_id) FROM transaction_history");
				rs.next();
				maxTransactionId = rs.getInt(1);
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal error: " + e);
			}
			
			try (Connection connection = OracleConnection.getConnection()) {
				String sql = "INSERT INTO transaction_history "
						+ "VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, maxTransactionId + 1);
				ps.setInt(2, transfer.getDestination());
				ps.setString(3, "The Bank");
				ps.setFloat(4, transfer.getAmount());
				ps.setFloat(5,  0.0F);
				ps.setDate(6, new java.sql.Date(new Date().getTime()));
				ps.setInt(7, transfer.getTransferId());
				ps.execute();			
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal error: " + e);
			}
			
			try (Connection connection = OracleConnection.getConnection()) {
				String sql = "INSERT INTO transaction_history "
						+ "VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, maxTransactionId + 2);
				ps.setInt(2, transfer.getSource());
				ps.setString(3, "The Bank");
				ps.setFloat(4, 0.0F);
				ps.setFloat(5,  transfer.getAmount());
				ps.setDate(6, new java.sql.Date(new Date().getTime()));
				ps.setInt(7, transfer.getTransferId());
				ps.execute();			
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal error: " + e);
			}
		}
		
		return execute;
	}
	
}
