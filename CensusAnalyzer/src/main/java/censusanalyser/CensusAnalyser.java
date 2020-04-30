package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            Iterator<IndiaCensusCSV> censusCSVIterator = this.getCsvFileIterator( reader, IndiaCensusCSV.class );
            Iterable<IndiaCensusCSV> csvIterable = () -> (Iterator<IndiaCensusCSV>) censusCSVIterator;
            int numOfEnteries = (int) StreamSupport.stream( csvIterable.spliterator(), false ).count();
            return numOfEnteries;
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
    public int loadIndianStateCodeData(String indiaStateCsvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( indiaStateCsvFilePath ) );
            Iterator<IndianState> stateCSVIterator = this.getCsvFileIterator( reader, IndianState.class );
            Iterable<IndianState> csvIterable = () -> stateCSVIterator;
            int numOfEnteries = (int) StreamSupport.stream( csvIterable.spliterator(), false ).count();
            return numOfEnteries;
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    private <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>( reader );
        csvToBeanBuilder.withType( csvClass );
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }
}