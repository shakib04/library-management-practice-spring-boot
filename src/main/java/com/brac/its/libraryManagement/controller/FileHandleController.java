package com.brac.its.libraryManagement.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileHandleController {

  @GetMapping(
      value = "/2_Leave_Policy_v2.pdf",
      produces = MediaType.APPLICATION_PDF_VALUE
  )
  public @ResponseBody
  byte[] getImageWithMediaType() throws IOException {
    InputStream in = getClass().getClassLoader()
        .getResourceAsStream("static/2_Leave_Policy_v2.pdf");
    return org.apache.commons.io.IOUtils.toByteArray(in);
  }
}
