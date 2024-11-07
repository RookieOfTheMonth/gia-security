package iisi.example.gia.service.impl;

import iisi.example.gia.model.entity.DeptDO;
import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.repository.DeptRepository;
import iisi.example.gia.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository repository;

    @Override
    public List<DeptDO> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<DeptDO> getAllPages(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<DeptDO> getOneDept(Integer deptno) {
        return repository.findById(deptno);
    }

    @Override
    @Transactional
    public DeptDO update(DeptDO deptDO) {
        return repository.save(deptDO);
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        DeptDO deptDO = repository.getOneDeptWithEmps(deptno);
        return deptDO.getEmpDOs();
    }

    @Override
    @Transactional
    public void deleteDept(Integer deptno) {
        repository.deleteById(deptno);
    }


    @Override
    @Transactional
    public void addDept() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDname("IT");
        deptDO.setLoc("Taipei");
        repository.save(deptDO);
        int i = 1 / 0;
    }
}
