package iisi.example.gia.controller;

import iisi.example.gia.model.entity.DeptDO;
import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.model.vo.DeptVO;
import iisi.example.gia.model.vo.EmpVO;
import iisi.example.gia.service.DeptService;
import iisi.example.gia.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<DeptDO> deptDOs = deptService.getAll();
        List<EmpDO> empDOs = empService.getAll();
        model.addAttribute("deptVOs", transformDeptVOs(deptDOs));
        model.addAttribute("empVOs", transformEmpVOs(empDOs));
        return "index";
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs
                .stream()
                .map(deptDO -> {
                    DeptVO deptVO = new DeptVO();
                    deptVO.setDeptno(deptDO.getDeptno());
                    deptVO.setDname(deptDO.getDname());
                    deptVO.setLoc(deptDO.getLoc());
                    return deptVO;
                })
                .collect(Collectors.toList());
    }

    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOs) {
        return empDOs
                .stream()
                .map(empDO -> {
                    EmpVO empVO = new EmpVO();
                    empVO.setEmpno(empDO.getEmpno());
                    empVO.setEname(empDO.getEname());
                    empVO.setJob(empDO.getJob());
                    empVO.setHiredate(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                    .format(empDO.getHiredate())
                    );
                    empVO.setSal(empDO.getSal());
                    empVO.setComm(empDO.getComm());
                    return empVO;
                })
                .collect(Collectors.toList());
    }

}
