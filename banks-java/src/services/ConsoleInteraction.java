package services;

import objects.*;
import tools.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.out;

public class ConsoleInteraction
{

    public static void mainWork(CentralBank centralBank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(centralBank, "central bank");
        while (true)
        {
            out.println("Welcome! Please, choose yours status py pressing appropriate button");
            out.println("'1' - bank system editor");
            out.println("'2' - client of the bank");
            out.println("'0' - To exit");
            String workString = scan.nextLine();
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer menuDigit = Integer.parseInt(workString);

            switch (menuDigit)
            {
                case 0:
                    return;
                case 1:
                    editorMainWork(centralBank);
                    break;
                case 2:
                    clientMainWork(centralBank);
                    break;
            }
        }
    }

    private static void editorMainWork(CentralBank centralBank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(centralBank, "central bank");
        while (true)
        {
            out.println("Please, choose option by pressing appropriate button");
            out.println("'1' - Register new bank");
            out.println("'2' - Edit the certain bank");
            out.println("'3' - Notify banks");
            out.println("'0' - to Exit");
            String workString = scan.nextLine();
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer menuDigit = Integer.parseInt(workString);

            switch (menuDigit)
            {
                case 0:
                    return;
                case 1:
                    registerNewBank(centralBank);
                    break;
                case 2:
                    editBank(centralBank);
                    break;
                case 3:
                    notifyBanksAboutNewDay(centralBank);
                    break;
            }
        }
    }

    private static void registerNewBank(CentralBank centralBank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(centralBank, "central bank");
        out.println("Please, enter the name of the bank");
        String name = scan.nextLine();
        out.println("Please, enter the credit commission of the bank");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double creditCommission = Double.parseDouble(workString);

        out.println("Please, enter the debit percent");
        workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double debitPercent = Double.parseDouble(workString);

        out.println("Please, enter the verify limit");
        workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double verifyLimit = Double.parseDouble(workString);

        var depositPercents = new HashMap<Integer, Double>();
        out.println("Please, enter the deposit percents in format:");
        out.println("50000 - 2.5");
        out.println("100000 - 4");
        out.println("End");

        while (true)
        {
            workString = scan.nextLine();
            if (Objects.equals(workString, "0"))
            {
                break;
            }

            if (workString == null || workString.isEmpty())
            {
                throw new BanksException("Invalid value of input");
            }

            String[] strings = workString.split(" ");
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer key = Integer.parseInt(workString);

            if (!DoubleTryParse.tryParseDouble(strings[strings.length - 1])){
                throw new BanksException("Invalid format of the input");
            }
            Double value = Double.parseDouble(workString);

            depositPercents.put(key, value);
        }

        centralBank.registerNewBank(name, creditCommission, debitPercent, verifyLimit, depositPercents);
    }

    private static void editBank(CentralBank centralBank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        out.println("Please, enter the name of the bank you would like to edit");
        String name = scan.nextLine();
        Bank tempBank = centralBank.findBankByName(name);
        if (tempBank == null)
        {
            throw new BanksException("Please, check correctness of entered data");
        }

        bankEditingMenu(tempBank);
    }

    private static void bankEditingMenu(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        while (true)
        {
            out.println("Please, choose what you would like to edit");
            out.println("'1' - Set new credit commission");
            out.println("'2' - Set new debit percent");
            out.println("'3' - Set new verify limit");
            out.println("'4' - Set new deposit percents");
            out.println("'0' - Exit");
            String workString = scan.nextLine();
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer menuDigit = Integer.parseInt(workString);

            switch (menuDigit)
            {
                case 0:
                    return;
                case 1:
                    setNewCreditCommission(bank);
                    break;
                case 2:
                    setNewDebitPercent(bank);
                    break;
                case 3:
                    setNewVerifyLimit(bank);
                    break;
                case 4:
                    setNewDepositPercents(bank);
                    break;
            }
        }
    }

    private static void setNewCreditCommission(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        out.println("Please, enter the new value for credit commission");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double newCreditCommission = Double.parseDouble(workString);

        bank.setNewCreditCommission(newCreditCommission);
    }

    private static void setNewDebitPercent(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        out.println("Please, enter the new value for debit percent");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double newDebitPercent = Double.parseDouble(workString);

        bank.setNewDebitPercent(newDebitPercent);
    }

    private static void setNewVerifyLimit(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        out.println("Please, enter the new value for verify limit");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double newVerifyLimit = Double.parseDouble(workString);

        bank.setNewVerifyLimit(newVerifyLimit);
    }

    private static void setNewDepositPercents(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        var depositPercents = new HashMap<Integer, Double>();
        out.println("Please, enter the deposit percents in format:");
        out.println("50000 - 2.5");
        out.println("100000 - 4");
        out.println("End");

        while (true)
        {
            String workString = scan.nextLine();
            if (workString == "0")
            {
                break;
            }

            if (workString == null || workString.isEmpty())
            {
                throw new BanksException("Invalid value of input");
            }

            String[] strings = workString.split(" ");
            if (!IntegerTryParse.tryParseInt(strings[0])){
                throw new BanksException("Invalid format of the input");
            }
            Integer key = Integer.parseInt(workString);

            if (!DoubleTryParse.tryParseDouble(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Double value = Double.parseDouble(strings[strings.length - 1]);

            depositPercents.put(key, value);
        }

        bank.setNewDepositPercents(depositPercents);
    }

    private static void notifyBanksAboutNewDay(CentralBank centralBank) throws BanksException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(centralBank, "central bank");
        centralBank.bankDayNotification();
    }

    private static void clientMainWork(CentralBank centralBank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(centralBank, "central bank");
        while (true)
        {
            out.println("Welcome to "+ centralBank.getName() + "! Please, choose the bank by pressing appropriate button");
            for (int i = 0; i < (long) centralBank.getBanks().size(); i++)
            {
                out.println((i + 1) + " - {centralBank.Banks[i].Name}");
            }

            out.println("'0' - to Exit");

            String workString = scan.nextLine();
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer menuDigit = Integer.parseInt(workString);

            if (menuDigit == 0)
            {
                break;
            }

            bankHello(centralBank.getBanks().get(menuDigit - 1));
        }
    }

    private static void bankHello(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        out.println("Welcome to" + bank.getName() + "! Are you new here?");
        out.println("Please, choose your status by pressing appropriate button");
        out.println("'1' - new client");
        out.println("'2' - regular client");
        out.println("'0' - to Exit");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        switch (menuDigit)
        {
            case 0:
                break;
            case 1:
                clientRegistration(bank);
                break;
            case 2:
                clientEntering(bank);
                break;
        }
    }

    private static void clientRegistration(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        String address = null;
        String passportData = null;
        out.println("Please, enter your name");
        String name = scan.nextLine();
        out.println("Please, enter your surname");
        String surname = scan.nextLine();
        out.println("Would you like to enter the address?");
        out.println("Use '1' for agreement and '2' for rejection");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        switch (menuDigit)
        {
            case 1:
                address = scan.nextLine();
                break;
            case 2:
                break;
        }

        out.println("Would you like to enter your passport data?");
        out.println("Use '1' for agreement and '2' for rejection");
        workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        menuDigit = Integer.parseInt(workString);

        switch (menuDigit)
        {
            case 1:
                passportData = scan.nextLine();
                break;
            case 2:
                break;
        }

        if (address == null && passportData == null)
        {
            bank.regClient(new PersonalData.Builder(name, surname).Build());
        }
        else if (address == null)
        {
            bank.regClient(new PersonalData.Builder(name, surname).setPassport(passportData).Build());
        }
        else if (passportData == null)
        {
            bank.regClient(new PersonalData.Builder(name, surname).setAddress(address).Build());
        }
        else
        {
            bank.regClient(new PersonalData.Builder(name, surname).setAddress(address).setPassport(passportData).Build());
        }

        out.println("Done!");
        clientEntering(bank);
    }

    private static void clientEntering(Bank bank) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(bank, "bank");
        out.println("Please, enter your name");
        String name = scan.nextLine();
        out.println("Please, enter your surname");
        String surname = scan.nextLine();
        Client client = bank.findClientByFullName(name, surname);
        if (client == null)
        {
            throw new BanksException("Invalid input");
        }

        clientPersonalProfile(client);
    }

    private static void clientPersonalProfile(Client client) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(client, "client");
        while (true)
        {
            out.println("Welcome, " + client.getName() + "!");
            out.println("'1' - check all your accounts");
            out.println("'2' - create new account");
            out.println("'0' - exit");
            String workString = scan.nextLine();
            if (!IntegerTryParse.tryParseInt(workString)){
                throw new BanksException("Invalid format of the input");
            }
            Integer menuDigit = Integer.parseInt(workString);

            switch (menuDigit)
            {
                case 0:
                    return;
                case 1:
                    checkAccounts(client);
                    break;
                case 2:
                    createNewAccount(client);
                    break;
            }
        }
    }

    private static void checkAccounts(Client client) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(client, "client");
        if ((long) client.getAccounts().size() == 0)
        {
            out.println("You don't have any accounts");
            return;
        }

        out.println("You can choose the account by entering appropriate digit");
        for (int i = 0; i < (long) client.getAccounts().size(); i++)
        {
            out.println((i + 1) + " - {client.Accounts[i].ReturnAccountType()} {client.Accounts[i].Id().ToString()}");
        }

        out.println("Enter '0' to exit");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        if (menuDigit == 0)
        {
            return;
        }

        accountManager(client.getAccounts().get(menuDigit - 1));
    }

    private static void accountManager(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        out.println("Enter appropriate digit to use option");
        out.println("'1' - To transfer money to another account in this bank");
        out.println("'2' - To transfer money to account in another bank");
        out.println("'3' - To withdraw money");
        out.println("'4' - To deposit money in this account");
        out.println("'5' - To see what will happen in some days");
        out.println("'6' - To request cancelling for operation");
        out.println("'0' -To exit");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        switch (menuDigit)
        {
            case 0:
                return;
            case 1:
                moneyTransferInBank(account);
                break;
            case 2:
                moneyTransferAnotherBank(account);
                break;
            case 3:
                withDrawMoney(account);
                break;
            case 4:
                depositMoney(account);
                break;
            case 5:
                timeMachineInteract(account);
                break;
            case 6:
                requestToCancelTransaction(account);
                break;
        }
    }

    private static void createNewAccount(Client client) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(client, "client");
        out.println("Please, choose the type of account by entering the appropriate digit");
        out.println("'1' - DebitAccount");
        out.println("'2' - Deposit account");
        out.println("'3' - Credit account");
        out.println("'0' - To exit");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        switch (menuDigit)
        {
            case 0:
                return;
            case 1:
                createDebitAccount(client);
                break;
            case 2:
                createDepositAccount(client);
                break;
            case 3:
                createCreditAccount(client);
                break;
        }
    }

    private static void requestToCancelTransaction(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        Bank bank = account.getClient().getBank();
        out.println("Please, give the id of transaction");
        String workString = scan.nextLine();
        ITransaction transaction = bank.searchTransactionById(workString);
        if (transaction.getAccountFrom() == account)
            throw new BanksException("The transaction was made not from yours account");
        bank.cancelTransaction(transaction);
    }

    private static void createDebitAccount(Client client) throws BanksException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(client, "client");
        var tempDebitAccount = new DebitAccount(client);
        client.getBank().regClientAccount(client, tempDebitAccount);
        out.println("Done!");
    }

    private static void createCreditAccount(Client client) throws BanksException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(client, "client");
        var tempCreditAccount = new DebitAccount(client);
        client.getBank().regClientAccount(client, tempCreditAccount);
    }

    private static void createDepositAccount(Client client) throws BanksException, IOException {
        Guard.isNotNull(client, "client");
        Scanner scan = new Scanner(System.in);
        Bank bank = client.getBank();
        out.println("Please, enter the sum for account");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double sum = Double.parseDouble(workString);

        out.println("Please, enter the timeframe for account");
        workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer timeframe = Integer.parseInt(workString);

        var tempDepositAccount = new DepositAccount(client, sum, timeframe);
        bank.regClientAccount(client, tempDepositAccount);
        out.println("Done!");
    }

    private static void moneyTransferInBank(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        out.println("Please, enter the id of account you want to transfer to");
        String secondAccountId = scan.nextLine();
        out.println("Please, enter the sum of transfer");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double sum = Double.parseDouble(workString);

        account.getClient().getBank().moneyTransfer(account, secondAccountId, sum);
        out.println("Done!");
    }

    private static void moneyTransferAnotherBank(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        CentralBank centralBank = account.getClient().getBank().getCentralBank();
        out.println("Please, choose the bank from the list by entering appropriate digit");
        out.println("'0' - to Exit");
        for (int i = 0; i < (long) centralBank.getBanks().size(); i++)
        {
            out.println((i + 1) + " - {centralBank.Banks[i].Name}");
        }

        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer menuDigit = Integer.parseInt(workString);

        if (menuDigit == 0)
        {
            return;
        }

        out.println("Please, enter the id of the account you want to transfer money to");
        String id = scan.nextLine();
        out.println("Please, enter the sum you want to transfer");
        workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double sum = Double.parseDouble(workString);

        centralBank.transactionBetweenBanks(account, centralBank.getBanks().get(menuDigit - 1), id, sum);
        out.println("Done!");
    }

    private static void withDrawMoney(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        out.println("Please, enter the su you want to withdraw");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double sum = Double.parseDouble(workString);

        account.getClient().getBank().moneyWithdraw(account, sum);
        out.println("Done!");
    }

    private static void depositMoney(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        out.println("Please, enter the su you want to deposit");
        String workString = scan.nextLine();
        if (!DoubleTryParse.tryParseDouble(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Double sum = Double.parseDouble(workString);

        account.getClient().getBank().depositMoney(account, sum);
        out.println("Done!");
    }

    private static void timeMachineInteract(IAccount account) throws BanksException, IOException {
        Scanner scan = new Scanner(System.in);
        Guard.isNotNull(account, "account");
        out.println("Please, enter the number of days you want to see in");
        String workString = scan.nextLine();
        if (!IntegerTryParse.tryParseInt(workString)){
            throw new BanksException("Invalid format of the input");
        }
        Integer days = Integer.parseInt(workString);

        IAccount tempAccount = TimeMachine.work(account, days);
        out.println("In " +  days + " days there will be {tempAccount.Balance()} on the balance");
    }
}