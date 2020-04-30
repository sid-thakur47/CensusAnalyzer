package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianState {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
}