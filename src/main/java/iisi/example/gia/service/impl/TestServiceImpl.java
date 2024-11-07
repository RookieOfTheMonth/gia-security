package iisi.example.gia.service.impl;

import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.service.DeptService;
import iisi.example.gia.service.EmpService;
import iisi.example.gia.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private final EmpService empService;
    private final DeptService deptService;

    public TestServiceImpl(EmpService empService, DeptService deptService) {
        this.empService = empService;
        this.deptService = deptService;
    }


    @Override
    @Transactional
    public EmpDO addEmp(EmpDO empDO) {
        log.warn("before addEmp");
        try {
            EmpDO empDO1 = empService.addEmp(empDO);
        } catch (Exception e) {
            log.error("error", e);
        }
        log.warn("after addEmp");
        log.warn("before addDept");
        try {
            deptService.addDept();
        } catch (Exception e) {
            log.error("error", e);
        }
        log.warn("after addDept");
        return empDO;
    }
}