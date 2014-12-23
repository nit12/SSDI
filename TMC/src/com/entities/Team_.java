package com.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Integer> teamid;
	public static volatile SingularAttribute<Team, String> teamName;
	public static volatile SingularAttribute<Team, Integer> teamSeeding;
	public static volatile ListAttribute<Team, Staff> staffList;
	public static volatile SingularAttribute<Team, Tournament> tid;
	public static volatile ListAttribute<Team, Player> playerList;
}
