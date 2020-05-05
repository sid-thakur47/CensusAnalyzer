package censusanalyser;

public class IndiaCensusDAO {

    public String state;
    public Integer areaInSqKm;
    public Integer densityPerSqKm;
    public Integer population;
    public String stateCode;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {

        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }
    public IndiaCensusDAO(IndianState indiaState) {
        state = indiaState.state;
        stateCode = indiaState.stateCode;
    }
}
