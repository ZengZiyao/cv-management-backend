package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.PubSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalDto extends PubSource {

    private String id;

    private String name;
}
