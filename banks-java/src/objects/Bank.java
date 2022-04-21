package objects;
import services.*;
import tools.*;
import java.util.*;


public class Bank {

    private final CentralBank centralBank;
    private final String name;
    private final List<Client> clients;
    private Double creditCommission;
    private Double debitPercent;
    private Double verifyLimit;
    private Map<Integer, Double> depositPercents;

    public Bank(CentralBank centralBank, String name, Double creditCommission, Double debitPercent, Double verifyLimit, Map<Integer, Double> depositPercents) throws BanksException {
        ParameterValidation(centralBank, name, creditCommission, debitPercent, verifyLimit, depositPercents);
        this.centralBank = centralBank;
        this.name = name;
        clients = Collections.emptyList();
        this.creditCommission = creditCommission;
        this.debitPercent = debitPercent;
        this.depositPercents = new HashMap<Integer, Double>();
        this.verifyLimit = verifyLimit;
    }

    public String getName(){
        return name;
    }

    public Double getDebitPercent(){
        return debitPercent;
    }

    public Double getCreditCommission(){
        return creditCommission;
    }

    public CentralBank getCentralBank(){
        return centralBank;
    }

    public Map<Integer, Double> getDepositPercents(){ return depositPercents; }

    public List<objects.Client> getClients(){ return clients.stream().toList(); }

    private void ParameterValidation(CentralBank centralBank, String name, Double creditCommission, Double debitPercent, Double verifyLimit, Map<Integer, Double> depositPercents) throws BanksException {
        if (name == null || name.isEmpty())
            throw new BanksException("Invalid value of input");
        if (centralBank == null)
            throw new BanksException("Invalid value of central bank");
        if (creditCommission < 0)
            throw new BanksException("Invalid value of credit commission");
        if (debitPercent > 100 || debitPercent < 0)
            throw new BanksException("Invalid value of debit percent");
        if (verifyLimit < 0)
            throw new BanksException("Invalid value of verify limit");
        if (this.depositPercents != null && this.depositPercents.values().stream().anyMatch(d -> d > 100 || d < 0))
            throw new BanksException("Invalid value of deposit percents");
        if (this.depositPercents != null && this.depositPercents.keySet().stream().anyMatch(d -> d < 0))
            throw new BanksException("Invalid value of deposit limits");
    }


    public void setNewDebitPercent(Double newValue) throws BanksException {
        if (newValue < 0 || newValue > 100)
            throw new BanksException("Invalid value of the new debit percents");
        debitPercent = newValue;
        notifyClients("Changed debit percent. The new value is " + newValue);
    }

    public void setNewCreditCommission(Double newValue) throws BanksException {
        if (newValue < 0)
            throw new BanksException("Invalid value of the new value of the credit commission");
        creditCommission = newValue;
        notifyClients("Changed the credit commision. The new value is " + newValue);
    }

    public void setNewDepositPercents(Map<Integer, Double> newValue) throws BanksException {
        Guard.isNotNull(newValue, "new value for deposit percents");
        if (newValue.keySet().stream().anyMatch(s -> s < 0))
            throw new BanksException("At least one sum has invalid value");
        if (newValue.values().stream().anyMatch(p -> p < 0))
            throw new BanksException("At least one element of percents has invalid value");
        depositPercents = newValue;
        notifyClients("Changed the deposit percents.");
    }

    public void setNewVerifyLimit(Double newValue) throws BanksException {
        if (newValue < 0)
            throw new BanksException("Invalid value for new value of verify limit");
        verifyLimit = newValue;
        notifyClients("Changed the verify limit. New value" + newValue);
    }

    public Client regClient(PersonalData personalData) throws BanksException {
        Guard.isNotNull(personalData, "personal data");
        var tempClient = new Client(personalData, this);
        clients.add(tempClient);
        return tempClient;
    }

    public Client findClientByPassport(String passportData) throws BanksException {
        if (passportData == null || passportData.isEmpty())
            throw new BanksException("Invalid value of passport data");

        return clients.stream().filter(s -> s.getPassportData().equals(passportData)).findFirst().get();
    }

    public Client findClientByFullName(String name, String surname) throws BanksException {
        if (name == null || name.isEmpty())
            throw new BanksException("Invalid value of name");

        if (surname == null || surname.isEmpty())
            throw new BanksException("Invalid value of surname");

        return clients.stream().filter(s  -> s.getName().equals(name) && s.getSurname().equals(surname)).findFirst().get();
    }

    public void regClientAccount(Client client, IAccount account) throws BanksException {
        Guard.isNotNull(client, "client");
        Guard.isNotNull(account, "account");
        client.addAccount(account);
    }

    public boolean checkVerifyOfClient(Client client) throws BanksException {
        Guard.isNotNull(client, "client");
        return client.getAddress() != null && client.getPassportData() != null;
    }

    public IAccount findAccountById(String id) throws BanksException {
        if (id == null || id.isEmpty())
            throw new BanksException("Invalid value of id");

        for (Client c : clients) {
            for (IAccount a : c.getAccounts()) {
                if (a.getId().toString().equals(id))
                    return a;
            }
        }

        throw new BanksException("Such account is not registered");
    }

    public void moneyTransfer(IAccount firstAccount, String secondAccountId, double sum) throws BanksException {
        Guard.isNotNull(firstAccount, "first account");
        if (secondAccountId.isEmpty())
            throw new BanksException("Invalid value of second account id");

        IAccount secondAccount = findAccountById(secondAccountId);
        if (secondAccount == null)
            throw new BanksException("There is no such account in the bank");

        if (firstAccount == secondAccount)
            throw new BanksException("You can't transfer money to the account where you are transfering from");
        if (!checkVerifyOfClient(firstAccount.getClient()) && sum > verifyLimit)
            throw new BanksException("You need to enter all you personal data to gain the ability to transfer that sum");
        if (firstAccount.getBalance() < sum)
            throw new BanksException("You can't transfer the sum bigger than your balance");
        var transfer = new TransferOfMoney(firstAccount, secondAccount, sum);
        if (!firstAccount.getClient().getBank().permissionForReduceMoneyTransaction(transfer)) return;
        firstAccount.reduceMoney(sum);
        secondAccount.increaseMoney(sum);
        firstAccount.addTransaction(transfer);
        secondAccount.addTransaction(transfer);
    }

    public void moneyWithdraw(IAccount account, double sum) throws BanksException {
        Guard.isNotNull(account, "account");
        if (sum < 0)
            throw new BanksException("Invalid value of sum");
        if (!checkVerifyOfClient(account.getClient()) && sum > verifyLimit)
            throw new BanksException("You need to enter all you personal data to gain the ability to withdraw that sum");
        if (account.getBalance() < sum) throw new BanksException("You can't transfer the sum bigger than your balance");
        var moneyWithdraw = new WithdrawOfMoney(account, sum);
        if (account.getClient().getBank().permissionForReduceMoneyTransaction(moneyWithdraw)) {
            account.reduceMoney(sum);
            account.addTransaction(moneyWithdraw);
        }
    }

    public void depositMoney(IAccount account, double sum) throws BanksException {
        Guard.isNotNull(account, "account");
        if (sum < 0)
            throw new BanksException("Invalid value of sum");
        account.increaseMoney(sum);
        var withdrawMoney = new WithdrawOfMoney(account, sum);
        account.addTransaction(withdrawMoney);
    }

    public void cancelTransaction(ITransaction transaction) throws BanksException {
        Guard.isNotNull(transaction, "transaction");
        transaction.cancelTransaction();
    }

    public ITransaction searchTransactionById(String id) throws BanksException {
        if (id == null || id.isEmpty())
            throw new BanksException("Invalid value of id");
        for (Client c : clients){
            for (IAccount a : c.getAccounts())
                for (ITransaction t : a.getHistoryOfTransactions()){
                    if (t.getId().toString().equals(id))
                        return t;
                }
        }
        return null;
    }

    public boolean permissionForReduceMoneyTransaction(ITransaction transaction) throws BanksException {
        if (!checkVerifyOfClient(transaction.getAccountFrom().getClient()) && transaction.getSum() > verifyLimit) {
            throw new BanksException("You need to enter more personal data to interact with this sum");
        }

        if (transaction.getSum() > transaction.getAccountFrom().getBalance()) {
            throw new BanksException("Not enough money on account");
        }

        return true;
    }

    private void notifyClients(String message){
        for (Client c : clients)
            c.notify(message);
    }

}
