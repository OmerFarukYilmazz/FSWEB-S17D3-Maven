package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validations.ZooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer,Koala> koalas;

    @PostConstruct
    public void init(){
        this.koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAll(){
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala find(@PathVariable("id") Integer id){
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKoalaExistence(koalas,id,true);
        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala){
        ZooKangarooValidation.checkKoalaExistence(koalas, koala.getId(),false);

        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id,@RequestBody Koala koala){
        ZooKangarooValidation.isIdValid(id);

        koala.setId(id);
        if(koalas.containsKey(id)){
            koalas.put(id,koala);
            return koalas.get(id);
        }
        else{
            return save(koala);
        }
    }

    @DeleteMapping("{id}")
    public Koala delete(@PathVariable Integer id){
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKoalaExistence(koalas,id,true);
        return koalas.remove(id);
    }
}
