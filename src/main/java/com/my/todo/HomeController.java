package com.my.todo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		
				
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String Login(HttpServletRequest request)throws Exception{
	
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class,DB.class);
		DataSource ds = ac.getBean(DataSource.class);
		DB _db = ac.getBean(DB.class);
	
		_name =_db.Login(request.getParameter("id"),request.getParameter("pw"));
		System.out.println("이름 : "+ _name);
		
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
		DataSource ds = ac.getBean(DataSource.class);
		DB _db = ac.getBean(DB.class);
		
		System.out.println(_db.Register(request.getParameter("id"), request.getParameter("pw"), request.getParameter("name")));

		return "register";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Locale locale, Model model)throws Exception{
				
		System.out.println("메인:"+ _name);

		model.addAttribute("member", _name );
		
		return "board";
	}

}
