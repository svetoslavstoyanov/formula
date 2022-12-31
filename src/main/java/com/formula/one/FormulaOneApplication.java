package com.formula.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormulaOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormulaOneApplication.class, args);
    }
//  TODO: fix didNotFinished - assign all drivers that left based on random int. Maybe rename that property to finished
//  TODO: fix edits - omit sets if null values
//  TODO: update teams winsCount, pointsCount on finished race
//  TODO: create annotation(maybe) in order to share pageable method functionallity in controller
//  TODO: read https://graphql.org/learn/ in order to optimize schema. If possible separate it to smaller files

//  TODO*: error handling
//  TODO*: handle more precise business logic around validations
//  TODO*: security
//  TODO*: sockets

//  TODO**: add status for race? READY, RUNNING, FINISHED
//  TODO**: add years
//  TODO**: add stints for race info for drivers per race
//  TODO**: add delete race

//  TODO***: filters queries
}
