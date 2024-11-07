package iisi.example.gia.service;

import iisi.example.gia.model.entity.EmpDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Integer empno);

    Optional<EmpDO> getOneEmp(Integer empno);

    List<EmpDO> getAll();

    Page<EmpDO> getAllPages(Pageable pageable);

    @Transactional
    EmpDO testAddEmp(EmpDO empDO);
}
