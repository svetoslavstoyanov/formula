package com.formula.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormulaOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormulaOneApplication.class, args);
    }
//  TODO: mock generate races
//  TODO: mock finish race
//  TODO: update teams winsCount, pointsCount on finished race
//  TODO: query/mutations for generating data

//  TODO*: error handling
//  TODO*: handle more precise business logic around validations
//  TODO*: security

//  TODO**: add status for race? READY, RUNNING, FINISHED
//  TODO**: add years
//  TODO**: add stints for race info for drivers per race
//  TODO**: add delete race

//  TODO***: filters queries
}
