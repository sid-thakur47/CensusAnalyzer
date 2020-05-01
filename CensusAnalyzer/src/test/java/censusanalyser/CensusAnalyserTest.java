package censusanalyser;

import CSVBuilderException.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.doc";
    private static final String WRONG_DELIMITER_FILE = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_HEADER_FILE = "./src/main/resources/IndiaStateCensusDataHeader.csv";

    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.doc";
    private static final String WRONG_STATE_DELIMITER_FILE = "./src/main/resources/IndiaStateCodeDelimiter.csv";
    private static final String WRONG_STATE_HEADER_FILE = "./src/main/resources/IndiaStateCodeHeader.csv";

    @Test
    public void givenIndianCensus_CSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData( INDIA_CENSUS_CSV_FILE_PATH );
            Assert.assertEquals( 29, numOfRecords );
        } catch (CSVBuilderException e) {
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndiaCensusData( WRONG_CSV_FILE_PATH );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithWrongFileType_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData( WRONG_CSV_FILE_TYPE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithIncorrectDelimiter_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData( WRONG_DELIMITER_FILE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithIncorrectHeader_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData( WRONG_HEADER_FILE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianStateCode_CSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStateCodeData( INDIA_STATE_CSV_FILE_PATH );
            Assert.assertEquals( 37, numOfRecords );
        } catch (CSVBuilderException e) {
        }
    }
    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_CSV_FILE_PATH );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }

    @Test
    public void givenIndianStateCode_WithWrongFileType_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_CSV_FILE_TYPE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianStateCodeData_WithIncorrectDelimiter_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_DELIMITER_FILE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianStateCodeData_WithIncorrectHeader_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_HEADER_FILE );
        } catch (CSVBuilderException e) {
            Assert.assertEquals( CSVBuilderException.ExceptionType.WRONG_DATA, e.type );
        }
    }
}
