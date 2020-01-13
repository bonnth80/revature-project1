package com.novabank.ui;

import org.apache.log4j.Logger;

import com.novabank.accountBO.AccountBoImp;
import com.novabank.exception.BusinessException;
import com.novabank.transferBO.TransferBoImp;

public class Menu {
	private static Logger log = Logger.getLogger(Menu.class);
	public static final String linebreak = "------------------------------------------------------------------------------------------------------------";
	
	public static void displayTitle() {
		System.out.println("--------------------------------------------- WELCOME TO: -------------------------------------------------");
		System.out.println("                  ___           ___                                  ___           ___           ___     ");
		System.out.println("      ___        /__/\\         /  /\\                  _____         /  /\\         /__/\\         /__/|    ");
		System.out.println("     /  /\\       \\  \\:\\       /  /:/_                /  /::\\       /  /::\\        \\  \\:\\       |  |:|    ");
		System.out.println("    /  /:/        \\__\\:\\     /  /:/ /\\              /  /:/\\:\\     /  /:/\\:\\        \\  \\:\\      |  |:|    ");
		System.out.println("   /  /:/     ___ /  /::\\   /  /:/ /:/_            /  /:/~/::\\   /  /:/~/::\\   _____\\__\\:\\   __|  |:|    ");
		System.out.println("  /  /::\\    /__/\\  /:/\\:\\ /__/:/ /:/ /\\          /__/:/ /:/\\:| /__/:/ /:/\\:\\ /__/::::::::\\ /__/\\_|:|____");
		System.out.println(" /__/:/\\:\\   \\  \\:\\/:/__\\/ \\  \\:\\/:/ /:/          \\  \\:\\/:/~/:/ \\  \\:\\/:/__\\/ \\  \\:\\~~\\~~\\/ \\  \\:\\/:::::/");
		System.out.println(" \\__\\/  \\:\\   \\  \\::/       \\  \\::/ /:/            \\  \\::/ /:/   \\  \\::/       \\  \\:\\  ~~~   \\  \\::/~~~~ ");
		System.out.println("      \\  \\:\\   \\  \\:\\        \\  \\:\\/:/              \\  \\:\\/:/     \\  \\:\\        \\  \\:\\        \\  \\:\\     ");
		System.out.println("       \\__\\/    \\  \\:\\        \\  \\::/                \\  \\::/       \\  \\:\\        \\  \\:\\        \\  \\:\\    ");
		System.out.println("                 \\__\\/         \\__\\/                  \\__\\/         \\__\\/         \\__\\/         \\__\\/ ");
		System.out.println(linebreak + "\n");
	}

	public static void displayEmployeeMenu() {
		try {
			log.info("\nPlease select from these available actions:");
			log.info("\t1. View pending account applications. (" + new AccountBoImp().getPendingApprovalCount() + ")");
			log.info("\t2. Retrieve customer account info.");
			log.info("\t3. Retrieve transaction log.");
			log.info("\t4. Register new user.");
			log.info("\t5. Sign out.");
			log.info("\t6. Exit application.");
			
		} catch (BusinessException e) {
			log.error(e.getMessage());
		}
	}
	
	public static void displayCustomerMenu(int userId) {
		try {
			log.info("\nPlease select from these available actions:");
			log.info("\t1. Apply for a new account.");
			log.info("\t2. View accounts list.");
			log.info("\t3. Make a withdrawal.");
			log.info("\t4. Make a deposit");
			log.info("\t5. Post Money Transfer.");
				log.info("\t6. View incumbent transfer requests. (" + 
					new TransferBoImp().getTransferCountByUserId(userId) + ")");
			log.info("\t7. Sign out.");
			log.info("\t8. Exit application.");
		} catch (BusinessException e) {
			log.error(e.getMessage());
		}
	}
	
	public static void displayPendingAccountsHeader() {
		String accountHeader = padStringRight("Account #", 16);
		String userNameHeader = padStringRight("User Name", 30);
		String startBalanceHeader = padStringRight("S. Balance", 20);
		String creationDateHeader = padStringRight("Creation Date", 22);
		String statusHeader = padStringRight("Status", 16);
		log.info(accountHeader + userNameHeader + startBalanceHeader + creationDateHeader + statusHeader);
		log.info(linebreak);
	}
	
	public static void displayUserActiveAccountsHeader() {
		String accountHeader = padStringRight("Account #", 25);
		String balanceHeader = padStringRight("Balance", 25);
		String creationDateHeader = padStringRight("Creation Date", 25);
		log.info(accountHeader + balanceHeader + creationDateHeader);
		log.info(linebreak);
	}

	public static void displayPendingTransfersHeader() {
		String transferHeader = padStringRight("Transfer Number", 20);
		String sourceHeader = padStringRight("Source Account", 25);
		String destinationHeader = padStringRight("Destination Account", 25);
		String amountHeader = padStringRight("Transfer Amount", 25);
		String dateHeader = padStringRight("Date posted", 25);
		log.info(transferHeader + sourceHeader + destinationHeader + amountHeader + dateHeader);
		log.info(linebreak);
	}
	
	public static void displayTransactionsHeader() {
		String transactionIdHeader = padStringRight("Transaction Id", 18);
		String accountIdHeader = padStringRight("Account #", 12);
		String actingPartyHeader = padStringRight("Acting Party", 20);
		String creditHeader = padStringRight("Credit", 12);
		String debitHeader = padStringRight("Debit", 12);
		String dateHeader = padStringRight("Date", 15);
		String transferId = padStringRight("Transfer Id", 15);
		log.info(transactionIdHeader + accountIdHeader + actingPartyHeader + creditHeader + debitHeader + dateHeader + transferId);
		log.info(linebreak);
	}
	
	public static String padStringRight(String str, int maxStringSize) {
		StringBuffer sb = new StringBuffer(str);
		if (sb.length() < maxStringSize) {
			while (sb.length() < maxStringSize) {
				sb.append(" ");
			}
		} else if (sb.length() > maxStringSize) {
			sb.replace(maxStringSize - 3, sb.length(), "...");
		}
		
		return sb.toString();
	}
	
}
