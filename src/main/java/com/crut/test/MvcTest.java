package com.crut.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crut.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * ʹ��Spring��Ԫ����
 * @author linmi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//���԰�spring IOC�Զ�ע��
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	
	MockMvc mockMvc;
	
	//����springMVC��IOC
	@Autowired
	WebApplicationContext context;
	
	@Before
	public void initMockMvc() {
		//����һ��mockMVC
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","7")).andReturn();
		//����ɹ����������л���PageInfo�����ǿ���ȡ��������֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo page = (PageInfo)request.getAttribute("pageInfo");
		System.out.println("��ҳ����"+page.getPages());
		System.out.println("�ܼ�¼����"+page.getTotal());
		System.out.println("��ǰҳ����"+page.getPageNum());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[] nums = page.getNavigatepageNums();
		System.out.println(Arrays.toString(nums));
		
		List<Employee> emps = page.getList();
		for (Employee employee : emps) {
			System.out.println(employee.toString());
			
		}
		
		
	}
	

}
