package com.lmph.be.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlowInfo {

    private Long flowId;

    private String name;

    private String color;

    private String createdBy;

    private LocalDate createdDate;

    private List<FlowSectionInfo> flowSectionInfos = new ArrayList<>();
}
