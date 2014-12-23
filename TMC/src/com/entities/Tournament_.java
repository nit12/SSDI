package com.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.899-0400")
@StaticMetamodel(Tournament.class)
public class Tournament_ {
	public static volatile SingularAttribute<Tournament, Integer> tid;
	public static volatile SingularAttribute<Tournament, String> tname;
	public static volatile SingularAttribute<Tournament, String> type;
	public static volatile SingularAttribute<Tournament, Date> startdate;
	public static volatile SingularAttribute<Tournament, Date> enddate;
	public static volatile SingularAttribute<Tournament, String> status;
	public static volatile ListAttribute<Tournament, Staff> staffList;
	public static volatile ListAttribute<Tournament, Draw> drawList;
	public static volatile ListAttribute<Tournament, Team> teamList;
	public static volatile ListAttribute<Tournament, Salary> salaryList;
	public static volatile ListAttribute<Tournament, User> userList;
	public static volatile ListAttribute<Tournament, Transaction> transactionList;
	public static volatile ListAttribute<Tournament, Player> playerList;
}
