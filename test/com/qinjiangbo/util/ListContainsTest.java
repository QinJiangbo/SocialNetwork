package com.qinjiangbo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListContainsTest {

	public static void main(String[] args) {
//		List<People> list = new ArrayList<People>();
//		list.add(new People("Amy", "ddd", 11));
//		list.add(new People("richard", "ccc", 22));
//		
//		System.out.println(list.contains(new People("Amy", "ddd", 11)));
		
//		List<String> list = new ArrayList<String>();
//		list.add("aaa");
//		list.add("bbb");
//		System.out.println(list.contains("aaa"));
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(6794);
		list.add(567);
		list.add(1567);
		list.add(4567);
		list.add(8950);
		Collections.sort(list);
		for(Integer inte : list) {
			System.out.println(inte);
		}
	}

}

class People {
	
	private String name;
	private String location;
	private int age;
	
	public People(String name, String location, int age) {
		super();
		this.name = name;
		this.location = location;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof People) {
			People people = (People) o;
			if(people.getAge() == age
					|| people.getName().equals(name)
					|| people.getLocation().equals(location)) {
				return true;
			}
		}
		return false;
	}
}