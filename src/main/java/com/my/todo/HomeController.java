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
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


import com.my.todo.DB;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	public static String _name = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model)throws Exception{
	//	logger.info("Welcome home! The client locale is {}.", locale);
		_name = null;
		


		return "home";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(HttpServletRequest request, HttpServletResponse response)throws Exception{

		response.setContentType("text/html; charset=UTF-8");
		 
		PrintWriter out = response.getWriter();
		 





		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
	
		_name =_db.Login(request.getParameter("id"),request.getParameter("pw"));
		System.out.println("이름 : "+ _name);
		
		
		if (_name == null) {
			out.println("<script>alert('일치하는 계정이 없습니다.');</script>");
			out.flush();
			
			return "home";
		}
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String Register(){
	//	logger.info("Welcome home! The client locale is {}.", locale);	

		return "register";
	}
	
	@RequestMapping(value = "/registerS", method = RequestMethod.POST)
	public String RegisterS(HttpServletRequest request)throws Exception{
	
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
		
		System.out.println(_db.Register(request.getParameter("id"), request.getParameter("pw"), request.getParameter("name")));

		return "redirect:/";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Locale locale, Model model)throws Exception{
				
		System.out.println("메인:"+ _name);

		model.addAttribute("member", _name );
		
		return "board";
	}
	
	@RequestMapping(value = "/CreateWork", method = RequestMethod.POST)
	public String CreateWork(HttpServletRequest request)throws Exception{
	
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
		System.out.println(request.getParameter("desc"));
		System.out.println(_db.CreateWork(request.getParameter("desc"), request.getParameter("date"), _name));
		System.out.println("이름 : "+ _name);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String Submit(HttpServletRequest request)throws Exception{
	
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
		System.out.println(request.getParameter("_id"));
		System.out.println(_db.Submit(request.getParameter("_id")));
		System.out.println("이름 : "+ _name);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/cancle", method = RequestMethod.POST)
	public String Cancle(HttpServletRequest request)throws Exception{
	
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
		System.out.println(request.getParameter("_id"));
		System.out.println(_db.Cancel(request.getParameter("_id")));
		System.out.println("이름 : "+ _name);
		
		return "redirect:/board";
	}
	
	
	
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public HashMap<String, ArrayList<ArrayList>> init(@RequestBody HashMap<String, ArrayList<ArrayList>> map) throws Exception  {
    	
    	ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		
		DB _db = ac.getBean(DB.class);
	//	System.out.println(_db.Do(_name));
		
		ArrayList<ArrayList> result = _db.Do(_name);

		 map.put("desc", result.get(0));
		 map.put("date", result.get(1));
		 map.put("id", result.get(2));
		 map.put("status",result.get(3));
		 System.out.println(result.get(3));
        return map;

    }
	
	
	
}
