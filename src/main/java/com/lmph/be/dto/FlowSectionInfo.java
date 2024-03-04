package com.lmph.be.dto;

import lombok.Data;

@Data
public class FlowSectionInfo {

    private Long id;

    private FlowInfo flowInfo;

    private SectionInfo sectionInfo;

    private Integer sortOrder;

}
