package com.capgemini.model;


import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategoryVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcategory;
	
	@Column
	private String name;
	
	@OneToMany( mappedBy = "categoryTask", cascade = CascadeType.ALL)
	private List<TaskVO> tasks;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="iduser")
	private UserVO userCategory;

	public CategoryVO() {
		super();
	}

	public CategoryVO(String name, List<TaskVO> tasks, UserVO userCategory) {
		super();
		this.name = name;
		this.tasks = tasks;
		this.userCategory = userCategory;
	}

	public CategoryVO(int idcategory, String name, List<TaskVO> tasks, UserVO userCategory) {
		super();
		this.idcategory = idcategory;
		this.name = name;
		this.tasks = tasks;
		this.userCategory = userCategory;
	}

	public int getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskVO> tasks) {
		this.tasks = tasks;
	}

	public UserVO getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserVO userCategory) {
		this.userCategory = userCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcategory, name, tasks, userCategory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryVO other = (CategoryVO) obj;
		return idcategory == other.idcategory && Objects.equals(name, other.name) && Objects.equals(tasks, other.tasks)
				&& Objects.equals(userCategory, other.userCategory);
	}

	@Override
	public String toString() {
		return "CategoryVO [idcategory=" + idcategory + ", name=" + name + ", tasks=" + tasks + ", userCategory="
				+ userCategory + "]";
	}
	
}
