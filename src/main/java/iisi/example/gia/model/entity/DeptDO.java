package iisi.example.gia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "dept2")
@Entity
@NamedQuery(name = "dept.all", query = "SELECT dept FROM DeptDO dept")
public class DeptDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPTNO", nullable = false, columnDefinition = "NUMBER(3)")
    private Integer deptno;

    @Column(name = "DNAME", columnDefinition = "VARCHAR2(14 CHAR)")
    private String dname;

    @Column(name = "LOC", columnDefinition = "VARCHAR2(13 CHAR)")
    private String loc;

    @OneToMany(mappedBy = "deptDO", cascade = CascadeType.REMOVE)
    private List<EmpDO> empDOs;
}
