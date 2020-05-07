package censusanalyser.service;

import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusClass,
                                                     String csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusMap = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CSVBuilderInterface csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<E> csvList = csvBuilderInterface.getCSVFileList( reader, censusClass );
            csvList.forEach( (censusCSV) -> new CensusAdapterFactory().getCensusObject( censusClass,
                    censusMap, censusCSV ) );
            return censusMap;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
}