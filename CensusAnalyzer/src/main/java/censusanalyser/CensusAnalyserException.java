package censusanalyser;

public class CensusAnalyserException extends Exception {

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_DELIMITER;
    }
}
