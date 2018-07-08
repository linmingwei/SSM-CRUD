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
 * 使用Spring单元测试
 * @author linmi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//可以把spring IOC自动注入
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	
	MockMvc mockMvc;
	
	//传入springMVC的IOC
	@Autowired
	WebApplicationContext context;
	
	@Before
	public void initMockMvc() {
		//返回一个mockMVC
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","7")).andReturn();
		//请求成功后，请求域中会有PageInfo，我们可以取出进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo page = (PageInfo)request.getAttribute("pageInfo");
		System.out.println("总页数："+page.getPages());
		System.out.println("总记录数："+page.getTotal());
		System.out.println("当前页数："+page.getPageNum());
		System.out.println("在页面需要连续显示的页码");
		int[] nums = page.getNavigatepageNums();
		System.out.println(Arrays.toString(nums));
		
		List<Employee> emps = page.getList();
		for (Employee employee : emps) {
			System.out.println(employee.toString());
			
		}
		
		
	}
	

}
