package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.915-0400")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> userid;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> emailid;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, Integer> supervisorid;
	public static volatile SingularAttribute<User, Tournament> tid;
}
