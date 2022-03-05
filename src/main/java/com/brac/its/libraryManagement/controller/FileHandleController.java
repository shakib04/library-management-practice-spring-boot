package com.brac.its.libraryManagement.controller;

import java.io.IOException;
import java.io.InputStream;

import com.brac.its.libraryManagement.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileHandleController {

  @Autowired
  private StorageService storageService;

  @Autowired
  /*public FileHandleController(StorageService storageService) {
    this.storageService = storageService;
  }*/

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

  @CrossOrigin(origins = "https://idoumu.csb.app", allowedHeaders = "*")
  @PostMapping("/file/upload")
  public MultipartFile handleFileUpload(@RequestParam("file") MultipartFile file) {

    storageService.store(file);
    /*redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");*/

    return file;
  }
}
