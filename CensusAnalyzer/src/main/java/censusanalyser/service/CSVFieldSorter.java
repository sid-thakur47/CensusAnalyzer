package censusanalyser.service;

import censusanalyser.dao.IndiaCensusDAO;

import java.util.Comparator;

public class CSVFieldSorter {

    public Comparator<IndiaCensusDAO> getCurrentSort(String field) {

        Comparator<IndiaCensusDAO> comparator = null;
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
}
