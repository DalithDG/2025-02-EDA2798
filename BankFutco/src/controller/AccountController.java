package controller;

import model.Account;
import services.AccountService;
import services.IAccountService;

import java.util.Scanner;

public class AccountController {

    private Scanner scanner;

    public void menu() {
        IAccountService accountService = new AccountService();
        scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n===== ACCOUNT MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Find All Accounts");
            System.out.println("4. Delete Account");
            System.out.println("5. Update Account");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> createAccount(accountService);
                case 2 -> viewAccount(accountService);
                case 3 -> findAllAccounts(accountService);
                case 4 -> deleteAccount(accountService);
                case 5 -> updateAccount(accountService);
                case 0 -> System.out.println("Exiting account menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }
    private void createAccount(IAccountService accountService) {
        System.out.println("\n--- Create New Account ---");

        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Account Holder Name: ");
        String holderName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Account Type: ");
        String accountType = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        Account account = new Account(accountNumber, holderName, email, phoneNumber,  accountType, address);
        accountService.save(account);
        System.out.println("Account created successfully: " + account);
    }

    private void viewAccount(IAccountService accountService) {
        System.out.print("Enter Account Number to view: ");
        String accountNumber = scanner.nextLine();
        accountService.findById(accountNumber).ifPresentOrElse(
                acc -> System.out.println("Account Found: " + acc),
                () -> System.out.println("Account number not found.")
        );
    }

    private void findAllAccounts(IAccountService accountService) {
        System.out.println("\n--- All Accounts ---");
        accountService.findAll().forEach(System.out::println);
    }

    private void deleteAccount(IAccountService accountService) {
        System.out.print("Enter Account Number to delete: ");
        String accountNumber = scanner.nextLine();
        boolean deleted = accountService.deleteById(accountNumber);
        if (deleted) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account number not found.");
        }
    }

    private void updateAccount(IAccountService accountService) {
        System.out.print("Enter Account Number to update: ");
        String accountNumber = scanner.nextLine();
        accountService.findById(accountNumber).ifPresentOrElse(acc -> {
            System.out.println("Updating Account: " + acc);

            System.out.print("New Account Holder Name (current: " + acc.getName() + "): ");
            String holderName = scanner.nextLine();
            if (!holderName.isBlank()) acc.setName(holderName);

            System.out.print("New Email (current: " + acc.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) acc.setEmail(email);

            System.out.print("New Phone Number (current: " +  acc.getMobileNumber() + "): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.isBlank()) acc.setMobileNumber(phoneNumber);

            System.out.print("New Account Type (current: " + acc.getAccountType() + "): ");
            String accountType = scanner.nextLine();
            if (!accountType.isBlank()) acc.setAccountType(accountType);

            System.out.print("New Address (current: " + acc.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isBlank()) acc.setAddress(address);

            accountService.save(acc);
            System.out.println("Account updated successfully: " + acc);
        }, () -> System.out.println("Account number not found."));
    }  
}

