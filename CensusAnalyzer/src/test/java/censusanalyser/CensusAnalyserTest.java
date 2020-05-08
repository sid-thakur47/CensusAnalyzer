package censusanalyser;

import censusanalyser.constants.FilePathConstant;
import censusanalyser.exception.CensusAnalyserException;
import censusanalyser.model.IndiaCensusCSV;
import censusanalyser.model.USCensusCSV;
import censusanalyser.service.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

public class CensusAnalyserTest implements FilePathConstant {

    @Test
    public void givenIndianCensus_CSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            int numOfRecords = censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE_PATH );
            Assert.assertEquals( 29, numOfRecords );
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithWrongFileType_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_TYPE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithIncorrectDelimiter_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_DELIMITER_FILE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WithIncorrectHeader_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_HEADER_FILE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianStateCode_CSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            int numOfRecords = censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_STATE_CSV_FILE_PATH );
            Assert.assertEquals( 36, numOfRecords );
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_STATE_CSV_FILE_PATH );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }

    @Test
    public void givenIndianStateCode_WithWrongFileType_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_STATE_CSV_FILE_TYPE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }
    @Test
    public void givenIndianStateCodeData_WithIncorrectDelimiter_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_STATE_DELIMITER_FILE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianStateCodeData_WithIncorrectHeader_Should_ReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, WRONG_STATE_HEADER_FILE );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.WRONG_DATA, e.type );
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnStartSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
        censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "state" );
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class );
        Collections.reverse( Arrays.asList( censusCSV ) );
        Assert.assertEquals( "Andhra Pradesh", censusCSV[0].state );
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnEndSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
        censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "state" );
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class );
        Collections.reverse( Arrays.asList( censusCSV ) );
        Assert.assertEquals( "West Bengal", censusCSV[censusCSV.length - 1].state );
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
        censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "population" );
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class );
        Assert.assertEquals( "Uttar Pradesh", censusCSV[0].state );
    }
    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.INDIA );
        censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "density" );
        IndiaCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, IndiaCensusCSV[].class );
        Assert.assertEquals( "Bihar", censusCSV[0].state );
    }
    @Test
    public void givenUSCensus_CSVFile_ReturnsCorrectRecords() throws CensusAnalyserException {

        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        int numOfRecords = censusAnalyser.loadCensusData( CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH );
        Assert.assertEquals( 51, numOfRecords );
    }
    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedStartResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "population" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "California", censusCSV[0].state );
    }
    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedEndResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "population" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "Wyoming", censusCSV[censusCSV.length - 1].state );
    }
    @Test
    public void givenUSCensusData_WhenSortedOnDensity_ShouldReturnMostDenseStateSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "density" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "District of Columbia", censusCSV[0].state );
    }
    @Test
    public void givenUSCensusData_WhenSortedOnDensity_ShouldReturnLeastDenseStateSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "density" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "Alaska", censusCSV[censusCSV.length - 1].state );
    }

    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnLargestAreaSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "area" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "Alaska", censusCSV[0].state );
    }
    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnSmallestAreaSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser( Country.US );
        censusAnalyser.loadCensusData( Country.US, US_CENSUS_CSV_FILE_PATH );
        String sortedCensusData = censusAnalyser.getStateWiseCensusData( "area" );
        USCensusCSV[] censusCSV = new Gson().fromJson( sortedCensusData, USCensusCSV[].class );
        Assert.assertEquals( "Alaska", censusCSV[censusCSV.length - 1].state );
    }
}