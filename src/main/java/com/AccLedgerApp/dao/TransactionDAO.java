package com.AccLedgerApp.dao;

import com.AccLedgerApp.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionDAO {

    void createTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByMonthToDate(String date);

    List<Transaction> getTransactionsByYearToDate(String date);

    List<Transaction> getTransactionsForPreviousMonth(Date date);

    List<Transaction> getTransactionsForPreviousYear(String date);

    List<Transaction> getPayments();

    List<Transaction> getDeposits();

}
