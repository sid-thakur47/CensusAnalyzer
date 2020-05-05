package censusanalyser.exception;

public class CensusAnalyserException extends Exception {

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,WRONG_DATA,NO_CENSUS_DATA,NO_SUCH_CLASS
    }
}