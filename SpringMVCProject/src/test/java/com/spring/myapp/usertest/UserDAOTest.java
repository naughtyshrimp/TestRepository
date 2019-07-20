package com.spring.myapp.usertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.repository.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class UserDAOTest {
	
	@Autowired
	private IUserDAO dao;
	
	/*@Test
	public void insertTest() throws Exception {
		
		UserVO user = new UserVO();
		user.setUserId("abc1234");
		user.setUserPw("aaa4321!");
		user.setUserName("김철수");
		
		dao.register(user);
		System.out.println("회원가입 성공!");
		
	}*/
	
	/*@Test
	public void duplicateTest() throws Exception {
		
		int n = dao.isDuplicateId("banana");
		if(n == 1) 
			System.out.println("아이디가 중복됨!!");
		else 
			System.out.println("아이디 사용 가능!");
		
	}*/
	
	//로그인 테스트
	@Test
	public void loginTest() throws Exception {
		
		//로그인 처리를 위해 암호화된 DB패스워드를 디코딩하여 비교
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		LoginVO login = new LoginVO();
		login.setUserId("banana");
		login.setUserPw("bbb1234!");
		
		UserVO user = dao.login(login);
		System.out.println("로그인 시도 회원정보: " + user);
		
		if(user != null) {
			String dbPw = user.getUserPw();
			System.out.println("DB password: " + dbPw);
			String inputPw = login.getUserPw();
			System.out.println("Input password: " + inputPw);
			
			if(encoder.matches(inputPw, dbPw)) {
				System.out.println("로그인 성공!");
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
			
		} else {
			System.out.println("존재하지 않는 회원입니다.");
		}
		
	}
	
}
















