package com.encore.spring.domain;

public class Product {

	private String name;
	private String maker;
	private int price, id;

	public Product() {
	}

	public Product(int id) {
		super();
		this.id = id;
	}

	public Product(String name, String maker, int price) {
		super();
		this.name = name;
		this.maker = maker;
		this.price = price;
	}

	public Product(String name, String maker, int id, int price) {
		super();
		this.name = name;
		this.maker = maker;
		this.id = id;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", maker=" + maker + ", price=" + price + ", id=" + id + "]";
	}

}
