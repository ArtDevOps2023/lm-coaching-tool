package com.lmph.be.service;

import com.lmph.be.dao.EmployeeFlowDao;
import com.lmph.be.dto.EmployeeFlowInfo;
import com.lmph.be.entity.*;
import com.lmph.be.form.EmployeeFlowForm;
import com.lmph.be.utility.DTOUtil;
import com.lmph.be.utility.FormUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for Employee's Flow
 *
 * @author Jeffrey John Javison
 * @since 06-Dec-2023
 */
@Service
public class EmployeeFlowService {

    private EmployeeFlowDao employeeFlowDao;

    @Autowired
    public EmployeeFlowService(EmployeeFlowDao employeeFlowDao) {
        this.employeeFlowDao = employeeFlowDao;
    }

    /**
     * Get all of {@link com.lmph.be.entity.Employee Employee}'s {@link com.lmph.be.entity.Flow Flow}
     *
     * @author Jeffrey John Javison
     * @since 06-Dec-2023
     */
    public List<EmployeeFlowInfo> getEmployeeFlowsByEmployeeId(Long employeeId){

        List<EmployeeFlow> employeeFlows = this.employeeFlowDao.findByemployeeId(employeeId);

        return employeeFlows.stream().map(DTOUtil::toEmployeeFlowInfo).toList();
    }

    /**
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param employeeFlowForm
     * @return
     */
    public EmployeeFlowInfo upsertEmployeeFlow(EmployeeFlowForm employeeFlowForm){
        EmployeeFlowInfo employeeFlowInfo = new EmployeeFlowInfo();
        EmployeeFlow employeeFlow = FormUtil.employeeFlowFormToEntity(employeeFlowForm);

        employeeFlow = this.employeeFlowDao.save(employeeFlow);

        BeanUtils.copyProperties(employeeFlow, employeeFlowInfo);

        return employeeFlowInfo;
    }

    /**
     * @author Jeffrey John Javison
     * @since 13-Dec-2023
     * @param infos
     */
    public void upsertEmployeeFlows(List<EmployeeFlowInfo> infos){

        List<EmployeeFlow> employeeFlows = infos.stream().map(DTOUtil::employeeInfotoEmployeeFlow).toList();

        this.employeeFlowDao.saveAll(employeeFlows);
    }

    /**
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param employeeId
     */
    @Transactional
    public void deleteEmployeeFlow(Long employeeId){
        this.employeeFlowDao.deleteByemployeeId(employeeId);
    }

}
