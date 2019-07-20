package com.spring.myapp.user.service;

import java.util.Date;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserService {

	//회원가입 처리
	void register(UserVO user) throws Exception;

	//아이디 중복확인 처리
	int isDuplicateId(String userId) throws Exception;

	//로그인 시도 회원정보 조회처리
	UserVO login(LoginVO login) throws Exception;
	
	//자동로그인 유지 처리
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;

	//세션아이디 검증 후 회원정보 불러오는 처리
	UserVO getUserWithSessionId(String sessionId) throws Exception;
}










