package com.crut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crut.bean.Employee;
import com.crut.bean.Msg;
import com.crut.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 处理emps请求
 * @author linmi
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 单个删除 批量删除2合一
	 * 批量删除: 1-2-3
	 * 单个删除: 1
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids") String ids) {
		//批量删除
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			//单个删除
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
		
	}
	
	
	/**
	 * 判断用户名是否合法
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkUser(String empName) {
		//判断用户名是否合法
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regex)) {
			return Msg.fail().add("va_msg", "2-5位汉字,6-16字母,_-");
		}
		boolean b = employeeService.checkUser(empName);
		if (b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}
		
	}
	/**
	 * 根据ID获取员工信息
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("empId") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		return Msg.success().add("emp", employee);
	}
	/**
	 * 如果ajax直接发送 put请求
	 * 问题:请求体中有数据
	 * 但是Employee封装不上
	 * sql拼装条件 update tbl_emp where ID = xx
	 * 原因: 
	 * 1. tomcat 将请求体中的数据封装成map
	 * 2. getParameter()就会从这个map中取值
	 * 3. spring MVC封装POJO对象
	 *        会把POJO的属性值  request.getParameter("email");
	 * ajax发送PUT请求引发的血案:
	 *    PUT请求的数据  request.getParameter()取不到;
	 *    tomcat一看是PUT请求不会封装请求体中的数据为map,只有post请求的数据才会封装成map
	 *    
	 *    
	 * 
	 * 根据ID更新员工信息
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",  method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * 员工保存    对保存的数据进行校验
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误字段名"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
			
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	
	
	/**
	 * 获取所有员工
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		//pn-当前页数，  5-每页个数  查询之前使用PageHelper进行分页
		PageHelper.startPage(pn,5);
		List<Employee> emps = employeeService.getEmps(); 
		//使用PageInfo包装查询后的结果, 只需要将PageInfo交给页面就行了
		//PageInfo有详细的分页信息，包括我们查询出来的数据, 传入连续显示的页数 ：5
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
		
	}
	
	
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		//pn-当前页数，  5-每页个数  查询之前使用PageHelper进行分页
		PageHelper.startPage(pn,5);
		List<Employee> emps = employeeService.getEmps(); 
		//使用PageInfo包装查询后的结果, 只需要将PageInfo交给页面就行了
		//PageInfo有详细的分页信息，包括我们查询出来的数据, 传入连续显示的页数 ：5
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		return "list";
	}

}
