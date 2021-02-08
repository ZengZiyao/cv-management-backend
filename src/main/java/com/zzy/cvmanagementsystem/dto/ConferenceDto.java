package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.PubSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceDto extends PubSource {
    @Id
    private String id;

    private String name;
}
