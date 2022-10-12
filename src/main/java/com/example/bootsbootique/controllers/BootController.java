package com.example.bootsbootique.controllers;


import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Optional;

import com.example.bootsbootique.entities.Boot;
import com.example.bootsbootique.enums.BootType;
import com.example.bootsbootique.exceptions.NotImplementedException;
import com.example.bootsbootique.exceptions.QueryNotSupportedException;
import com.example.bootsbootique.repositories.BootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/boots")
public class BootController {

    private BootRepository bootRepository;

    @Autowired
    public BootController(BootRepository bootRepository) {
        this.bootRepository = bootRepository;
    }

    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        return bootRepository.findAll();
    }

    @GetMapping("/types")
    public List<BootType> getBootTypes() {
        return Arrays.asList(BootType.values());
    }

    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        return bootRepository.save(boot);
    }

    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {
        if(bootRepository.existsById(id)){
            Boot deleted = bootRepository.findById(id).get();
            bootRepository.deleteById(id);
            return deleted;
        }
        return null;
    }

    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        if(bootRepository.existsById(id)){
            Boot incBoot = bootRepository.findById(id).get();
            incBoot.setQuantity(incBoot.getQuantity()+1);
            bootRepository.save(incBoot);
            return incBoot;
        }
        return null;
    }

    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        if(bootRepository.existsById(id)){
            Boot incBoot = bootRepository.findById(id).get();
            incBoot.setQuantity(incBoot.getQuantity()-1);
            bootRepository.save(incBoot);
            return incBoot;
        }
        return null;
    }

    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) throws QueryNotSupportedException {
        if (Objects.nonNull(material)) {
            if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, type, size, and minimum
                // quantity
                return bootRepository.findBootBySizeAndMaterialAndTypeAndQuantityAfter(size,material,type,minQuantity);
            } else if (Objects.nonNull(type) && Objects.nonNull(size)) {
                // call the repository method that accepts a material, size, and type
                return bootRepository.findBootByMaterialAndSizeAndType(material,size,type);
            } else if (Objects.nonNull(type) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, a type, and a minimum
                // quantity
                return bootRepository.findBootByMaterialAndTypeAndQuantityAfter(material,type,minQuantity);
            } else if (Objects.nonNull(type)) {
                // call the repository method that accepts a material and a type
                return bootRepository.findBootByMaterialAndType(material,type);
            } else {
                // call the repository method that accepts only a material
                return bootRepository.findBootByMaterial(material);
            }
        } else if (Objects.nonNull(type)) {
            if (Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type, size, and minimum quantity
                return bootRepository.findBootByTypeAndSizeAndQuantityAfter(type,size,minQuantity);
            } else if (Objects.nonNull(size)) {
                // call the repository method that accepts a type and a size
                return bootRepository.findBootByTypeAndSize(type,size);
            } else if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type and a minimum quantity
                return bootRepository.findBootByTypeAndQuantityAfter(type,minQuantity);
            } else {
                // call the repository method that accept only a type
                return bootRepository.findBootByType(type);
            }
        } else if (Objects.nonNull(size)) {
            if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a size and a minimum quantity
                return bootRepository.findBootBySizeAndQuantityAfter(size,minQuantity);
            } else {
                // call the repository method that accepts only a size
                return bootRepository.findBootBySize(size);
            }
        } else if (Objects.nonNull(minQuantity)) {
            // call the repository method that accepts only a minimum quantity
            return bootRepository.findBootByQuantityAfter(minQuantity);
        } else {
            return bootRepository.findAll();
        }
    }

}