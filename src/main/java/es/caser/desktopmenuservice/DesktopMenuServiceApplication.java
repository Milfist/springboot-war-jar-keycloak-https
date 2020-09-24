package es.caser.desktopmenuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DesktopMenuServiceApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(DesktopMenuServiceApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(DesktopMenuServiceApplication.class, args);
  }
}
