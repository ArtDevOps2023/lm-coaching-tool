package com.lmph.be.dto;

import com.lmph.be.entity.Flow;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FlowAndSectionsInfo {


    private Long flowId;
    private String flowName;

    private List<FlowSectionInfo> sectionInfos;

}
