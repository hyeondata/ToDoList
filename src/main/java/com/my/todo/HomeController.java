package com.my.todo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.api.Session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	private Member User = new Member();
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request)throws Exception{
	//	logger.info("Welcome home! The client locale is {}.", locale);
		HttpSession session = request.getSession();
		session.setAttribute("Login", "False");

		return "home";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		 
		PrintWriter out = response.getWriter();
		
		System.out.println(session.getAttribute("Login"));
		
		User.setId(request.getParameter("id"));
		User.setPw(request.getParameter("pw"));
		User.setName(memberService.DBDao.Login(User));

		
		
		if (User.getName() == null) {
			out.println("<script>alert('일치하는 계정이 없습니다.');</script>");
			out.flush();
			
			session.setAttribute("Login", "False");
			
			return "home";
		}
		session.setAttribute("Login", "True");
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String Register(){
	//	logger.info("Welcome home! The client locale is {}.", locale);	

		return "register";
	}
	
	@RequestMapping(value = "/registerS", method = RequestMethod.POST)
	public String RegisterS(HttpServletRequest request)throws Exception{
	
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		memberService.DBDao.memberInsert(member);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(HttpServletRequest request,Locale locale, Model model, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String login = (String)session.getAttribute("Login");
		System.out.println(login);
		if (login.equals("False")) {
			out.println("<script>alert('로그인 해주세요.');</script>");
			out.flush();
			return "home";
		}
		System.out.println("메인:"+ User.getName());

		model.addAttribute("member", User.getName() );
		
		return "board";
	}
	
	@RequestMapping(value = "/CreateWork", method = RequestMethod.POST)
	public String CreateWork(HttpServletRequest request)throws Exception{
	
		
		memberService.DBDao.CreateWork(request.getParameter("desc"),request.getParameter("date"),User);
		
		System.out.println("이름 : "+ User.getName());
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String Submit(HttpServletRequest request)throws Exception{
	
		
		memberService.DBDao.Submit(request.getParameter("_id"));
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/cancle", method = RequestMethod.POST)
	public String Cancle(HttpServletRequest request)throws Exception{
	
		
		memberService.DBDao.Cancel(request.getParameter("_id"));

		
		return "redirect:/board";
	}
	
	
	
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public HashMap<String, ArrayList<ArrayList>> init(@RequestBody HashMap<String, ArrayList<ArrayList>> map) throws Exception  {
    	
    	List<Board> result  = memberService.DBDao.board(User);
    	ArrayList<String> desc = new ArrayList<String>();
    	ArrayList<String> date = new ArrayList<String>();
    	ArrayList<String> id = new ArrayList<String>();
    	ArrayList<Integer> status = new ArrayList<Integer>();
    	
    	ArrayList<ArrayList> _Data = new ArrayList<ArrayList>();
    	
    	for(int i = 0 ; i<result.size();i++) {
    		desc.add(result.get(i).getDesc());
    		date.add(result.get(i).getDate());
    		id.add(result.get(i).getid());
    		
    		status.add(result.get(i).getstatus());
    		
    		}
    	_Data.add(desc);
    	_Data.add(date);
    	_Data.add(id);
    	_Data.add(status);
    	System.out.println(_Data);
    	
		map.put("desc", _Data.get(0));
		map.put("date", _Data.get(1));
		map.put("id", _Data.get(2));
		map.put("status", _Data.get(3));
		
        return map;

    }
	
	
	
}
