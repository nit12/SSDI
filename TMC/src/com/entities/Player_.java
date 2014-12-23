package com.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, Integer> playerId;
	public static volatile SingularAttribute<Player, String> playerName;
	public static volatile SingularAttribute<Player, Date> dob;
	public static volatile SingularAttribute<Player, Integer> seeding;
	public static volatile SingularAttribute<Player, Team> teamId;
	public static volatile SingularAttribute<Player, Tournament> tid;
}
