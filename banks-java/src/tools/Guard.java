package tools;

public class Guard {
    public static <E> void isNotNull(E value, String message) throws BanksException {
        if (value == null)
            throw new BanksException("The exception occured with message: %s".formatted(message));
    }
}
