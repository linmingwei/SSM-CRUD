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
 * �Ƽ�ʹ��spring�ĵ�Ԫ���ԣ������Զ�������ע�����
 *����spring testģ��
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	/**
	 * ����DepartmentMapper
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
		//1. ����ApplicationContext
		//ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//2. ��������ȥ��mapper 
		//DepartmentMapper mapper=context.getBean(DepartmentMapper.class);
		//System.out.print(mapper);
		System.out.print(mapper);
		//1. ���벿��
		
/*		mapper.insertSelective(new Department(null,"������"));
		mapper.insertSelective(new Department(null,"���Բ�"));
*/		
		//employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",5));
		
		//ִ���������� 
		EmployeeMapper emapper = sqlSession.getMapper(EmployeeMapper.class);
		/*for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
			emapper.insertSelective(new Employee(null, uid, "M", uid+"@qq.com", 5));
		}*/
		//���²���
		//employeeMapper.updateByPrimaryKey(new Employee(2, "Marry", "F", "Marry@126.com", 5));
		//ɾ������
		employeeMapper.deleteByPrimaryKey(1002);
		
				
		System.out.println("����ɹ�");

		
		
	}

}
