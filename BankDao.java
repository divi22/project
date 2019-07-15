package com.cap.dao;

import com.cap.bean.Bank;
import com.cap.exception.*;


import java.util.List;

public interface BankDao {
	
	
	public Bank AddCustomer(Bank bk) throws BankException;
	public List<Bank> GetCustomer() throws BankException;
	
	public Bank Deposit(double amount,Bank bk) throws BankException;
	public Bank Withdraw(double amount,Bank bk) throws BankException;
	
	public Bank getCustomer(int acc_no) throws BankException;
	public double showBalance(Bank bk)throws BankException;
	
	public boolean transFund(double amount,Bank frm,Bank to) throws BankException;
	public List<String> printTransactions(Bank bk)throws BankException;
	
	
}
