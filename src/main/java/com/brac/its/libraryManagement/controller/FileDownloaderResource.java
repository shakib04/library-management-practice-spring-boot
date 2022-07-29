package com.brac.its.libraryManagement.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;

import java.net.URL;

@RestController
public class FileDownloaderResource {

    @GetMapping(path = "/your-api/get-video-file")
    public ResponseEntity<InputStreamResource> downloadFile() throws Exception {
        //File downloadFile = new File("src/main/resources/static/1. Introduction to Validation and Constraints with Spring MVC.xlms");
        File downloadFile = new File("http://ipv4.download.thinkbroadband.com/20MB.zip");
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(downloadFile));
        InputStreamResource resource = new InputStreamResource(new URL("http://212.183.159.230/5MB.zip").openStream());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFile.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(downloadFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @RequestMapping(path = "/downloadLargeFile2", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> downloadLargeFile2() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final File file = new File("src/main/resources/static/1. Introduction to Validation and Constraints with Spring MVC.xlms");
        final FileSystemResource resource = new FileSystemResource(file);
        httpHeaders.set(HttpHeaders.LAST_MODIFIED, String.valueOf(file.lastModified()));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
