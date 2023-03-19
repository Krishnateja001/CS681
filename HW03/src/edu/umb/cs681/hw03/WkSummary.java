package edu.umb.cs681.hw03;

import java.util.*;

public class WkSummary{
    double open;
    double close;
    double high;
    double low;

    // constructor for assigning values
    WkSummary(Double open, Double close, Double high, Double low){
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }

    double getOpen() {

        return open;
    }
    double getClose() {

        return close;
    }
    double getHigh() {

        return high;
    }
    double getLow() {

        return low;
    }
}

