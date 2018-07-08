package com.crut.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crut.bean.Department;
import com.crut.bean.Employee;
import com.crut.dao.DepartmentMapper;
import com.crut.dao.EmployeeMapper;
import com.crut.service.EmployeeService;
/**
 * 
 * @author linmi
 * 推荐使用spring的单元测试，可以自动帮我们注入组件
 *导入spring test模块
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	/**
	 * 测试DepartmentMapper
	 */
	@Autowired
	DepartmentMapper mapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession; 
	
	@Autowired
	EmployeeService employeeService;
	
	
	@Test
	public void testService() {
		System.out.println(employeeService);
	}
	
	@Test
	public void test() {
		//1. 创建ApplicationContext
		//ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//2. 从容器中去除mapper 
		//DepartmentMapper mapper=context.getBean(DepartmentMapper.class);
		//System.out.print(mapper);
		System.out.print(mapper);
		//1. 插入部门
		
/*		mapper.insertSelective(new Department(null,"开发部"));
		mapper.insertSelective(new Department(null,"测试部"));
*/		
		//employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",5));
		
		//执行批量插入 
		EmployeeMapper emapper = sqlSession.getMapper(EmployeeMapper.class);
		/*for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
			emapper.insertSelective(new Employee(null, uid, "M", uid+"@qq.com", 5));
		}*/
		//更新测试
		//employeeMapper.updateByPrimaryKey(new Employee(2, "Marry", "F", "Marry@126.com", 5));
		//删除测试
		employeeMapper.deleteByPrimaryKey(1002);
		
				
		System.out.println("插入成功");

		
		
	}

}
