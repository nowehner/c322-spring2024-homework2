package com.example.c322spring2024homework2.controllers;


import com.example.c322spring2024homework2.model.Builder;
import com.example.c322spring2024homework2.model.Guitar;
import com.example.c322spring2024homework2.model.Type;
import com.example.c322spring2024homework2.model.Wood;
import com.example.c322spring2024homework2.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//banggggg
@CrossOrigin
public class InventoryController {

    private InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }
    @GetMapping("/search")
    public List<Guitar> search(@RequestParam(required = false) String serialNumber,
                               @RequestParam(required = false) Double price,
                               @RequestParam(required = false) String builder,
                               @RequestParam(required = false) String model,
                               @RequestParam(required = false) String type,
                               @RequestParam(required = false) String backWood,
                               @RequestParam(required = false) String topWood){

        Builder builderEnum;
        boolean builderIsDefined = false;
        if(builder != null){
            builderEnum = Builder.valueOf(builder.toUpperCase());
            builderIsDefined = true;
        }else {
            builderEnum = null;
        }

        Type typeEnum;
        boolean typeIsDefined = false;
        if(type != null){
            typeEnum = Type.valueOf(type.toUpperCase());
            typeIsDefined = true;
        }else {
            typeEnum = null;
        }

        Wood backWoodEnum;
        boolean backwoodIsDefined = false;
        if(backWood != null){
            backWoodEnum = Wood.valueOf(backWood.toUpperCase());
            backwoodIsDefined = true;
        }else {
            backWoodEnum = null;

        }

        Wood topWoodEnum;
        boolean topwoodIsDefined = false;
        if(topWood != null){
            topWoodEnum = Wood.valueOf(topWood.toUpperCase());
            topwoodIsDefined = true;
        }else{
            topWoodEnum = null;
        }

        Guitar guitar = new Guitar(serialNumber, price == null ? 0 : price, builderIsDefined ? builderEnum : null, model, typeIsDefined ? typeEnum : null, backwoodIsDefined ? backWoodEnum : null, topwoodIsDefined ? topWoodEnum : null);
        System.out.println("ran here");
        return inventoryRepository.search(guitar);
    }


    @PostMapping("/add")
    public boolean add(@RequestBody Guitar data){
        System.out.println("add attempted");
        System.out.println(data.getBackWood());
        try{
            return inventoryRepository.addGuitar(data);
        } catch(IOException e){
            return false;
        }
    }

    @GetMapping("/find/{serialNumber}")
    public Guitar findGuitar(@PathVariable String serialNumber){
        try {
            return inventoryRepository.getGuitar(serialNumber);
        } catch (IOException e) {
            return null;
        }
    }

}
