package censusanalyser.service;

import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;
import censusanalyser.model.IndiaCensusCSV;
import censusanalyser.model.IndianState;
import censusanalyser.model.USCensusCSV;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<CensusDAO> censusList;
    Map<String, CensusDAO> censusMap;

    public CensusAnalyser() {
        this.censusList = new ArrayList<>();
        this.censusMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData( csvFilePath, IndiaCensusCSV.class );
    }

    public int loadUSCodeData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData( csvFilePath, USCensusCSV.class );
    }
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData( csvFilePath, IndianState.class );
    }

    public <E> int loadCensusData(String csvFilePath, Class<E> censusClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<E> csvList = csvBuilderInterface.getCSVFileList( reader, censusClass );
            csvList.forEach( (censusCSV) -> new CSVFieldSorter().getCensusObject( censusClass, censusMap, censusCSV ) );
            censusList = new ArrayList( censusMap.values() );
            return censusList.size();
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    public String getStateWiseCensusData(String fieldType) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException( "No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA );
        }
        censusList = new ArrayList( censusMap.values() );
        Comparator<CensusDAO> censusComparator = new CSVFieldSorter().getCurrentSort( fieldType );
        this.sort( censusComparator );
        String sortedStateCensusJson = new Gson().toJson( this.censusList );
        return sortedStateCensusJson;
    }

    private void sort(Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                CensusDAO census1 = censusList.get( j );
                CensusDAO census2 = censusList.get( j + 1 );
                if (censusComparator.compare( census2, census1 ) > 0) {
                    censusList.set( j, census2 );
                    censusList.set( j + 1, census1 );
                }
            }
        }
    }
}