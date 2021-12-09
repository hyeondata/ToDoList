package com.my.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberDao MemberDao;
	
	@Autowired
	BoardDao BoardDao;

	
	
}
