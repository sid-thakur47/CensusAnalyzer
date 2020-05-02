package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianState extends IndiaDetails {

    @CsvBindByName(column = "State Name", required = true)
    public String state;
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    @Override
    public String getState() {
        return state;
    }
    @Override
    public String toString() {
        return "IndianState{" +
                "state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
