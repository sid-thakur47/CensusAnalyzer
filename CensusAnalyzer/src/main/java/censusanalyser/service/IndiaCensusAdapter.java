package censusanalyser.service;

import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;
import censusanalyser.model.IndiaCensusCSV;
import censusanalyser.model.IndianState;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndiaCensusAdapter extends CensusAdapter {
    Map<String, CensusDAO> censusMap = new HashMap<>();

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusMap = super.loadCensusData( IndiaCensusCSV.class, csvFilePath[0] );
        this.loadIndianStateCodeData( censusMap, csvFilePath[1] );
        return censusMap;
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
