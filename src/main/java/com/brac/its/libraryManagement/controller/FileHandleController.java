package com.brac.its.libraryManagement.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brac.its.libraryManagement.storage.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Log4j2
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

  //@CrossOrigin(origins = "https://idoumu.csb.app", allowedHeaders = "*")
  @PostMapping("/file/upload")
  public ResponseEntity<Boolean> handleFileUpload(@RequestParam("file") MultipartFile file) {
    log.info(file.getOriginalFilename());
    try {
      List<String> fileNames = new ArrayList<>();
      fileNames.add(file.getOriginalFilename());
      storageService.store(file);
      return ResponseEntity.ok(true);
    }catch (Exception e){
      return ResponseEntity.ok(false);
    }
    /*redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");*/

    //return file;
  }

  @PostMapping("/file/upload/multiple")
  public ResponseEntity<Boolean> handleMultipleFileUpload(@RequestParam("files") List<MultipartFile> files) {
    //log.info(file.getOriginalFilename());
    try {
      List<String> fileNames = new ArrayList<>();
      files.forEach(x -> {
        storageService.store(x);
        fileNames.add(x.getOriginalFilename());
      });
      return ResponseEntity.ok(true);
    }catch (Exception e){
      return ResponseEntity.ok(false);
    }
    /*redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");*/

    //return file;
  }

}
