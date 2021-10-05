package com.ty.project2.hibernate1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(name="StudentTable1")
public class StudentTable implements Serializable {
	@Id
	//@Column(name="Student_Rollno")
	private int rollno;
	//@Column(name="Student_name")
	private String name;
	//@Column(name="Student_phno")
	private long phno;
	//@Column(name="Student_Standard")
	private String standard;
	public StudentTable() {
		super();
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Override
	public String toString() {
		return "Student1 [rollno=" + rollno + ", name=" + name + ", phno=" + phno + ", standard=" + standard + "]";
	}
	
	

}

