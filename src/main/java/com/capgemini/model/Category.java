package com.capgemini.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;
	
	@Column
	private String name;
	
	@OneToMany( mappedBy = "category")
	private List<Task> tasks;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Category() {}

	/**
	 * @return the id
	 */
	public Long getId() {
		return category_id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.category_id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category_id, name, tasks, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(category_id, other.category_id) && Objects.equals(name, other.name) && Objects.equals(tasks, other.tasks)
				&& Objects.equals(user, other.user);
	}

}
