package censusanalyser.dao;

import censusanalyser.pojo.IndiaCensusCSV;
import censusanalyser.pojo.IndianState;
import censusanalyser.pojo.USCensusCSV;

public class IndiaCensusDAO {

    public String state;
    public Integer population;
    public String stateCode;
    public double totalArea;
    public double populationDensity;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {

        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }
    public IndiaCensusDAO(IndianState indiaState) {
        state = indiaState.state;
        stateCode = indiaState.stateCode;
    }
    public IndiaCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        totalArea = usCensusCSV.totalArea;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        stateCode=usCensusCSV.stateId;
    }
}
