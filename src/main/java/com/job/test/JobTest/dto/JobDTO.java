package com.job.test.JobTest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "JOB")
public class JobDTO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "COMPANY_NAME")
    @JsonProperty("company_name")
    private String companyName;

    @Column(name = "TITLE")
    private String title;

    @Column(columnDefinition = "TEXT", name = "DESCRIPTION")
    private String description;

    @Column(name = "REMOTE")
    private boolean remote;

    @Column(name = "URL")
    private String url;

    @ElementCollection
    @Column(name = "TAG")
    @CollectionTable(name = "JOB_TAGS", joinColumns = @JoinColumn(name = "JOB_ID"))
    private List<String> tags;

    @Column(name = "LOCATION")
    private String location;

    @CreatedDate
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "CREATED_AT")
    private Date createdAt;
}
