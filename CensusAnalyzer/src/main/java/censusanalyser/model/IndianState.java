package censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndianState  {

    @CsvBindByName(column = "State Name", required = true)
    public String state;
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndianState{" +
                "state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
