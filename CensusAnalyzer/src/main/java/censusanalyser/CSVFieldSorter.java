package censusanalyser;

import java.util.Comparator;

public class CSVFieldSorter {

    public Comparator<IndiaCensusDAO> getCurrentSort(String field) {

        Comparator<IndiaCensusDAO> comparator = null;
        switch (field) {
            case "population":
                comparator = Comparator.comparing( census -> census.population );
                break;
            case "density":
                comparator = Comparator.comparing( census -> census.densityPerSqKm );
                break;
            case "area":
                comparator = Comparator.comparing( census -> census.areaInSqKm );
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
}
