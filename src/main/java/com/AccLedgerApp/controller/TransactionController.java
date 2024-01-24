package com.AccLedgerApp.controller;

import com.AccLedgerApp.dao.TransactionDAO;
import com.AccLedgerApp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @PostMapping("/create")
    public String createTransaction(@RequestBody Transaction transaction) {
        try {
            transactionDAO.createTransaction(transaction);
            return "Transaction created successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create transaction.";
        }
    }

    @GetMapping("/month-to-date/{date}")
    public List<Transaction> getTransactionsByMonthToDate(@PathVariable String date) {
        try {
            return transactionDAO.getTransactionsByMonthToDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error as needed
        }
    }

    @GetMapping("/year-to-date/{date}")
    public List<Transaction> getTransactionsByYearToDate(@PathVariable String date) {
        try {
            return transactionDAO.getTransactionsByYearToDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error as needed
        }
    }

    @GetMapping("/previous-month/{date}")
    public List<Transaction> getTransactionsForPreviousMonth(@PathVariable String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);

            return transactionDAO.getTransactionsForPreviousMonth(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle error as needed
        }
    }


    @GetMapping("/previous-year/{date}")
    public List<Transaction> getTransactionsForPreviousYear(@PathVariable String date) {
        try {
            return transactionDAO.getTransactionsForPreviousYear(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error as needed
        }
    }

    @GetMapping("/payments")
    public List<Transaction> getPayments() {
        return transactionDAO.getPayments();
    }

    @GetMapping("/deposits")
    public List<Transaction> getDeposits() {
        return transactionDAO.getDeposits();
    }

}

