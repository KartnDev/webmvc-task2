package org.example.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReverseResponse {
    private Integer errorCode;
    private String errorMessage;
    private Boolean hasErrors;
}
