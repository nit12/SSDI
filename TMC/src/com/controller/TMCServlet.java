package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.exceptions.NonexistentEntityException;
import com.entities.Player;
import com.entities.Salary;
import com.entities.Staff;
import com.entities.Team;
import com.entities.Tournament;
import com.entities.Transaction;
import com.entities.Draw;
import com.entities.User;
import com.services.CalendarUtilityDefault;
import com.services.wbservice;

/**
 * Servlet implementation class TMCServlet
 */
//@WebServlet( urlPatterns={"/TMCServlet"})
public class TMCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TMCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("The example for path123123123");
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		wbservice wb=new wbservice(session);
		
		if(action!=null&&action.equalsIgnoreCase("login")){
			
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			System.out.println(email);
			System.out.println(password);
			String role=wb.checkUser(email, password);
			if(role.equalsIgnoreCase("GM")){
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/GMDashboard.jsp");
						dispatcher.forward(request,response);
			}
			if(role.equalsIgnoreCase("FM")){
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/FMDashboard.jsp");
						dispatcher.forward(request,response);
			}
			if(role.equalsIgnoreCase("EM")){
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/EMDashboard.jsp");
						dispatcher.forward(request,response);
			}
			if(role.equalsIgnoreCase("SM")){
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/SMDashboard.jsp");
						dispatcher.forward(request,response);
			}
			if(role.equalsIgnoreCase("")){
				System.out.println("role is empty");
				//request.setAttribute("errorMsg","error");
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/login.jsp?errorMsg=error");
						dispatcher.forward(request,response);
			}
			
			
		}
		else if(action!=null&&action.equalsIgnoreCase("newTournament")){
			
			String tname=request.getParameter("tname");
			String ttype=request.getParameter("ttype");
			String sdate=request.getParameter("sdate");
			String edate=request.getParameter("edate");
			String fname=request.getParameter("fname");
			String femail=request.getParameter("femail");
			String ename=request.getParameter("ename");
			String eemail=request.getParameter("eemail");
			String sname=request.getParameter("sname");
			String semail=request.getParameter("semail");
			
			Calendar cSDate=Calendar.getInstance();
			Calendar cEDate=Calendar.getInstance();
			try {
				cSDate=CalendarUtilityDefault.getCalendarFromString(sdate);
				cEDate=CalendarUtilityDefault.getCalendarFromString(edate);
				System.out.println(cSDate.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Tournament t1=new Tournament();
			t1.setTname(tname);
			t1.setType(ttype);
			t1.setStartdate(cSDate.getTime());
			t1.setEnddate(cEDate.getTime());
			t1.setStatus("Just Started");
			wb.addTournament(t1);
			
			int maxTID=0;
			Tournament maxTour=new Tournament();
			List<Tournament> tour=wb.getTournaments();
           	if(null!=tour && tour.size()!=0){
           	for(Tournament t: tour){
           		if(t.getTid()>maxTID)
           		{
           			maxTID=t.getTid();
           			maxTour=t;
           		}
           		}
           	}
           	System.out.println("max tid is "+maxTID);
			
			User u1=new User();
			u1.setEmailid(femail);
			u1.setName(fname);
			u1.setPassword("root");
			u1.setRole("FM");
			u1.setTid(maxTour);
			wb.addUser(u1);
			
			User u2=new User();
			u2.setEmailid(eemail);
			u2.setName(ename);
			u2.setPassword("root");
			u2.setRole("EM");
			u2.setTid(maxTour);
			wb.addUser(u2);
			
			User u3=new User();
			u3.setEmailid(semail);
			u3.setName(sname);
			u3.setPassword("root");
			u3.setRole("SM");
			u3.setTid(maxTour);
			wb.addUser(u3);
			
			
			
			
			RequestDispatcher dispatcher =
					getServletContext().getRequestDispatcher("/GMDashboard.jsp");
					dispatcher.forward(request,response);
			
		}
		
		
else if(action!=null&&action.equalsIgnoreCase("newSponsor")){
			
			String spname=request.getParameter("spname");
			String spamount=request.getParameter("spamount");
			BigDecimal spamount1=new BigDecimal(spamount);
			String spdate=request.getParameter("spdate");
			
			Calendar cSDate=Calendar.getInstance();
			try {
				cSDate=CalendarUtilityDefault.getCalendarFromString(spdate);
				
				System.out.println(cSDate.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Transaction t1=new Transaction();
			User user=(User)session.getAttribute("User");
			t1.setReason(spname);
			t1.setAmount(spamount1);
			t1.setTransDate(cSDate.getTime());
			t1.setTid(user.getTid());
			t1.setTransType("CR");
			wb.addSponsor(t1);
			
			
			
			RequestDispatcher dispatcher =
					getServletContext().getRequestDispatcher("/Sponsor.jsp");
					dispatcher.forward(request,response);
			
		}	
else if(action!=null&&action.equalsIgnoreCase("createStaff")){
	
	String sname=request.getParameter("staffname");
	String staffcontact=request.getParameter("staffcontact");
	int stcontact=Integer.parseInt(staffcontact);
	String sroleId=request.getParameter("roleId");
	int iroleId=Integer.parseInt(sroleId);
	
	Salary sal=wb.getSalary(iroleId);
	
	
	Staff s=new Staff();
	User user=(User)session.getAttribute("User");
	
	s.setStaffName(sname);
	s.setContactNo(stcontact);
	s.setRoleId(sal);
	s.setTid(user.getTid());
	wb.addStaff(s);
	
	
	
	RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher("/SMDashboard.jsp");
			dispatcher.forward(request,response);
	
}	
		
			else if(action!=null&&action.equalsIgnoreCase("addExpenditure")){
				
				String spname=request.getParameter("spname");
				String spamount=request.getParameter("spamount");
				BigDecimal spamount1=new BigDecimal(spamount);
				String spdate=request.getParameter("spdate");
				System.out.println("date "+spdate);
				Calendar cSDate=Calendar.getInstance();
				try {
					cSDate=CalendarUtilityDefault.getCalendarFromString(spdate);
					
					System.out.println(cSDate.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Transaction t1=new Transaction();
				User user=(User)session.getAttribute("User");
				t1.setReason(spname);
				t1.setAmount(spamount1);
				t1.setTransDate(cSDate.getTime());
				t1.setTid(user.getTid());
				t1.setTransType("DB");
				wb.addSponsor(t1);
				
				
				
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/Expenditure.jsp");
						dispatcher.forward(request,response);
				
			}	
			else if(action!=null&&action.equalsIgnoreCase("newTeam")){
				
		        String teamSize=request.getParameter("tsize");
		        int tSize=Integer.parseInt(teamSize);
		        String playerSize=request.getParameter("psize");
		        int pSize=Integer.parseInt(playerSize);
		        User user=(User)session.getAttribute("User");
		        
				
				Calendar pDOB=Calendar.getInstance();
				/*try {
					cSDate=CalendarUtilityDefault.getCalendarFromString(spdate);
					
					System.out.println(cSDate.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
		        
				for(int i=1;i<=tSize;i++){
					Team t=new Team();
					t.setTeamName(request.getParameter("tname"+i));
					String sTeamSeeding=request.getParameter("tseeding"+i);
					int iteamseeding=Integer.parseInt(sTeamSeeding);
					t.setTeamSeeding(iteamseeding);
					t.setTid(user.getTid());
					wb.addTeams(t);
					
					
					
					for(int j=1;j<=pSize;j++){
						Player p=new Player();
						p.setPlayerName(request.getParameter("pname"+i+j));
						String sPDOB=request.getParameter("pDOBdate"+i+j);
						try {
							pDOB=CalendarUtilityDefault.getCalendarFromString(sPDOB);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String sPlayerSeeding=request.getParameter("pseeding"+i+j);
						int iplayerSeeding=Integer.parseInt(sPlayerSeeding);
						p.setDob(pDOB.getTime());
						p.setSeeding(iplayerSeeding);
						p.setTid(user.getTid());
						p.setTeamId(t);
						wb.addPlayers(p);
					}
					
					
				}
							
				
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/EMDashboard.jsp");
						dispatcher.forward(request,response);
				
			}
			else if(action!=null&&action.equalsIgnoreCase("newDraw")){
				
/*		        String teamSize=request.getParameter("tsize");
		        int tSize=Integer.parseInt(teamSize);
		        String playerSize=request.getParameter("psize");
		        int pSize=Integer.parseInt(playerSize);*/
		        User user=(User)session.getAttribute("User");
		        
				
				Calendar cDOM=Calendar.getInstance();
				/*try {
					cSDate=CalendarUtilityDefault.getCalendarFromString(spdate);
					
					System.out.println(cSDate.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
		        
				List<Draw> drawList=wb.getDraws();
				if(null!=drawList && drawList.size()!=0)
	           	{
	           	for(Draw d: drawList){
			           	if(d.getTid().getTid()==(user.getTid()).getTid()){
							d.setVenue(request.getParameter("venue"+d.getMatchno().intValue()));
							String sMatchDate=request.getParameter("date"+d.getMatchno().intValue());
							try {
								cDOM=CalendarUtilityDefault.getCalendarFromString(sMatchDate);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							d.setMatchDate(cDOM.getTime());
							String sRoundNo=request.getParameter("round"+d.getMatchno().intValue());
							d.setRoundNo(Integer.parseInt(sRoundNo));
							try {
								wb.editDraw(d);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			           		
			           	}
			           	
			           	
	           		}
	           	}
				
				RequestDispatcher dispatcher =
						getServletContext().getRequestDispatcher("/EMDashboard.jsp");
						dispatcher.forward(request,response);
				
			}
			else if(action!=null&&action.equalsIgnoreCase("updateSalary")){
				User user=(User)session.getAttribute("User");
				List<Salary> slist=user.getTid().getSalaryList();
				for(int j=0;j<slist.size();j++){
					Salary salary=slist.get(j);
					String sal=request.getParameter("salary"+j);
					
					BigDecimal bg=new BigDecimal(sal);
					
					salary.setSalary(bg);
								try {
									wb.editSalary(salary);
								} catch (NonexistentEntityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					
				}
				
				
								
								RequestDispatcher dispatcher =
										getServletContext().getRequestDispatcher("/MaintainStaffSalary.jsp");
										dispatcher.forward(request,response);
								
							}
			else if(action!=null&&action.equalsIgnoreCase("allocateStaff")){
				User user=(User)session.getAttribute("User");
				List<Staff> slist=user.getTid().getStaffList();
				for(int j=0;j<slist.size();j++){
					Staff staff=slist.get(j);
					String steamId=request.getParameter("teamId"+j);
					int iteamId=Integer.parseInt(steamId);
					Team t=wb.getTeam(iteamId);
					
					staff.setTeamId(t);
					try {
						wb.editStaff(staff);
					} catch (NonexistentEntityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
					
				}	
				RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/SMDashboard.jsp");
				dispatcher.forward(request,response);
								
			}
			else if(action!=null&&action.equalsIgnoreCase("generateSalary")){
				User user=(User)session.getAttribute("User");
				
				
				Calendar cSalDate=Calendar.getInstance();
				String sSalDate=request.getParameter("saldate");
				try {
					cSalDate=CalendarUtilityDefault.getCalendarFromString(sSalDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String sMonth=new SimpleDateFormat("MMM").format(cSalDate.getTime());
				
				List<Staff> staffList=user.getTid().getStaffList();
				
				for(Staff s: staffList){
					
				Transaction t1=new Transaction();
				
				t1.setReason("Salary for "+s.getStaffName()+" /Month-"+sMonth);
				t1.setAmount(s.getRoleId().getSalary());
				t1.setTransDate(cSalDate.getTime());
				t1.setTid(user.getTid());
				t1.setTransType("DB");
				wb.addSponsor(t1);
				}
				request.setAttribute("SalaryGeneration","Sucessfully generated salary for staff for month "+sMonth);
				
				
				RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/MaintainStaffSalary.jsp");
				dispatcher.forward(request,response);
								
			}


	}

}
