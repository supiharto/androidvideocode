package com.fangwei.domain;

public class Person {
	private Integer id;
	private String name;
	private String phone;
	private Integer amount;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Person(Integer id, String name, String phone, Integer amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.phone = phone;
	}

	public Person(Integer id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public Person(String name, String phone, Integer amount) {
		this.amount = amount;
		this.name = name;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}
	
	
	
}
