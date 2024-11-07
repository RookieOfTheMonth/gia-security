package iisi.example.gia.repository;


import iisi.example.gia.model.entity.DeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeptRepository extends JpaRepository<DeptDO, Integer> {

    @Query(value = "SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno")
    DeptDO getOneDeptWithEmps(@Param("deptno") Integer deptno);

    @Query(value = "SELECT * FROM DeptDO where deptno = :deptno", nativeQuery = true)
    DeptDO getOneById(@Param("deptno") Integer deptno);

    DeptDO findDeptnoByDnameAndLocOrderByDeptno(@Param("dname") String dname, @Param("loc") String loc);

}
