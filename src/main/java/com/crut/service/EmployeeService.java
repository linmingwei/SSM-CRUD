package com.crut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crut.bean.Employee;
import com.crut.bean.EmployeeExample;
import com.crut.bean.EmployeeExample.Criteria;
import com.crut.dao.EmployeeMapper;

/**
 * 
 * @author linmi
 *
 */
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 */
	public List<Employee> getEmps() {
		 return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
		
	}
	/**
	 * 校验用户名是否存在
	 * @param empName
	 * @return
	 */
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	public Employee getEmp(Integer empId) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}
	//单个删除
	public void deleteEmp(Integer empId) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(empId);
		
	}
	//批量删除
	public void deleteBatch(List<Integer> str_ids) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(str_ids);
		employeeMapper.deleteByExample(example);
		
	}
}
