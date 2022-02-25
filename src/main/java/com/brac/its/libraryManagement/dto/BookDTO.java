package com.brac.its.libraryManagement.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Integer id;
    private String name;
    private String author;
    private int copies;

    private int systemUserId;
    private String systemUserName;
    private String systemUserEmail;

    private Long publisherId;
    private String publisherName;
    private String publisherAddress;
    private String publisherGovtLicence;
}
