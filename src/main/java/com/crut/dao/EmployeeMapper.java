package com.crut.dao;

import com.crut.bean.Employee;
import com.crut.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);
    
//    �����ŵĲ�ѯ
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    
//    �����ŵĲ�ѯ
    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}