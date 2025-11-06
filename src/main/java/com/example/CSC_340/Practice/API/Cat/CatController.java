package com.example.CSC_340.Practice.API.Cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // MVC Controller
public class CatController {

    @Autowired
    private CatService CatService;

    /**
     * Endpoint to get all cats
     *
     * @return List of all cats
     */
    @GetMapping({"/cats", "/cats/"})
    public Object getAllCats(Model model) {
        //return CatService.getAllCats();
        model.addAttribute("catsList", CatService.getAllCats());
        model.addAttribute("title", "All Cats");
        return "cats-list"; // view name
    }

    /**
     * Endpoint to get a cat by ID
     *
     * @param id The ID of the cat to retrieve
     * @return The cat with the specified ID
     */
    @GetMapping("/cats/{id}")
    public String getCatById(@PathVariable long id, Model model) {
        //return CatService.getCatById(id);
        model.addAttribute("cat", CatService.getCatById(id));
        model.addAttribute("title", "Cat #: " + id);
        return "cat-details";
    }

    /**
     * Endpoint to get cats by name
     *
     * @param name Optional name substring to search for (contains, case-insensitive). If omitted returns all cats.
     * @return List of cats matching the criteria
     */
    @GetMapping("/cats/name")
    public Object getCatsByName(@RequestParam String key, Model model) {
        if (key != null) {
            model.addAttribute("catsList", CatService.getCatsByName(key));
            model.addAttribute("title", "Cats By Name: " + key);
            return "cats-list";
        } else {
         return "redirect:/cats/";
        }
    }

    

    /**
     * Endpoint to get cats by description
     *
     * @param description The description to search for
     * @return List of cats with the specified description
     */
    @GetMapping("/cats/description/{description}")
    public Object getCatsByDescription(@PathVariable String description, Model model) {
        //return CatService.getCatsByDescription(description);
        model.addAttribute("catsList", CatService.getCatsByDescription(description));
        model.addAttribute("title", "Cats By Description: " + description);
        return "cats-list";
    }

    /**
     * Endpoint to get cats by breed
     *
     * @param breed The breed to search for
     * @return List of cats with the specified breed
     */
    @GetMapping("/cats/breed")
    public Object getCatsByBreed(@RequestParam(name = "breed", defaultValue = "Domestic") String breed, Model model) {
        //return new ResponseEntity<>(CatService.getCatsByBreed(breed), HttpStatus.OK);
        model.addAttribute("catsList", CatService.getCatsByBreed(breed));
        model.addAttribute("title", "Cats By Breed: " + breed);
        return "cats-list";
    }


    /**
    * Endpoint to show the create form for a new cat
    *
    * @param model The model to add attributes to
    * @return The view name for the create form
    */
    @GetMapping("/cats/createForm")
    public Object showCreateForm(Model model) {
        Cat cat = new Cat();
        model.addAttribute("cat", cat);
        model.addAttribute("title", "Create New Cat");
        return "cats-create";
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

    @GetMapping("/cats/search")
    public ResponseEntity<?> searchCatsByName(@RequestParam(name = "name", required = false) String name) {
    Object results = CatService.getCatsByName(name);
    return ResponseEntity.ok(results);
    }


    @GetMapping("/cats/category/{category}")
    public ResponseEntity<?> getByCategory(
    @PathVariable String category,
    @RequestParam(name = "value") String value) {
        switch (category.toLowerCase()) {
        case "breed":
                return ResponseEntity.ok(CatService.getCatsByBreed(value));
        case "age":
            try {
                Double ageVal = Double.valueOf(value);
                    return ResponseEntity.ok(CatService.getCatsByAge(ageVal));
                } catch (NumberFormatException ex) {
                    return ResponseEntity.badRequest().body("Invalid age value: must be a number");
                }
        case "description":
            return ResponseEntity.ok(CatService.getCatsByDescription(value));
        case "name":
            return ResponseEntity.ok(CatService.getCatsByName(value));
        default:
            return ResponseEntity
            .badRequest()
            .body("Unsupported category. Supported: name, breed, age, description");
        }
    }
    
    /**
     * Endpoint to add a new cat
     *
     * @param cat The cat to add
     * @return List of all cats
     */
        @PostMapping("/cats")
        public Object addCat(@RequestBody Cat cat) {
        Cat newCat = CatService.addCat(cat);
        return "redirect:/cats/" + newCat.getCatId();
    }

  /**
   * Endpoint to show the update form for a cat
   *
   * @param id    The ID of the cat to update
   * @param model The model to add attributes to
   * @return The view name for the update form
   */
  @GetMapping("/cats/updateForm/{id}")
  public Object showUpdateForm(@PathVariable Long id, Model model) {
    Cat cat = CatService.getCatById(id);
    model.addAttribute("cat", cat);
    model.addAttribute("title", "Update Cat: " + id);
    return "cats-update";
  }

  /**
   * Endpoint to update a cat
   *
   * @param id  The ID of the cat to update
   * @param cat The updated cat information
   * @return The updated cat
   */
  // @PutMapping("/cats/{id}")
    @PostMapping("/cats/update/{id}")
    public Object handleUpdateForm(@PathVariable Long id, Cat cat) {
        CatService.updateCat(id, cat);
        return "redirect:/cats/" + id;
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
  // @DeleteMapping("/cats/{id}")
  @GetMapping("/cats/delete/{id}")
  public Object deleteCat(@PathVariable Long id) {
    CatService.deleteCat(id);
    return "redirect:/cats";
  }




    /**
   * Endpoint to write a cat to a JSON file
   *
   * @param cat The cat to write
   * @return An empty string indicating success
   */
  @PostMapping("/cats/writeFile")
  public Object writeJson(@RequestBody Cat cat) {
    return CatService.writeJson(cat);
  }

  /**
   * Endpoint to read a JSON file and return its contents
   *    
   * @return The contents of the JSON file
   */
  @GetMapping("/cats/readFile")
  public Object readJson() {
    return CatService.readJson();

  }

}
