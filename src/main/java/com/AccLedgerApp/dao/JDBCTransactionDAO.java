package com.AccLedgerApp.dao;

import com.AccLedgerApp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class JDBCTransactionDAO implements TransactionDAO {

    private DataSource dataSource;

    @Autowired
    public JDBCTransactionDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (transaction_date, time, description, vendor, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(transaction.getDate().getTime()));
            preparedStatement.setString(2, transaction.getTime());
            preparedStatement.setString(3, transaction.getDescription());
            preparedStatement.setString(4, transaction.getVendor());
            preparedStatement.setDouble(5, transaction.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Transaction transaction = mapResultSetToTransaction(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByMonthToDate(String date) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE MONTH(transaction_date) = MONTH(?) AND YEAR(transaction_date) = YEAR(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = mapResultSetToTransaction(resultSet);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByYearToDate(String date) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE YEAR(transaction_date) = YEAR(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = mapResultSetToTransaction(resultSet);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsForPreviousMonth(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        // Set the end date to the last day of the previous month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfPreviousMonth = calendar.getTime();

        // Set the start date to the first day of the previous month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfPreviousMonth = calendar.getTime();

        String query = "SELECT * FROM transactions WHERE transaction_date BETWEEN ? AND ?";

        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(firstDayOfPreviousMonth.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(lastDayOfPreviousMonth.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = mapResultSetToTransaction(resultSet);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsForPreviousYear(String date) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE YEAR(transaction_date) = YEAR(?) - 1";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = mapResultSetToTransaction(resultSet);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getPayments() {
        String query = "SELECT * FROM transactions WHERE amount < 0";
        return executeQuery(query);
    }

    @Override
    public List<Transaction> getDeposits() {
        String query = "SELECT * FROM transactions WHERE amount > 0";
        return executeQuery(query);
    }

    private List<Transaction> executeQuery(String query) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Transaction transaction = mapResultSetToTransaction(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    private Transaction mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getLong("id"));
        transaction.setDate(resultSet.getDate("transaction_date"));
        transaction.setTime(resultSet.getString("time"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setVendor(resultSet.getString("vendor"));
        transaction.setAmount(resultSet.getDouble("amount"));
        return transaction;
    }
}
