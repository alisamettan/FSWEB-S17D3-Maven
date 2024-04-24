package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas=new HashMap<>();

        koalas.put(1,new Koala(1,"Koala",5,60.0,"female"));
    }

    @GetMapping
    public List<Koala> getKoala(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoala(@PathVariable int id){
        if(!koalas.containsKey(id)){
             throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }
        return koalas.get(id);
    }

    @PostMapping()
    public Koala saveKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id,@RequestBody Koala koala){

        if(!koalas.containsKey(id)){
            throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }
        koalas.put(id,new Koala(koala.getId(),koala.getName(),koala.getWeight(),koala.getSleepHour(),koala.getGender()));
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKangaroo(@PathVariable int id){
        if(!koalas.containsKey(id)){
            throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }
        Koala koala=koalas.get(id);
        koalas.remove(id,koala);
        return koala;
    }

}
