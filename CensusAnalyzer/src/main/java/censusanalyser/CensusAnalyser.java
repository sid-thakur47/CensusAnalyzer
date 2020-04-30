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
            Iterator<IndiaCensusCSV> censusCSVIterator = getCSVFileIterator( reader, IndiaCensusCSV.class );
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int namOfEateries = (int) StreamSupport.stream( csvIterable.spliterator(), false ).count();
            return namOfEateries;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            Iterator<IndianState> censusCSVIterator = getCSVFileIterator( reader, IndianState.class );
            Iterable<IndianState> csvIterable = () -> censusCSVIterator;
            int namOfStates = (int) StreamSupport.stream( csvIterable.spliterator(), false ).count();
            return namOfStates;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>( reader );
        csvToBeanBuilder.withType( csvClass );
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }
}