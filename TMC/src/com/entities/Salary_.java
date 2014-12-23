package com.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Salary.class)
public class Salary_ {
	public static volatile SingularAttribute<Salary, Integer> roleId;
	public static volatile SingularAttribute<Salary, String> roleName;
	public static volatile SingularAttribute<Salary, BigDecimal> salary;
	public static volatile ListAttribute<Salary, Staff> staffList;
	public static volatile SingularAttribute<Salary, Tournament> tid;
}
