/**
 * 
 */
package com.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import com.controller.DrawJpaController;
import com.controller.PlayerJpaController;
import com.controller.ReportJpaController;
import com.controller.SalaryJpaController;
import com.controller.StaffJpaController;
import com.controller.TeamJpaController;
import com.controller.TournamentJpaController;
import com.controller.TransactionJpaController;
import com.controller.UserJpaController;
import com.controller.exceptions.NonexistentEntityException;
import com.entities.Draw;
import com.entities.Player;
import com.entities.Report;
import com.entities.Salary;
import com.entities.Staff;
import com.entities.Team;
import com.entities.Tournament;
import com.entities.Transaction;
import com.entities.User;


/**
 * @author vinodkumar
 *
 */
public class wbservice {

	/**
	 * @param args
	 */
	private static EntityManagerFactory emf;
	private HttpSession session;
	
	static{
		emf=Persistence.createEntityManagerFactory("TMC");
	}
	
	public wbservice(){}
	public wbservice(HttpSession session){
		this.session=session;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//wbservice wb=new wbservice();


	}

	public  void addTournament(Tournament t){
		TournamentJpaController controller = new TournamentJpaController(emf);
		try {
			controller.create(t);
			System.out.println("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  void addUser(User u){
		UserJpaController controller = new UserJpaController(emf);
		try {
			controller.create(u);
			System.out.println("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String checkUser(String email,String password)
	{
		String role="";
		
		UserJpaController userController=new UserJpaController(emf);
		List<User> userList=userController.findUserEntities();
		User u1 = null;
		for(User u :userList){
			if(u.getEmailid().equals(email)&&u.getPassword().equals(password)){
				role=u.getRole();
				u1=u;
			}
		}
		if(u1!=null)
		 session.setAttribute("User",u1);
		
		
		
		return role;
		
	}
	public List<Tournament> getTournaments(){
		
		TournamentJpaController tjc=new TournamentJpaController(emf);
		List<Tournament> tour=new ArrayList<Tournament>();
		
		tour=tjc.findTournamentEntities();
		System.out.println("size of tournaments"+tour.size());
		return tour;
		
		
	}
	public Tournament findTournament(Integer tid){
		
		TournamentJpaController tjc=new TournamentJpaController(emf);
		Tournament tour=new Tournament();
		
		tour=tjc.findTournament(tid);
		
		return tour;
		
		
	}
	public List<Report> getReport(){
		
		ReportJpaController pjc=new ReportJpaController(emf);
		List<Report> ReportList=new ArrayList<Report>();
		
		ReportList=pjc.findReportEntities();
		System.out.println("size of report"+ReportList.size());
		return ReportList;
		
		
	}
	public List<Draw> getDraws(){
		
		DrawJpaController djc=new DrawJpaController(emf);
		List<Draw> drawList=new ArrayList<Draw>();
		
	//	User user=(User)session.getAttribute("User");
		
		drawList=djc.findDrawEntities();
		System.out.println("size of draws"+drawList.size());
		return drawList;
		
		
	}
	public List<Team> getTeams(){
		
		TeamJpaController tjc=new TeamJpaController(emf);
		List<Team> TeamList=new ArrayList<Team>();
		
		TeamList=tjc.findTeamEntities();
		System.out.println("size of teams"+TeamList.size());
		return TeamList;
		
		
	}
	public List<Player> getPlayers(){
		
		PlayerJpaController pjc=new PlayerJpaController(emf);
		List<Player> PlayerList=new ArrayList<Player>();
		
		PlayerList=pjc.findPlayerEntities();
		System.out.println("size of teams"+PlayerList.size());
		return PlayerList;
		
		
	}
	public Salary getSalary(int iRoleId){
		
		SalaryJpaController sjc=new SalaryJpaController(emf);
		List<Salary> salList=new ArrayList<Salary>();
		Salary s1=new Salary();
		salList=sjc.findSalaryEntities();
		
		for(Salary sal:salList){
			if(sal.getRoleId()==iRoleId)
				s1=sal;
		}
		return s1;
		
		
		
	}
	public Team getTeam(int iTeamId){
		
		TeamJpaController tjc=new TeamJpaController(emf);
		List<Team> teamList=new ArrayList<Team>();
		Team t1=new Team();
		teamList=tjc.findTeamEntities();
		
		for(Team team:teamList){
			if(team.getTeamid()==iTeamId)
				t1=team;
		}
		return t1;
		
		
	}
	
public List<Transaction> getTransactions(){
		
		TransactionJpaController tjc=new TransactionJpaController(emf);
		List<Transaction> trans=new ArrayList<Transaction>();
		
		trans=tjc.findTransactionEntities();
		System.out.println("size of transactions"+trans.size());
		return trans;
		
		
	}
public void editDraw(Draw d) throws NonexistentEntityException, Exception{
	
	DrawJpaController controller = new DrawJpaController(emf);
	
	
	controller.edit(d);
	System.out.println("success in editing the draw");
	
	
	
}
public void editSalary(Salary sal) throws NonexistentEntityException, Exception{
	
	SalaryJpaController controller = new SalaryJpaController(emf);
	
	
	controller.edit(sal);
	System.out.println("success in editing the salary");
	
	
	
}
public void editStaff(Staff staff) throws NonexistentEntityException, Exception{
	
	StaffJpaController controller = new StaffJpaController(emf);
	
	
	controller.edit(staff);
	System.out.println("success in editing the staff ");
	
	
	
}

public List<Staff> getStaff(){
	
	StaffJpaController sjc=new StaffJpaController(emf);
	List<Staff> staff=new ArrayList<Staff>();
	
	staff=sjc.findStaffEntities();
	System.out.println("size of transactions"+staff.size());
	return staff;
	
	
}
public void addSponsor(Transaction t1) {
	TransactionJpaController controller = new TransactionJpaController(emf);
	try {
		controller.create(t1);
		System.out.println("Success");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void addStaff(Staff s) {
	StaffJpaController controller = new StaffJpaController(emf);
	try {
		controller.create(s);
		System.out.println("Success in creating staff");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void addDraw(Draw d1) {
	DrawJpaController controller = new DrawJpaController(emf);
	try {
		controller.create(d1);
		System.out.println("Success");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void addTeams(Team t) {
	TeamJpaController controller = new TeamJpaController(emf);
	try {
		controller.create(t);
		System.out.println("Success");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void addPlayers(Player p) {
	PlayerJpaController controller = new PlayerJpaController(emf);
	try {
		controller.create(p);
		System.out.println("Success");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public int[] generateDraw(int teamSize,int MinTeamid){
	
	int[] adraw=new int[teamSize];
		
	
	int degree=(int) (Math.log(teamSize) / Math.log(2));
	
	
	int size=(int) Math.pow(2,degree+1)-1;
	
	
	 int[] a=new int[size];
	
	 a[0]=1;
	
	upheap(a,0,1,degree);
	
	for(int i=(teamSize-1),j=0;i<a.length;i++,j++){
		adraw[j]=a[i]+MinTeamid-1;
		}
		//System.out.print(" "+a[i]);
	return adraw;
	
	

}
public static void upheap(int a1[],int p,int ctr,int degree){
	
	int c=(2*p)+1;
	  
		a1[c]=a1[p];
		a1[c+1]=((int)(Math.pow(2,ctr))+1)-a1[c];
		
		ctr++;  
	if(ctr<=degree){
		
		upheap(a1,c,ctr,degree);
		upheap(a1,c+1,ctr,degree);
	}

}
	
}
