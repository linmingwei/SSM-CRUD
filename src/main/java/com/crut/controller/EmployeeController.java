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
 * ����emps����
 * @author linmi
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ����ɾ�� ����ɾ��2��һ
	 * ����ɾ��: 1-2-3
	 * ����ɾ��: 1
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			//����ɾ��
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
		
	}
	
	
	/**
	 * �ж��û����Ƿ�Ϸ�
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkUser(String empName) {
		//�ж��û����Ƿ�Ϸ�
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regex)) {
			return Msg.fail().add("va_msg", "2-5λ����,6-16��ĸ,_-");
		}
		boolean b = employeeService.checkUser(empName);
		if (b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
		
	}
	/**
	 * ����ID��ȡԱ����Ϣ
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
	 * ���ajaxֱ�ӷ��� put����
	 * ����:��������������
	 * ����Employee��װ����
	 * sqlƴװ���� update tbl_emp where ID = xx
	 * ԭ��: 
	 * 1. tomcat ���������е����ݷ�װ��map
	 * 2. getParameter()�ͻ�����map��ȡֵ
	 * 3. spring MVC��װPOJO����
	 *        ���POJO������ֵ  request.getParameter("email");
	 * ajax����PUT����������Ѫ��:
	 *    PUT���������  request.getParameter()ȡ����;
	 *    tomcatһ����PUT���󲻻��װ�������е�����Ϊmap,ֻ��post��������ݲŻ��װ��map
	 *    
	 *    
	 * 
	 * ����ID����Ա����Ϣ
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
	 * Ա������    �Ա�������ݽ���У��
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("�����ֶ���"+fieldError.getField());
				System.out.println("������Ϣ"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
			
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	
	
	/**
	 * ��ȡ����Ա��
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		//pn-��ǰҳ����  5-ÿҳ����  ��ѯ֮ǰʹ��PageHelper���з�ҳ
		PageHelper.startPage(pn,5);
		List<Employee> emps = employeeService.getEmps(); 
		//ʹ��PageInfo��װ��ѯ��Ľ��, ֻ��Ҫ��PageInfo����ҳ�������
		//PageInfo����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ����������, ����������ʾ��ҳ�� ��5
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
		
	}
	
	
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		//pn-��ǰҳ����  5-ÿҳ����  ��ѯ֮ǰʹ��PageHelper���з�ҳ
		PageHelper.startPage(pn,5);
		List<Employee> emps = employeeService.getEmps(); 
		//ʹ��PageInfo��װ��ѯ��Ľ��, ֻ��Ҫ��PageInfo����ҳ�������
		//PageInfo����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ����������, ����������ʾ��ҳ�� ��5
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		return "list";
	}

}
