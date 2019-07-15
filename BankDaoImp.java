package com.cap.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cap.bean.Bank;
import com.cap.exception.BankException;


public class BankDaoImp  implements BankDao{
	Bank bk=new Bank();
	private static Map<Integer,Bank> banks=new HashMap<>();
	
	
   //method to add customer
	@Override
	public Bank AddCustomer(Bank bk) throws BankException {
		banks.put(bk.getAcc_no(), bk);
		return bk;
	}
     
	
	@Override
	public List<Bank> GetCustomer() throws BankException {
		Collection<Bank>list=banks.values();
		List<Bank>banklist=new ArrayList<>(list);
		return banklist;
	}

	@Override
	public Bank Deposit(double amount, Bank bk) throws BankException {
		double bal=bk.getAmount();
		bk.setAmount(bal+amount);
		bk.setTransaction("The amount deposited is"+amount);
		return bk;
	}

	@Override
	public Bank Withdraw(double amount, Bank bk) throws BankException {
		double bal=bk.getAmount();
		if(bal>=amount) {
			bk.setAmount(bal-amount);
			bk.setTransaction("The amount withdrawn is"+amount);
		return bk;
		}
		else {
			throw new BankException("Not enough amount");
		}
	}

	public Bank getCustomer(int acc_no) throws BankException {
	
		return banks.get(acc_no);
	}

	@Override
	public double showBalance(Bank bk) throws BankException {
		
		return bk.getAmount();
	}

	@Override
	public boolean transFund(double amount, Bank frm, Bank to) throws BankException {
		try {
			BankDao dao=new BankDaoImp();
			dao.Deposit(amount, to);
			dao.Withdraw(amount, frm);
			bk.setTransaction("The amount withdrawn is"+amount);
			bk.setTransaction("The amount deposited is"+amount);
			
			return true;
		}
	
		catch(BankException be) {
			throw new BankException("Fund transfer failed.");
		}
		
		
	
	
		
	}
	

	@Override
	public List<String> printTransactions(Bank bk) throws BankException {
		
		return bk.getTransactions();
	}

	


}
