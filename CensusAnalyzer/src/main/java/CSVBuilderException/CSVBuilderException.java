package CSVBuilderException;

public class CSVBuilderException extends Exception {
    public ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_DATA
    }
}
