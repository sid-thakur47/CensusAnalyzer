package censusanalyser.service;

import censusanalyser.dao.CensusDAO;
import censusanalyser.exception.CensusAnalyserException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;

public class CensusAdapterFactory {

    public static Map<String, CensusDAO> getCensusData(CensusAnalyser.Country country,
                                                       String... csvFilePath) throws CensusAnalyserException {
        if (country.equals( country.INDIA )) {
            return new IndiaCensusAdapter().loadCensusData( csvFilePath );
        } else if (country.equals( country.US )) {
            return new USCensusAdapter().loadCensusData( csvFilePath );
        } else {
            throw new CensusAnalyserException( "Incorrect Country", CensusAnalyserException.ExceptionType.WRONG_CLASS );
        }
    }
    public Comparator<CensusDAO> getCurrentSort(String field) {

        Comparator<CensusDAO> comparator = null;
        switch (field) {
            case "population":
                comparator = Comparator.comparing( census -> census.population );
                break;
            case "density":
                comparator = Comparator.comparing( census -> census.populationDensity );
                break;
            case "area":
                comparator = Comparator.comparing( census -> census.totalArea );
                break;
            case "state":
                comparator = Comparator.comparing( census -> census.state );
                break;
            case "statecode":
                comparator = Comparator.comparing( census -> census.stateCode );
                break;
        }
        return comparator;
    }

    public <E> void getCensusObject(Class<E> censusClass, Map censusMap, Object censusCSV) {
        try {
            Field field = censusClass.getDeclaredField( "state" );
            Class<?> censusDAOClass = Class.forName( "censusanalyser.dao.CensusDAO" );
            Constructor<?> censusConstructor = censusDAOClass.getConstructor( censusClass );
            String value = (String) field.get( censusCSV );
            censusMap.put( value, censusConstructor.newInstance( censusCSV ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
