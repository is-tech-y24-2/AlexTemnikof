package objects;

import services.IAccount;
import tools.*;

import java.util.ArrayList;
import java.util.List;

public class Client
{
    private final Bank bank;
    private final PersonalData personalData;
    private final List<IAccount> accounts;
    private boolean identified;
    private boolean subscribed;

    public Client(PersonalData personalData, Bank bank) throws BanksException {
        Guard.isNotNull(personalData, "personal data");
        Guard.isNotNull(bank, "bank");
        this.bank = bank;
        this.personalData = personalData;
        accounts = new ArrayList<IAccount>();
        identified = this.bank.checkVerifyOfClient(this);
    }

    public Bank getBank(){
        return bank;
    };

    public List<IAccount> getAccounts(){ return accounts.stream().toList(); }

    public boolean requestTheVerifyCheck() throws BanksException {
        identified = bank.checkVerifyOfClient(this);
        return identified;
    }

    public String getName()
    {
        return personalData.getName();
    }

    public String getSurname()
    {
        return personalData.getSurname();
    }

    public String getPassportData()
    {
        return personalData.getPassportData();
    }

    public String getAddress()
    {
        return personalData.getAddress();
    }

    public void addAccount(IAccount account) throws BanksException {
        Guard.isNotNull(account, "account");
        accounts.add(account);
    }

    public void notify(String message){
        if (subscribed)
            System.out.println(message);
    }

    public void subscribe(){
        subscribed = true;
    }

    public void unSubscribe(){
        subscribed = false;
    }

}