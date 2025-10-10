package com.example.CSC_340.Practice.API.Cat;

import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    /**
     * Method to get all cats
     *
     * @return List of all cats
     */
    public Object getAllCats() {
        return catRepository.findAll();
    }

    /**
     * Method to get a cat by ID
     *
     * @param catId The ID of the cat to retrieve
     * @return The cat with the specified ID
     */
    public Cat getCatById(@PathVariable long catId) {
        return catRepository.findById(catId).orElse(null);
    }

    /**
     * Method to get cats by name
     *
     * @param name The name of the cat to search for
     * @return List of cats with the specified name
     */
    public Object getCatsByName(String name) {
        if (name == null || name.isBlank()) {
            return catRepository.findAll();
        }
        return catRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Method to get cats by description
     *
     * @param description The description to search for
     * @return List of cats with the specified description
     */
    public Object getCatsByDescription(String description) {
        return catRepository.findByDescription(description);
    }

    /**
     * Method to get cats by age
     *
     * @param age The age to search for
     * @return List of cats with the specified age
     */
    public Object getCatsByAge(Double age) {
        return catRepository.findByAge(age);
    }

    /**
     * Method to get cats by breed
     *
     * @param breed The breed to search for
     * @return List of cats with the specified breed
     */
    public Object getCatsByBreed(String breed) {
        return catRepository.findByBreed(breed);
    }

    /**
     * Method to add a new cat
     *
     * @param cat The cat to add
     */
    public Cat addCat(Cat cat) {
        return catRepository.save(cat);
    }

    /**
     * Method to update a cat
     *
     * @param catId The ID of the cat to update
     * @param cat   The updated cat information
     */
    public Cat updateCat(Long catId, Cat cat) {
        cat.setCatId(catId);
        return catRepository.save(cat);
    }

    /**
     * Method to delete a cat
     *
     * @param catId The ID of the cat to delete
     */
    public void deleteCat(Long catId) {
        catRepository.deleteById(catId);
    }

    /**
   * Method to write a cat object to a JSON file
   *
   * @param cat The cat object to write
   */
  public String writeJson(Cat cat) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(new File("cats.json"), cat);
      return "Cat written to JSON file successfully";
    } catch (IOException e) {
      e.printStackTrace();
      return "Error writing cat to JSON file";
    }

  }

  /**
   * Method to read a cat object from a JSON file
   *
   * @return The cat object read from the JSON file
   */
  public Object readJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File("cats.json"), Cat.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }
}
