package iisi.example.gia.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmpVO implements Serializable {

    private Integer empno;

    @NotBlank(message = "請輸入員工姓名")
    private String ename;

    private String job;

    @NotBlank(message = "請輸入雇用日期")
    private String hiredate;

    @NotNull(message = "請輸入薪水")
    private Double sal;

    @NotNull(message = "請輸入獎金")
    private Double comm;

    private Integer deptno;

    private DeptVO deptVO;
}
