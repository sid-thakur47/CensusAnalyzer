package censusanalyser.dao;

import censusanalyser.model.IndiaCensusCSV;
import censusanalyser.model.IndianState;
import censusanalyser.model.USCensusCSV;

public class CensusDAO {

    public String state;
    public int population;
    public String stateCode;
    public double totalArea;
    public double populationDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {

        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }
    public CensusDAO(IndianState indiaState) {
        state = indiaState.state;
        stateCode = indiaState.stateCode;
    }
    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        totalArea = usCensusCSV.totalArea;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        stateCode = usCensusCSV.stateId;
    }
}
