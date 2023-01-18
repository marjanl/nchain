package com.nchain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nchain.util.AppType;
import com.nchain.util.Details;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "nchain_app")
public class NchainApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AppType type;
    @Column(name = "image")
    private String image;
    @JsonIgnore
    @Column(name = "store")
    private String store;
    @JsonIgnore
    @Column(name = "ratings")
    private Integer ratings;
    @JsonIgnore
    @Column(name = "size")
    private Integer size;
    @JsonIgnore
    @Column(name = "score")
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("android_store")
    public String getAndroidStore(){
        return type == AppType.android ? store : null;
    }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("ios_store")
    public String getIosStore(){
        return type == AppType.ios ? store : null;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("android_details")
    public Details getAndroidDetails(){
        return type == AppType.android ? new Details(ratings, null, size)  : null;
    }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("ios_details")
    public Details getIosDetails(){
        return type == AppType.ios ? new Details(ratings, null, size) : null;
    }

}
