package com.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:17:44.915-0400")
@StaticMetamodel(Transaction.class)
public class Transaction_ {
	public static volatile SingularAttribute<Transaction, Integer> transId;
	public static volatile SingularAttribute<Transaction, String> transType;
	public static volatile SingularAttribute<Transaction, BigDecimal> amount;
	public static volatile SingularAttribute<Transaction, String> reason;
	public static volatile SingularAttribute<Transaction, Date> transDate;
	public static volatile SingularAttribute<Transaction, Tournament> tid;
}
