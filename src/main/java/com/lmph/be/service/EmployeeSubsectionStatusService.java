package com.lmph.be.service;

import com.lmph.be.dao.EmployeeSubsectionStatusDao;
import com.lmph.be.dto.EmployeeSubsectionStatusInfo;
import com.lmph.be.entity.EmployeeSubsectionStatus;
import com.lmph.be.form.EmployeeSubsectionStatusForm;
import com.lmph.be.utility.DTOUtil;
import com.lmph.be.utility.FormUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSubsectionStatusService {

    private EmployeeSubsectionStatusDao employeeSubsectionStatusDao;

    @Autowired
    public EmployeeSubsectionStatusService(EmployeeSubsectionStatusDao employeeSubsectionStatusDao) {
        this.employeeSubsectionStatusDao = employeeSubsectionStatusDao;
    }

    /**
     * @author Jeffrey John Javison
     * @since 12-Dec-2023
     * @return
     */
    public List<EmployeeSubsectionStatusInfo> retrieveAllEmployeeSubsectionStatus(){

        List<EmployeeSubsectionStatus> employeeSubsectionStatuses =
                this.employeeSubsectionStatusDao.findAll();

        return employeeSubsectionStatuses.stream().map(DTOUtil::fromEntityToInfo).toList();
    }

    /**
     * @author Jeffrey John Javison
     * @since 14-Dec-2023
     * @return
     */
    public List<EmployeeSubsectionStatusInfo> retrieveEmployeeSubsectionStatusByEmployeeId(Long employeeId){
        List<EmployeeSubsectionStatus> employeeSubsectionStatuses =
                this.employeeSubsectionStatusDao.findByemployeeId(employeeId);

        return employeeSubsectionStatuses.stream().map(DTOUtil::fromEntityToInfo).toList();
    }

    public EmployeeSubsectionStatusInfo upsertEmployeeSubsectionStatus(EmployeeSubsectionStatusForm form) {
        EmployeeSubsectionStatus entity = FormUtil.fromFormToEntity(form);
        EmployeeSubsectionStatusInfo info = new EmployeeSubsectionStatusInfo();

        entity = this.employeeSubsectionStatusDao.save(entity);

        return DTOUtil.fromEntityToInfo(entity);
    }

    public void deleteEmployeeSubsectionStatus(Long id){
        this.employeeSubsectionStatusDao.deleteById(id);
    }
}
