package com.coding_hustle.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherResponse{


    private Current current;



    @Getter
    @Setter
    public class Current{

        private int temperature;

        private List<String> weather_descriptions;

        private int feelslike;

    }





}





