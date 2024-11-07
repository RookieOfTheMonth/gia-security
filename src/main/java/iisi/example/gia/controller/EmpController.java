package iisi.example.gia.controller;

import iisi.example.gia.model.entity.DeptDO;
import iisi.example.gia.model.entity.EmpDO;
import iisi.example.gia.model.vo.DeptVO;
import iisi.example.gia.model.vo.EmpVO;
import iisi.example.gia.service.DeptService;
import iisi.example.gia.service.EmpService;
import iisi.example.gia.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private TestService testService;

    @ResponseBody
    @GetMapping("/emp/listAll")
    public String listAll(Model model,
                          @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        String requestUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        Page<EmpVO> empVOs = empService.getAllPages(PageRequest.of(pageNum, pageSize)).map(this::transformEmpVO);
        model.addAttribute("requestUri", requestUri);
        model.addAttribute("page", empVOs);
        return "emp/listAll";
    }

    @PostMapping("/emp/getOne_For_Display")
    public String listOne(Model model, Integer empno) {
        Optional<EmpDO> optional = empService.getOneEmp(empno);
        if (optional.isPresent()) {
            EmpDO empDO = optional.get();
            model.addAttribute("empVO", transformEmpVO(empDO));
        }
        return "emp/listOne";
    }

    @GetMapping("/emp/add")
    public String showAddPage(Model model) {
        EmpVO empVO = new EmpVO();
        // default value
        empVO.setEname("王小明");
        empVO.setJob("manager");
        empVO.setHiredate(format(LocalDate.now()));
        empVO.setSal(10000.0);
        empVO.setComm(100.0);
        model.addAttribute("empVO", empVO);
        model.addAttribute("deptVOs", transformDeptVOs(deptService.getAll()));
        return "emp/add";
    }

    @PostMapping("/emp/insert")
    @ResponseBody
    public EmpDO insert(@RequestBody EmpVO empVO) {
        EmpDO empDO = empService.addEmp(transformEmpDO(empVO));
        return empDO;
    }

    @PostMapping("/emp/insert2")
    @ResponseBody
    public EmpDO insert2(@RequestBody EmpVO empVO) {
        EmpDO empDO = testService.addEmp(transformEmpDO(empVO));
//        EmpDO empDO = empService.testAddEmp(transformEmpDO(empVO));
        return empDO;
    }

    @PostMapping("/emp/getOne_For_Update")
    public String showUpdatePage(Model model, Integer empno) {
        Optional<EmpDO> optional = empService.getOneEmp(empno);
        if (optional.isPresent()) {
            EmpDO empDO = optional.get();
            model.addAttribute("empVO", transformEmpVO(empDO));
        }
        List<DeptDO> deptDOs = deptService.getAll();
        model.addAttribute("deptVOs", transformDeptVOs(deptDOs));
        return "emp/update";
    }

    @PostMapping("/emp/update")
    public String update(Model model, EmpVO empVO) {
        EmpDO updatedEmpDO = empService.updateEmp(transformEmpDO(empVO));
        model.addAttribute("empVO", transformEmpVO(updatedEmpDO));
        return "emp/listOne";
    }

    @PostMapping("/emp/delete")
    public String delete(Integer empno) {
        empService.deleteEmp(empno);
        return "redirect:/emp/listAll";
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs.stream()
                .map(this::transformDeptVO)
                .collect(Collectors.toList());
    }

    private EmpVO transformEmpVO(EmpDO empDO) {
        EmpVO empVO = new EmpVO();
        empVO.setEmpno(empDO.getEmpno());
        empVO.setEname(empDO.getEname());
        empVO.setJob(empDO.getJob());
        empVO.setHiredate(format(empDO.getHiredate()));
        empVO.setSal(empDO.getSal());
        empVO.setComm(empDO.getComm());
        empVO.setDeptVO(transformDeptVO(empDO.getDeptDO()));
        return empVO;
    }

    private EmpDO transformEmpDO(EmpVO empVO) {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(empVO.getEmpno());
        empDO.setEname(empVO.getEname());
        empDO.setJob(empVO.getJob());
        empDO.setHiredate(parse(empVO.getHiredate()));
        empDO.setSal(empVO.getSal());
        empDO.setComm(empVO.getComm());
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(empVO.getDeptno());
        empDO.setDeptDO(deptDO);
        return empDO;
    }

    private DeptVO transformDeptVO(DeptDO deptDO) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptDO.getDeptno());
        deptVO.setDname(deptDO.getDname());
        deptVO.setLoc(deptDO.getLoc());
        return deptVO;
    }

    private String format(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDate);
    }

    private LocalDate parse(String LocalDataString) {
        return LocalDate.parse(LocalDataString);
    }

}