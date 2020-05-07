package censusanalyser.service;

import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;
import censusanalyser.model.IndiaCensusCSV;
import censusanalyser.model.IndianState;
import censusanalyser.model.USCensusCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CensusLoader {
    Map<String, CensusDAO> censusMap = new HashMap<>();

    public Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country,
                                                 String... csvFilePath) throws CensusAnalyserException {
        if (country.equals( country.INDIA )) {
            return this.loadCensusData( IndiaCensusCSV.class, csvFilePath );
        } else if (country.equals( country.US )) {
            return this.loadCensusData( USCensusCSV.class, csvFilePath );
        } else {
            throw new CensusAnalyserException( "Incorrect Country", CensusAnalyserException.ExceptionType.WRONG_CLASS );
        }
    }

    private <E> Map<String, CensusDAO> loadCensusData(Class<E> censusClass,
                                                      String... csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath[0] ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<E> csvList = csvBuilderInterface.getCSVFileList( reader, censusClass );
            csvList.forEach( (censusCSV) -> new CSVFieldSorter().getCensusObject( censusClass,
                    censusMap, censusCSV ) );
            if (csvFilePath.length == 1) return censusMap;
            this.loadIndianStateCodeData( censusMap, csvFilePath[1] );
            return censusMap;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    public int loadIndianStateCodeData(Map<String, CensusDAO> censusMap,
                                       String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<IndianState> csvList = csvBuilderInterface.getCSVFileList
                    ( reader, IndianState.class );
            csvList.stream().filter( stateCSV -> this.censusMap.get( stateCSV.state ) != null )
                    .forEach( stateCSV -> this.censusMap.get( stateCSV.state ).stateCode = stateCSV.stateCode );
            return this.censusMap.size();
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
}
