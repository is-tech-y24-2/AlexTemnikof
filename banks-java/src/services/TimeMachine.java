package services;

import tools.BanksException;

public class TimeMachine
{
    public static IAccount work(IAccount account, int days) throws BanksException {
        IAccount tempAccount = account.makeCopy();
        for (int i = 0; i < days; i++)
        {
            tempAccount.dailyBankOperations();
        }

        return tempAccount;
    }
}
