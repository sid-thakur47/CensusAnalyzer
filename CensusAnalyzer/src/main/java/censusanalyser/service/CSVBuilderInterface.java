package censusanalyser.service;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface CSVBuilderInterface<E> {
    Iterator<E> getCSVFileIterator(Reader reader, Class csvClass);
    List<E> getCSVFileList(Reader reader, Class csvClass);

}
