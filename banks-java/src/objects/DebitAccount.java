package objects;

import services.*;
import tools.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebitAccount implements IAccount {

    private final double ZeroBalance = 0.0;
    private final int MonthDays = 30;
    private final int YearDays = 365;

    private final Client client;
    private final UUID id;
    private final List<ITransaction> historyOfTransactions;
    private Integer age;
    private Double balance;
    private Double currentSumOfMonthRefill;

    public DebitAccount(Client client) throws BanksException {
        Guard.isNotNull(client, "client");
        this.client = client;
        balance = ZeroBalance;
        currentSumOfMonthRefill = ZeroBalance;
        age = 0;
        id = UUID.randomUUID();
        historyOfTransactions = new ArrayList<ITransaction>();
    }

    private DebitAccount(DebitAccount other) throws BanksException {

        Guard.isNotNull(other, "other debit account");
        client = other.client;
        id = other.id;
        age = other.age;
        balance = other.balance;
        currentSumOfMonthRefill = other.currentSumOfMonthRefill;
        historyOfTransactions = other.historyOfTransactions;
    }

    public void addTransaction(ITransaction transaction) throws BanksException {
        Guard.isNotNull(transaction, "transaction");
        historyOfTransactions.add(transaction);
    }

    public Double getBalance()
    {
        return balance;
    }

    public UUID getId()
    {
        return id;
    }

    public Client getClient()
    {
        return client;
    }

    public List<ITransaction> getHistoryOfTransactions(){ return historyOfTransactions.stream().toList(); }

    public void increaseMoney(Double amount) throws BanksException {
        if (amount < 0)
            throw new BanksException("Invalid value of amount");
        balance += amount;
    }

    public void reduceMoney(Double amount) throws BanksException {
        if (amount < 0)
            throw new BanksException("Invalid value of amount");
        balance -= amount;
    }

    public void dailyBankOperations()
    {
        if (age % MonthDays != 0)
        {
            currentSumOfMonthRefill += balance * getPercent() / (100 * YearDays);
        }
        else
        {
            balance += currentSumOfMonthRefill;
            currentSumOfMonthRefill = ZeroBalance;
        }

        age++;
    }

    public String getAccountType()
    {
        return "Debit account";
    }

    public IAccount makeCopy() throws BanksException {
        return new DebitAccount(this);
    }

    private double getPercent()
    {
        return client.getBank().getDebitPercent();
    }
}