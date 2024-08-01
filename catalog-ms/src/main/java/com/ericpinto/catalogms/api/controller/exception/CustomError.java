package com.ericpinto.catalogms.api.controller.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class CustomError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
}
