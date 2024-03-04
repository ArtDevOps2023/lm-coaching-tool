package com.lmph.be.form;

import com.lmph.be.dto.SectionInfo;
import com.lmph.be.entity.Flow;
import lombok.Data;

import java.util.List;

@Data
public class FlowAndSectionsForm {

    private Flow flow;

    private List<SectionInfo> sectionInfos;

}
