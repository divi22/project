package com.cap.service;

import java.util.List;

import com.cap.bean.Bank;
import com.cap.exception.BankException;



public interface BankService {
	public Bank addCustomer(Bank bk) throws BankException;
	public List<Bank> getCustomer() throws BankException;
	public Bank deposit(double amount,Bank bk) throws BankException;
	public Bank withdraw(double amount,Bank bk) throws BankException;
	
	public Bank login(Bank bk,int accountno,String passwrd) throws BankException;
	
	public double showbal(Bank bk) throws BankException;
	public Bank GetCustomer(int acc_no) throws BankException;
	public boolean transFund(double amount,Bank frm,Bank to) throws BankException;
	public List<String> printTransactions(Bank bk) throws BankException;
}
