package com.example.CSC_340.Practice.API.Cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    @Autowired
    private CatService CatService;

    /**
     * Endpoint to get all cats
     *
     * @return List of all cats
     */
    @GetMapping("/cats")
    public Object getAllCats() {
        return CatService.getAllCats();
    }

    /**
     * Endpoint to get a cat by ID
     *
     * @param id The ID of the cat to retrieve
     * @return The cat with the specified ID
     */
    @GetMapping("/cats/{id}")
    public Cat getCatById(@PathVariable long id) {
        return CatService.getCatById(id);
    }

    /**
     * Endpoint to get cats by name
     *
     * @param name The name of the cat to search for
     * @return List of cats with the specified name
     */
    @GetMapping("/cats/name")
    public Object getCatsByName(@RequestParam String key) {
        if (key != null) {
            return CatService.getCatsByName(key);
        } else {
            return CatService.getAllCats();
        }

    }

    /**
     * Endpoint to get cats by description
     *
     * @param description The description to search for
     * @return List of cats with the specified description
     */
    @GetMapping("/cats/description/{description}")
    public Object getCatsByDescription(@PathVariable String description) {
        return CatService.getCatsByDescription(description);
    }

    /**
     * Endpoint to get cats by breed
     *
     * @param breed The breed to search for
     * @return List of cats with the specified breed
     */
    @GetMapping("/cats/breed")
    public Object getCatsByBreed(@RequestParam(name = "breed", defaultValue = "Domestic") String breed) {
        return new ResponseEntity<>(CatService.getCatsByBreed(breed), HttpStatus.OK);
    }

    /**
     * Endpoint to get cats by age
     *
     * @param age The age to search for
     * @return List of cats with the specified age
     */
    @GetMapping("/cats/age")
    public Object getCatsByAge(@RequestParam(name = "age", defaultValue = "1") Double age) {
        return new ResponseEntity<>(CatService.getCatsByAge(age), HttpStatus.OK);
    }

    /**
     * Endpoint to add a new cat
     *
     * @param cat The cat to add
     * @return List of all cats
     */
    @PostMapping("/cats")
    public Object addCat(@RequestBody Cat cat) {
        return CatService.addCat(cat);
    }

    /**
     * Endpoint to update a cat
     *
     * @param id  The ID of the cat to update
     * @param cat The updated cat information
     * @return The updated cat
     */
    @PutMapping("/cats/{id}")
    public Cat updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        CatService.updateCat(id, cat);
        return CatService.getCatById(id);
    }

    /**
     * Endpoint to delete a cat
     *
     * @param id The ID of the cat to delete
     * @return List of all cats
     */
    @DeleteMapping("/cats/{id}")
    public Object deleteCat(@PathVariable Long id) {
        CatService.deleteCat(id);
        return CatService.getAllCats();
    }
}