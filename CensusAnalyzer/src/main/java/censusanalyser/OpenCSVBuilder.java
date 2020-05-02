package censusanalyser;

import CSVBuilderException.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder<E> implements CSVBuilderInterface {

    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder( reader );
            csvToBeanBuilder.withType( csvClass );
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (Exception e) {
            throw new CSVBuilderException( e.getMessage(),
                    CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
}