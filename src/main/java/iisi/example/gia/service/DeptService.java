package iisi.example.gia.service;

import iisi.example.gia.model.entity.DeptDO;
import iisi.example.gia.model.entity.EmpDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface DeptService {

    List<DeptDO> getAll();

    Page<DeptDO> getAllPages(Pageable pageable);

    Optional<DeptDO> getOneDept(Integer deptno);

    DeptDO update(DeptDO deptDO);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

    @Transactional
    void addDept();
}
