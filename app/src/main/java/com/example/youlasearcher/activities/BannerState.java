package com.example.youlasearcher.activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BannerState {
    private String title;
    private String workSubTitle;
    private String periodSubTitle;
    private String url;
    private String id;
    private boolean active;
}
