package iisi.example.gia.model.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVO implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<EmpVO> empVOs;
}