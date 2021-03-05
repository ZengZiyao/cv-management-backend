package com.zzy.cvmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitationDto {
    private String id;

    private int database;

    private int countWithSelf;

    private int countWithoutSelf;

    private int hIndex;
}
