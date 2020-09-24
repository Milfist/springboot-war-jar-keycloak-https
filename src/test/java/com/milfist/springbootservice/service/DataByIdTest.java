package com.milfist.springbootservice.service;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
class DataByIdTest {

  @Autowired
  DataById dataById;

  @Test
  void givenCorrectID_call_getOptionsById_thenReturn_OK() throws Exception {
      String json = dataById.getDataById("data");
      Data data = new Gson().fromJson(json, Data.class);

      assertAll("data",
          ()-> assertEquals("Servicio", data.getTitle()),
          ()-> assertEquals(0, data.getOptions().size())
      );
  }

  @Test
  void givenWrongID_call_getOptionsById_thenReturn_FileNorFoundException() {
    assertThrows(FileNotFoundException.class, () -> dataById.getDataById("data2"));
  }

  @Test
  void givenCorrectID_withEmptyFile_thenReturnEmptyString() throws Exception {
    String json = dataById.getDataById("data_null");
    assertEquals("", json);
  }

}

@lombok.Data
class Data {
  private String title;
  private List<String> options;
  private Object security;
}
