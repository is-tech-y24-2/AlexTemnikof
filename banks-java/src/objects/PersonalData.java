package objects;

import tools.*;

public class PersonalData
{
    private final String name;
    private final String passportData;
    private final String address;
    private final String surname;

    private PersonalData(Builder builder)
    {
        name = builder.getName();
        surname = builder.getSurname();
        address = builder.getAddress();
        passportData = builder.getPassportData();
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
    public String getPassportData(){
        return passportData;
    }

    public String getAddress(){
        return address;
    }

    public static class Builder
    {
        private final String _name;

        private final String _surname;

        private String _passportData;

        private String _address;

        public Builder(String name, String surname) throws BanksException {
            if (name == null || name.isEmpty())
            {
                throw new BanksException("Invalid value of name");
            }

            if (surname == null || surname.isEmpty())
            {
                throw new BanksException("Invalid value of surname");
            }

            _name = name;
            _surname = surname;
        }

        private String getName(){ return _name; }

        private String getSurname(){ return _surname; }

        private String getPassportData(){ return _passportData; }

        private String getAddress(){ return _address; }


        public Builder setAddress(String address) throws BanksException {
            if (address == null || address.isEmpty())
            {
                throw new BanksException("Invalid value of address");
            }

            _address = address;
            return this;
        }

        public Builder setPassport(String passportData) throws BanksException {
            if (passportData == null || passportData.isEmpty())
            {
                throw new BanksException("Invalid value of passport data");
            }

            _passportData = passportData;
            return this;
        }

        public PersonalData Build()
        {
            return new PersonalData(this);
        }
    }
}
