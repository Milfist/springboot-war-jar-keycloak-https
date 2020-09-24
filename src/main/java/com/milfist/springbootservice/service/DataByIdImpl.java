package com.milfist.springbootservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class DataByIdImpl implements DataById {

  @Value("${service.options.path}")
  private String optionsPath;

  @Override
  public String getOptionsById(String id) throws IOException {
    byte[] optionsById = FileCopyUtils.copyToByteArray(getInputStreamFromResourceById(id));
    return new String(optionsById, StandardCharsets.UTF_8);
  }

  private InputStream getInputStreamFromResourceById(String id) throws IOException {
    Resource resource = new ClassPathResource(String.format("%s%s.json", optionsPath, id));
    return resource.getInputStream();
  }
}
