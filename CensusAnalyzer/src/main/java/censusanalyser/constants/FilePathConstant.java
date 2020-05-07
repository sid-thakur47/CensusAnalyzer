package censusanalyser.constants;

public interface FilePathConstant {

    String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    String WRONG_CSV_FILE_PATH = "./src/resources/IndiaStateCensusData.csv";
    String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.doc";
    String WRONG_DELIMITER_FILE = "./src/main/resources/IndiaStateCensusData.csv";
    String WRONG_HEADER_FILE = "./src/main/resources/IndiaStateCensusDataHeader.csv";
    String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    String WRONG_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    String WRONG_STATE_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.doc";
    String WRONG_STATE_DELIMITER_FILE = "./src/main/resources/IndiaStateCodeDelimiter.csv";
    String WRONG_STATE_HEADER_FILE = "./src/main/resources/IndiaStateCodeHeader.csv";
    String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    enum Country {
        INDIA, US
    }
}


