package com.crut.bean;

import org.springframework.stereotype.Component;

@Component
public class Department {
    private Integer deptId;

    private String deptName;

    public Integer getDeptId() {
        return deptId;
    }
    
    public Department() {
		super();
	}

	public Department(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}