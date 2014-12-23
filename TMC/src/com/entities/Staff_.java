package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Staff.class)
public class Staff_ {
	public static volatile SingularAttribute<Staff, Integer> staffId;
	public static volatile SingularAttribute<Staff, String> staffName;
	public static volatile SingularAttribute<Staff, Integer> contactNo;
	public static volatile SingularAttribute<Staff, Salary> roleId;
	public static volatile SingularAttribute<Staff, Team> teamId;
	public static volatile SingularAttribute<Staff, Tournament> tid;
}
