package com.example.bootsbootique.repositories;

import com.example.bootsbootique.entities.Boot;
import com.example.bootsbootique.enums.BootType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BootRepository extends JpaRepository<Boot,Integer> {

    List<Boot> findBootBySize(float size);
    List<Boot> findBootByMaterial(String material);
    List<Boot> findBootByType(BootType type);
    List<Boot> findBootByQuantityAfter(int minQuantity);

    List<Boot> findBootBySizeAndMaterialAndTypeAndQuantityAfter(float size,String material,BootType type,int minQuantity);

    List<Boot> findBootByMaterialAndSizeAndType(String material,float size,BootType type);

    List<Boot> findBootByMaterialAndTypeAndQuantityAfter(String material,BootType type,int minQuantity);

    List<Boot> findBootByMaterialAndType(String material,BootType type);

    List<Boot> findBootByTypeAndSizeAndQuantityAfter(BootType type, float size, int minQuantity);

    List<Boot> findBootByTypeAndQuantityAfter(BootType type, int minQuantity);
    List<Boot> findBootByTypeAndSize(BootType type, float size);

    List<Boot> findBootBySizeAndQuantityAfter(float size, int q);


}
