package iisi.example.gia.service.impl;


import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.repository.EmpRepository;
import iisi.example.gia.service.DeptService;
import iisi.example.gia.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EmpDO addEmp(EmpDO empDO) {
        repository.save(empDO);
        return empDO;
    }

    @Override
    @Transactional
    public EmpDO updateEmp(EmpDO empDO) {
        return repository.save(empDO);
    }

    @Override
    @Transactional
    public void deleteEmp(Integer empno) {
        repository.deleteById(empno);
    }

    @Override
    public Optional<EmpDO> getOneEmp(Integer empno) {
        return repository.findById(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<EmpDO> getAllPages(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public EmpDO testAddEmp(EmpDO empDO) {
        log.warn("before addEmp");
        try {
            EmpDO empDO1 = addEmp(empDO);
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
