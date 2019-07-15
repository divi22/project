package com.cap.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cap.bean.Bank;
import com.cap.dao.BankDaoImp;
import com.cap.exception.BankException;
import com.cap.service.BankService;
import com.cap.service.BankService;
import com.cap.service.BankServiceImp;

public class Main {
	

	static int acc_no=987632; //input static value to account number
	public static Bank acceptDetails() {
		Bank bank=new Bank();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name:");
		String name=sc.nextLine();
		bank.setName(name);
		System.out.println("Enter mobile number:");
		long mob=sc.nextLong();
		bank.setMobile_number(mob);
		System.out.println("Enter address:");
		String address=sc.next();
		bank.setAddress(address);
		System.out.println("Enter Username:");
		String uname=sc.next();
		bank.setUsername(uname);
		System.out.println("Enter Password:");
		String passwrd=sc.next();
		bank.setPasswrd(passwrd);
		System.out.println("Re enter your passeword:");
		String confirmpass=sc.next();
		bank.setConfirmpass(confirmpass);
		
		bank.setAcc_no(++acc_no); //account number is incremented
		
		
		return bank;
		
	}
	public static void display(List<Bank> banklist)
	{
		for(Bank b:banklist)
		{
			System.out.println(Bank.getAcc_no());
			System.out.println(b.getName());
			System.out.println(b.getMobile_number());
			System.out.println(b.getPasswrd());
		}
	}

	public static void main(String[] args) throws BankException{
		BankService service=new BankServiceImp();
		BankDaoImp dao=new BankDaoImp();
		Bank bk=new Bank();
		do {
			System.out.println("-----------------------------------------");
			System.out.println("1.Add Customer");
			System.out.println("2. Show Balance");
			System.out.println("3. Deposit Amount");
			System.out.println("4.Withdraw Amount");
			System.out.println("5.Fund Transfer");
			System.out.println("6.Print Transactions");
			System.out.println("7.Exit");
			System.out.println("-----------------------------------------");
			
			System.out.println("Select option");
			Scanner sc=new Scanner(System.in);
			
			int option=sc.nextInt();
			switch(option)
			{
			case 1:
				try {
					bk=acceptDetails();
					service.addCustomer(bk);
					System.out.println("Customer is added");
					System.out.println("Account number is"+bk.getAcc_no());
				}
				catch(BankException ex) {
					System.out.println(ex.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter account number:");
				
				try {
					int acc_no=sc.nextInt();
					bk=service.GetCustomer(acc_no);
					{
						System.out.println("Balance is Rs."+service.showbal(bk));
					}
					
				}
				catch(NullPointerException e) {
					System.out.println("Invalid account");
					
				}
				catch(BankException ex) {
					System.out.println(ex.getMessage());
					
				}
				catch(InputMismatchException eex) {
					System.out.println("Invalid account");
					
				}
				break;
			case 3:
				try {
				System.out.println("Enter account number:");
				int acc_no=sc.nextInt();
				System.out.println("Enter amount to deposit:");
				double amount=sc.nextDouble();
				bk=service.GetCustomer(acc_no);
				service.deposit(amount, bk);
				System.out.println("Deposited"+amount+"into the account successfully");
				System.out.println("The balance is"+service.showbal(bk));
			}
			catch(BankException ex)
			{
				System.out.println(ex.getMessage());
			}
				catch(InputMismatchException eex) {
					System.out.println("Invalid account");
					
				}
				catch(NullPointerException e) {
					System.out.println("Invalid account");
					
				}
				break;
			case 4:
				System.out.println("Enter account number:");
				try {
					int wd=sc.nextInt();
					System.out.println("Enter amount to withdraw:");
					double ps=sc.nextDouble();
					bk=service.GetCustomer(wd);
					service.withdraw(ps, bk);
					System.out.println("The amount"+ps+"is withdrawn successfully");
					System.out.println("Balance is"+service.showbal(bk));
				}
				catch(BankException ex) {
					System.out.println(ex.getMessage());
				}
				catch(NullPointerException e) {
					System.out.println("Invalid account");
					
				}
				catch(InputMismatchException eex) {
					System.out.println("Invalid account");
					
				}
				break;
			
			case 5:
				try {
					System.out.println("Enter account number of sender:");
					int as=sc.nextInt();
					System.out.println("Enter amount to transfer:");
					double a=sc.nextDouble();
					System.out.println("Enter account number of receiver:");
					int ar=sc.nextInt();
					bk=service.GetCustomer(as);
					Bank bk1=service.GetCustomer(ar);
					if(service.transFund(a, bk, bk1))
					{
						bk.setTransaction("Transferred Rs."+a+"from the account to"+bk1.getAcc_no());
						System.out.println("Transfer Successful");
						System.out.println("Balance="+service.showbal(bk));
					}
					
				}
					catch(BankException ex) {
						System.out.println(ex.getMessage());
					}
				catch(InputMismatchException eex) {
					System.out.println("Invalid account");
					
				}
				catch(NullPointerException e) {
					System.out.println("Invalid account");
					
				}
				
				break;
			case 6:
				try {
					System.out.println("Enter account number:");
					int acc_no=sc.nextInt();
					bk=service.GetCustomer(acc_no);
					List<String> transaction=service.printTransactions(bk);
					for(String str:transaction)
					{
						System.out.println(str+"\n");
					}
				}
				catch(BankException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(NullPointerException e) {
					System.out.println("Invalid account");
					
				}
				catch(InputMismatchException eex) {
					System.out.println("Invalid account");
					
				}
				break;
			case 7:
			{
				System.out.println(option);
			}
			break;
			default:
			{
				System.out.println("Invalid Option");
			}
					
					
				}
		}
		while(true);

	}

}
