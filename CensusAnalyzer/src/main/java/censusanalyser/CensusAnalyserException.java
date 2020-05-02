package censusanalyser;

public class CensusAnalyserException extends Exception {

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,WRONG_DATA
    }
}
