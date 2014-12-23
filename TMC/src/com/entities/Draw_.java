package com.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Draw.class)
public class Draw_ {
	public static volatile SingularAttribute<Draw, Integer> matchno;
	public static volatile SingularAttribute<Draw, String> team1Id;
	public static volatile SingularAttribute<Draw, String> team2Id;
	public static volatile SingularAttribute<Draw, String> venue;
	public static volatile SingularAttribute<Draw, Date> matchDate;
	public static volatile SingularAttribute<Draw, Character> result;
	public static volatile SingularAttribute<Draw, Integer> roundNo;
	public static volatile SingularAttribute<Draw, String> comments;
	public static volatile SingularAttribute<Draw, Tournament> tid;
}
