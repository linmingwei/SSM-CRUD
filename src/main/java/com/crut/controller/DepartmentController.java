package com.crut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crut.bean.Department;
import com.crut.bean.Employee;
import com.crut.bean.Msg;
import com.crut.service.DepartmentService;

/**
 * 处理和部门有关的请求
 * @author linmi
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	
	
	
	@RequestMapping("/getDepts")
	@ResponseBody
	public Msg getDepts() {
		List<Department> depts = departmentService.getDepts();
		return Msg.success().add("depts", depts);
		
	}

}
