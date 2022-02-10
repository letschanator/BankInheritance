import java.util.Scanner;

public class Banker {
    public static void main(String args[] ){
        Scanner src = new Scanner(System.in);
        Boolean done = false;
        while (!done){ // loops untill the banker is done making accounts
            System.out.println("What type of account would you like to make?");
            System.out.print("1 for an individual account and 2 for a company account:");
            int type = src.nextInt(); //stores the account type to be converted later
            if(type != 1 && type != 2){
                System.out.println("should either be a 1 or a 2, try again");
                break;
            }
            System.out.print("what is the starting balance:");
            double balance = src.nextDouble(); //saves balance to be used later
            System.out.print("what is the account number:");
            int accountNumber = src.nextInt();  //saves account number to be used later
            System.out.print("What is the name of the account holder:");
            String accountHolder = src.next();  // saves account holders name to be used later
            int accountType;
            String indOrCom;
            if (type == 1){ // depending in weather it's individual or company affects what type of accounts they can make
                indOrCom = "individual"; // converts the type into the proper format
                System.out.println("would you like to make a checking, loan, or savings account?");
                System.out.print("1 for checking, 2 for loan, 3 for savings:");
                accountType = src.nextInt(); // keeps weather it's a checking, loan, or savings account for later use
                if (accountType != 1 && accountType != 2 && accountType !=3){
                    System.out.println("should either be a 1, 2, or 3, try again");
                    break;
                }

            }else{
                indOrCom = "company"; // converts type into proper format
                System.out.println("would you like to make a checking or loan account?");
                System.out.print("1 for checking, 2 for loan:");
                accountType = src.nextInt();  //keeps weather it's a checking or loan account for later use
                if (accountType != 1 && accountType != 2){
                    System.out.println("should either be a 1 or a 2, try again");
                    break;
                }
            }
            boolean accDone = false;
            if (accountType == 1){ // need to split off based on account type because each account type does different things
                System.out.print("what is the overdraft limit:");
                double limit = src.nextDouble();
                CheckingAccount acc = new CheckingAccount(balance,accountNumber,accountHolder,limit,indOrCom); //makes the account using all information gathered so far
                while (!accDone){ // loops until the banker is done with this account
                    System.out.println("What would you like to do with this account?");
                    System.out.print("1 for withdrawal, 2 for deposit, 3 for nothing:");
                    int doNext = src.nextInt();  //stores he number of what they want to do next
                    if (doNext != 1 && doNext != 2 && doNext != 3){
                        System.out.println("should have been either a 1,2, or 3 finishing account");
                        break;
                    }if (doNext == 1){
                        System.out.print("How much would you like to withdrawal:");
                        double amount = src.nextDouble();
                        acc.withdrawal(amount); //withdrawals from the account the corresponding amount
                    }if (doNext == 2){
                        System.out.print("How much would you like to deposit:");
                        double amount = src.nextDouble();
                        acc.deposit(amount); //deposits the corresponding amount into the account
                    }
                    System.out.println("current amount in account: $" + Math.round(acc.getBalance()*100.0)/100.0);  // rounds the balance to 2 decimal places
                    System.out.println("would you like to continue on this account?");
                    System.out.print("1 for yes, 2 for no:");
                    int input = src.nextInt();
                    if (input != 1){ // if their input isn't 1, they don't want to continue on this account
                        accDone = true;
                    }
                }
            }if (accountType == 2){ //does a similar task but for loan accounts and their specifications
                System.out.print("what is the interest rate:");
                double interest = src.nextDouble();
                LoanAccount acc = new LoanAccount(balance,accountNumber,accountHolder,interest,indOrCom);
                while (!accDone){
                    System.out.println("What would you like to do with this account?");
                    System.out.print("1 for withdrawal, 2 for deposit against loan, 3 for apply interest, 4 for nothing:");
                    int doNext = src.nextInt();
                    if (doNext != 1 && doNext != 2 && doNext != 3 && doNext != 4){
                        System.out.println("should have been either a 1,2, 3, or 4 finishing account");
                        break;
                    }if (doNext == 1){
                        System.out.print("How much would you like to withdrawal:");
                        double amount = src.nextDouble();
                        acc.withdrawal(amount);
                    }if (doNext == 2){
                        System.out.print("How much would you like to deposit against the loan:");
                        double amount = src.nextDouble();
                        acc.deposit(amount);
                    }if (doNext == 3){
                        acc.chargeIntrest();
                    }
                    System.out.println("current amount in account: $" + Math.round(acc.getBalance()*100.0)/100.0);
                    System.out.println("would you like to continue on this account?");
                    System.out.print("1 for yes, 2 for no:");
                    int input = src.nextInt();
                    if (input != 1){
                        accDone = true;
                    }
                }
            }if (accountType == 3){ //does a similar task but for savings accounts and their specifications
                System.out.print("what is the interest rate:");
                double interest = src.nextDouble();
                SavingsAccount acc = new SavingsAccount(balance,accountNumber,accountHolder,interest);
                while (!accDone){
                    System.out.println("What would you like to do with this account?");
                    System.out.print("1 for withdrawal, 2 for deposit, 3 for apply interest, 4 for nothing:");
                    int doNext = src.nextInt();
                    if (doNext != 1 && doNext != 2 && doNext != 3 && doNext != 4){
                        System.out.println("should have been either a 1,2, 3, or 4 finishing account");
                        break;
                    }
                    if (doNext == 1){
                        System.out.print("How much would you like to withdrawal:");
                        double amount = src.nextDouble();
                        acc.withdrawal(amount);
                    }
                    if (doNext == 2){
                        System.out.print("How much would you like to deposit:");
                        double amount = src.nextDouble();
                        acc.deposit(amount);
                    }
                    if (doNext == 3){
                        acc.applyInterest();
                    }
                    System.out.println("current amount in account: $" + Math.round(acc.getBalance()*100.0)/100.0);
                    System.out.println("would you like to continue on this account?");
                    System.out.print("1 for yes, 2 for no:");
                    int input = src.nextInt();
                    if (input != 1){
                        accDone = true;
                    }
                }

            }
            System.out.println("Currently there are "); // prints off the current number of each type of account
            System.out.println(CheckingAccount.getNumOfCheckingAccounts() + " checking account(s)");
            System.out.println(LoanAccount.getNumOfLoanAccounts() + " loan account(s)");
            System.out.println(SavingsAccount.getNumOfSavingsAccounts() + " savings account(s)");
            System.out.println( Account.getNumOfAccounts() + " total account(s)");
            System.out.println("would you like to make another account?");
            System.out.print("1 for yes, 2 for no:");
            int input = src.nextInt();
            if (input != 1){ // if the input is anything but 1 the program ends otherwise loops back to create another account
                done = true;
            }

        }
    }
}
