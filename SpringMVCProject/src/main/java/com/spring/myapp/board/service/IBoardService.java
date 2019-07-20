package com.spring.myapp.board.service;

import java.util.List;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

public interface IBoardService {
	
	void insert(BoardVO article) throws Exception;
	BoardVO getArticle(int boardNo, boolean trigger) throws Exception;
	void update(BoardVO article) throws Exception;
    void delete(int boardNo) throws Exception;
    
    List<BoardVO> getAllArticles() throws Exception;
    
    //페이징 처리
    List<BoardVO> listPaging(Criteria cri) throws Exception;
    //게시글의 총 게시물 수를 불러오기
    int countArticles() throws Exception;
    
    //검색 동적 SQL처리
    List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
    int countSearchArticles(SearchCriteria cri) throws Exception;
    
	/////////////////////////////////////////////////////////////////
	////////////////////////Depreciated//////////////////////////////
    
    //제목으로 검색하여 게시물 불러오기
    List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception;
    int countSearchedArticles(SearchCriteria cri) throws Exception;
    
    //작성자 이름으로 검색 SQL처리
    List<BoardVO> listSearchByWriter(SearchCriteria cri) throws Exception;
    //검색 후 총 게시물 수 가져오기
    int countSearchedArticles2(SearchCriteria cri) throws Exception;
}






