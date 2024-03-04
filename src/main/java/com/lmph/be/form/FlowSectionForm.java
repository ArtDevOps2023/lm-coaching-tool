package com.lmph.be.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class FlowSectionForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 6244856997840076976L;

    private Long id;

    @NotNull(message = "Flow must be present.")
    private Long flowId;

    @NotNull(message = "Section must be present.")
    private Long sectionId;

    @Positive
    private Integer sortOrder;


}
