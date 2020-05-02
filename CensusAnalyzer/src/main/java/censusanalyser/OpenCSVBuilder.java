package censusanalyser;

import CSVBuilderException.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements CSVBuilderInterface {

    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) {
        return this.getCSVBean( reader, csvClass ).iterator();
    }
    @Override
    public List getCSVFileList(Reader reader, Class csvClass) {
        return this.getCSVBean( reader, csvClass ).parse();
    }
    private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder( reader );
            csvToBeanBuilder.withType( csvClass );
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
            return csvToBeanBuilder.build();
        } catch (Exception e) {
            throw new CSVBuilderException( e.getMessage(),
                    CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }
}