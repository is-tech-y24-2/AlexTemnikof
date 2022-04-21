package objects;

import services.*;
import tools.BanksException;
import tools.Guard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditAccount implements IAccount {
    private final int MonthDays = 30;
    private final double ZeroBalance = 0.0;
    private final Client client;
    private final UUID id;
    private Integer age;
    private Double balance;
    private final List<ITransaction> historyOfTransactions;

    public CreditAccount(Client client) throws BanksException {
        Guard.isNotNull(client, "client");
        this.client = client;
        balance = ZeroBalance;
        age = 0;
        id = UUID.randomUUID();
        historyOfTransactions = new ArrayList<ITransaction>();
    }

    private CreditAccount(CreditAccount other) throws BanksException {
        Guard.isNotNull(other, "other credit account");
        client = other.client;
        id = other.id;
        age = other.age;
        balance = other.balance;
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
        if (age % MonthDays == 0)
        {
            if (balance < 0)
            {
                balance -= getCommission();
            }
        }

        age++;
    }

    public String getAccountType()
    {
        return "Credit account";
    }

    public IAccount makeCopy() throws BanksException {
        return new CreditAccount(this);
    }

    private double getCommission() {
        return client.getBank().getCreditCommission();
    }
}