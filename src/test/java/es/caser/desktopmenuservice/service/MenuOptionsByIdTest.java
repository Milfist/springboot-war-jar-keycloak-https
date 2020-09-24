package es.caser.desktopmenuservice.service;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
class MenuOptionsByIdTest {

  @Autowired
  MenuOptionsById menuOptionsById;

  @Test
  void givenCorrectID_call_getOptionsById_thenReturn_OK() throws Exception {
      String json = menuOptionsById.getOptionsById("menu");
      Menu menu = new Gson().fromJson(json, Menu.class);

      assertAll("menu",
          ()-> assertEquals("General", menu.getTitle()),
          ()-> assertEquals(0, menu.getOptions().size())
      );
  }

  @Test
  void givenWrongID_call_getOptionsById_thenReturn_FileNorFoundException() {
    assertThrows(FileNotFoundException.class, () -> menuOptionsById.getOptionsById("menu2"));
  }

  @Test
  void givenCorrectID_withEmptyFile_thenReturnEmptyString() throws Exception {
    String json = menuOptionsById.getOptionsById("menu_null");
    assertEquals("", json);
  }

}

@Data
class Menu {
  private String title;
  private List<String> options;
  private Object security;
}
