package iisi.example.gia.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "emp2")
@Entity
@NamedQuery(name = "emp.all", query = "select emp from EmpDO emp")
public class EmpDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPNO", nullable = false, columnDefinition = "NUMBER(4)")
    private Integer empno;

    @Column(name = "ENAME", columnDefinition = "VARCHAR2(10 CHAR)")
    private String ename;

    @Column(name = "JOB", columnDefinition = "VARCHAR2(9 CHAR)")
    private String job;

    @Column(name = "HIREDATE", columnDefinition = "DATE")
    private LocalDate hiredate;

    @Column(name = "SAL", columnDefinition = "NUMBER(7, 2)")
    private Double sal;

    @Column(name = "COMM", columnDefinition = "NUMBER(7, 2)")
    private Double comm;

    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    @JsonIgnore
    private DeptDO deptDO;
}
