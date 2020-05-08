package censusanalyser.service;

import censusanalyser.constants.FilePathConstant;
import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyser implements FilePathConstant {
    public Country country;
    public CensusAnalyser(Country country) {
        this.country=country;
    }
    Map<String, CensusDAO> censusMap;

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusMap = CensusAdapterFactory.getCensusData( country,csvFilePath );
        return censusMap.size();
    }

    public String getStateWiseCensusData(String fieldType) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException( "No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA );
        }
        Comparator<CensusDAO> censusComparator = new CensusAdapterFactory().getCurrentSort( fieldType );
        List censusDTO=censusMap.values().stream()
                .sorted(censusComparator.reversed())
                .map( censusDAO -> censusDAO.getCensusDTO(country))
                .collect( Collectors.toList());
        String sortedStateCensusJson = new Gson().toJson( censusDTO );
        return sortedStateCensusJson;
    }


}