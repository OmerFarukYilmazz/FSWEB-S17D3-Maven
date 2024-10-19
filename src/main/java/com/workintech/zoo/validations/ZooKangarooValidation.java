package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKangarooValidation {
    public static void isIdValid(Integer id){
        if(id == null || id<0){
            throw new ZooException("Id isn't valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean existence) {
        if(existence){
            if(!kangaroos.containsKey(id)){
                throw new ZooException("Record isn't exist: " + id, HttpStatus.NOT_FOUND);
            }
        }
        else{
            if(kangaroos.containsKey(id)){
                throw new ZooException("Record is already exist: " + id , HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKangarooWeight(Double weight) {
        if(weight == null || weight <=0){
            throw new ZooException("Weight shouldn't be null or less than zero"+ weight ,HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKoalaExistence(Map<Integer, Koala> koalas, Integer id, boolean existence) {
        if(existence){
            if(!koalas.containsKey(id)){
                throw new ZooException("Record isn't exist: " + id, HttpStatus.NOT_FOUND);
            }
        }
        else{
            if(koalas.containsKey(id)){
                throw new ZooException("Record is already exist: " + id , HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKoalaSleepHour(Double sleepHour) {
        if(sleepHour == null || sleepHour <=0 || sleepHour>48){
            throw new ZooException("SleepHour shouldn't be null, less than zero or more than 48 hours"+ sleepHour ,HttpStatus.BAD_REQUEST);
        }
    }
}

