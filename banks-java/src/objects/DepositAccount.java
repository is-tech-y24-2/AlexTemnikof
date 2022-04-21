package objects;

import services.*;
import tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DepositAccount implements IAccount{

    private final double ZeroBalance = 0.0;
    private final int MonthDays = 30;
    private final int YearDays = 365;

    private final Client client;
    private final Double percent;
    private final Integer timeframe;
    private final UUID id;
    private final List<ITransaction> historyOfTransactions;
    private Double balance;
    private Integer age;
    private Double currentSumOfMonthRefill;

    public DepositAccount(Client client, Double sum, Integer timeframe) throws BanksException {
        Guard.isNotNull(client, "client");
        if (sum < 0)
            throw new BanksException("Invalid value of sum");
        if (timeframe < 0)
            throw new BanksException("Invalid value of timeframe");
        this.client = client;
        id = UUID.randomUUID();
        balance = sum;
        percent = GetPercent(sum);
        this.timeframe = timeframe;
        currentSumOfMonthRefill = ZeroBalance;
        age = 0;
        historyOfTransactions = new ArrayList<ITransaction>();
    }

    private DepositAccount(DepositAccount other) throws BanksException {
        Guard.isNotNull(other, "other deposit account");
        client = other.client;
        percent = other.percent;
        timeframe = other.timeframe;
        id = other.id;
        balance = other.balance;
        age = other.age;
        currentSumOfMonthRefill = other.currentSumOfMonthRefill;
        historyOfTransactions = other.historyOfTransactions;
    }

    public Double getBalance() {
        return balance;
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<ITransaction> getHistoryOfTransactions() {
        return historyOfTransactions.stream().toList();
    }

    public void increaseMoney(Double amount) throws BanksException {
        if (amount < 0)
            throw new BanksException("Invalid value of amount");
        balance += amount;
    }

    public void reduceMoney(Double amount) throws BanksException {
        if (amount < 0)
            throw new BanksException("Invalid value of amount");
        if (age < timeframe)
            throw new BanksException("You can't do anything with deposit account until it's lock date expires");
        balance -= amount;
    }

    public void dailyBankOperations()
    {
        if (age % MonthDays == 0) {
            balance += currentSumOfMonthRefill;
            currentSumOfMonthRefill = ZeroBalance;
        }
        else
        {
            currentSumOfMonthRefill += balance * percent / (100 * YearDays);
        }

        age++;
    }

    public void addTransaction(ITransaction transaction) throws BanksException {
        Guard.isNotNull(transaction, "transaction");
        historyOfTransactions.add(transaction);
    }

    public String getAccountType()
    {
        return "Deposit account";
    }

    public IAccount makeCopy() throws BanksException {
        return new DepositAccount(this);
    }

    private Double GetPercent(Double sum) throws BanksException {
        if (sum < 0)
            throw new BanksException("Invalid value of sum");
        Integer tempKey = client.getBank().getDepositPercents().keySet().stream().filter(s -> s < sum).findFirst().get();
        return client.getBank().getDepositPercents().get(tempKey);

    }
}