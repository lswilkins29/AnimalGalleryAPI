package com.example.CSC_340.Practice.API.Cat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cats") 
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long CatId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  private String breed;
  private Double age;
 


public Cat()  {
}

public Cat(Long catId, String name, String description, String breed, Double age) {
  CatId = catId;
  this.name = name;
  this.description = description;
  this.breed = breed;
  this.age = age;
}

public Cat(String name, String description, String breed, Double age) {
  this.name = name;
  this.description = description;
  this.breed = breed;
  this.age = age;
}

public Long getCatId() {
  return CatId;
}

public void setCatId(Long catId) {
  CatId = catId;
}

public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public String getDescription() {
  return description;
}

public void setDescription(String description) {
  this.description = description;
}

public String getBreed() {
  return breed;
}

public void setBreed(String breed) {
  this.breed = breed;
}

public Double getAge() {
  return age;
}

public void setAge(Double age) {
  this.age = age;
}

}