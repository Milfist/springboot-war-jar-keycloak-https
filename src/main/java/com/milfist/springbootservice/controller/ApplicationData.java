package com.milfist.springbootservice.controller;

import com.milfist.springbootservice.service.DataById;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApplicationData {

  private final DataById menuOptionsById;

  public ApplicationData(DataById menuOptionsById) {
    this.menuOptionsById = menuOptionsById;
  }

  @GetMapping(path = "/data/{id}", produces = { "application/json; charset=UTF-8" }, name = "getOptionsById")
  public ResponseEntity<String> getMenuOptionsById(@PathVariable String id) throws IOException {
    String menuOptions = menuOptionsById.getOptionsById(id);
    return menuOptions.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(menuOptions, HttpStatus.OK);
  }

}

