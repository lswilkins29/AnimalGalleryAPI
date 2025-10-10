package com.example.CSC_340.Practice.API.Cat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findByName(String name);

    // Find cats whose name contains the given string (case-insensitive)
    List<Cat> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Cat c WHERE c.age = ?1")
    List<Cat> findByAge(Double age);

    @Query("SELECT c FROM Cat c WHERE c.breed = ?1")
    List<Cat> findByBreed(String breed);

    @Query("SELECT c FROM Cat c WHERE c.description = ?1")
    List<Cat> findByDescription(String description);

}