package com.lmph.be.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class FlowForm implements Serializable {

    @Serial
    private static final long serialVersionUID = -2895894716907078049L;

    private Long flowId;

    @NotEmpty(message = "Flow name must not be empty.")
    @Size(max = 100, message = "Flow name must be less than or equal to 100 characters.")
    private String name;

    private List<FlowSectionForm> flowSectionForms;

    private String createdBy;

    private LocalDate createdDate;
}
