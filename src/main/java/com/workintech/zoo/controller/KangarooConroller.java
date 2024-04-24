package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooConroller {
    private Map<Integer, Kangaroo> kangaroos;


    @PostConstruct
    public void init(){
        kangaroos=new HashMap<>();
        kangaroos.put(1,new Kangaroo(1,"kanguru",3.55,150.50,"male",true));
    }

    @GetMapping
    public List<Kangaroo> getKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangaroo(@PathVariable int id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }

        return kangaroos.get(id);
    }

    @PostMapping()
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo){
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id,@RequestBody Kangaroo kangaroo){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(id,new Kangaroo(kangaroo.getId(),kangaroo.getName(),kangaroo.getHeight(),kangaroo.getWeight(),kangaroo.getGender(),kangaroo.isAggressive()));
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("id does not exist:"+id,HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be greater than 0.",HttpStatus.BAD_REQUEST);
        }
        Kangaroo kangaroo=kangaroos.get(id);
        kangaroos.remove(id,kangaroo);
        return kangaroo;
    }

}
