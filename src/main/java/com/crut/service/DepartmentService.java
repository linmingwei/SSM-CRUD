package com.crut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crut.bean.Department;
import com.crut.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	public List<Department> getDepts(){
		return departmentMapper.selectByExample(null);
	}

}
