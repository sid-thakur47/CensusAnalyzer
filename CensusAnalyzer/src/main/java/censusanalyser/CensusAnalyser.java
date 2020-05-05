package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList;
    Map<String, IndiaCensusDAO> censusMap;

    public CensusAnalyser() {
        this.censusList = new ArrayList<>();
        this.censusMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List csvList = csvBuilderInterface.getCSVFileList( reader, IndiaCensusCSV.class );
            Stream<IndiaCensusCSV> stream = csvList.stream();
            stream.forEachOrdered( (indiaCensusCSV) -> censusMap.put( indiaCensusCSV.state, new IndiaCensusDAO( indiaCensusCSV ) ) );
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

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List csvList = csvBuilderInterface.getCSVFileList( reader, IndianState.class );
            Stream<IndianState> stream = csvList.stream();
            stream.forEachOrdered( (indiastate) -> censusMap.put( indiastate.state, new IndiaCensusDAO( indiastate ) ) );
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
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException( "No Cenus Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA );
        }
        Comparator<IndiaCensusDAO> censusComparator = new CSVFieldSorter().getCurrentSort( fieldType );
        this.sortIntegerValues( censusComparator );
        String sortedStateCensusJson = new Gson().toJson( this.censusList );
        return sortedStateCensusJson;
    }

    private void sortIntegerValues(Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                IndiaCensusDAO census1 = censusList.get( j );
                IndiaCensusDAO census2 = censusList.get( j + 1 );
                if (censusComparator.compare( census2, census1 ) > 0) {
                    censusList.set( j, census2 );
                    censusList.set( j + 1, census1 );
                }
            }
        }
    }
}
