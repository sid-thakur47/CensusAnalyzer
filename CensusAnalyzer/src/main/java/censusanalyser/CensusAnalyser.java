package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {
    List<IndiaDetails> censusCSVList = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilderInterface.getCSVFileList( reader, IndiaCensusCSV.class );
            return censusCSVList.size();
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
            censusCSVList = csvBuilderInterface.getCSVFileList( reader, IndianState.class );
            return censusCSVList.size();
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    public String getStateWiseCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException( "No Cenus Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA );
        }
        Comparator<IndiaDetails> censusComparator = Comparator.comparing( IndiaDetails::getState );
        this.sort( censusComparator );
        String sortedStateCensusJson = new Gson().toJson( censusCSVList );
        return sortedStateCensusJson;
    }
    private void sort(Comparator<IndiaDetails> censusComparator) {
        for (int i = 0; i < censusCSVList.size() - 1; i++) {
            for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
                IndiaDetails census1 = censusCSVList.get( j );
                IndiaDetails census2 = censusCSVList.get( j + 1 );
                if (censusComparator.compare( census1, census2 ) > 0) {
                    censusCSVList.set( j, census2 );
                    censusCSVList.set( j + 1, census1 );
                }
            }
        }
    }
}