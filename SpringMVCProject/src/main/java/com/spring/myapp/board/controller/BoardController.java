package com.spring.myapp.board.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.service.IBoardService;
import com.spring.myapp.commons.paging.PageCreator;
import com.spring.myapp.commons.paging.SearchCriteria;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private IBoardService service;

	//게시글 목록페이지 열람요청 처리 메서드
	/*
	//페이징 처리 전
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {

		logger.info("/board/list request~~");

		model.addAttribute("articles", service.getAllArticles());

		return "board/list";
	}
	*/
	
	//페이징 처리 후 게시글 목록 요청
	/*@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Criteria cri, Model model) throws Exception {
		
		logger.info("/board/list : GET요청 발생!");
		
		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countArticles());
		
		model.addAttribute("articles", service.listPaging(cri));
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}*/
	
	//검색 처리 후 게시글 목록 불러오기
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(SearchCriteria cri, Model model) throws Exception {
		
		logger.info("/board/list : GET요청 발생!");
		
		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countSearchArticles(cri));
		
		/*String condition = cri.getCondition();
		
		if(condition.equals("title")) {
			pc.setArticleTotalCount(service.countSearchedArticles(cri));
			model.addAttribute("articles", service.listSearchByTitle(cri));
		} else if(condition.equals("writer")) {
			pc.setArticleTotalCount(service.countSearchedArticles2(cri));
			model.addAttribute("articles", service.listSearchByWriter(cri));
		} else {
			pc.setArticleTotalCount(service.countArticles());
			model.addAttribute("articles", service.listPaging(cri));
		}*/
		model.addAttribute("articles", service.listSearch(cri));
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}

	//게시글 작성화면 열람요청
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
				
		logger.info("method: get > /board/write");
		return "board/write";
	}

	//게시글 등록 요청
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		logger.info("method: post > /board/write");
		logger.info(article.toString());
		service.insert(article);
		redirectAttr.addFlashAttribute("message", "regSuccess");
		return "redirect:/board/list";
	}

	//게시물 상세 조회 페이지 요청
	@RequestMapping(value="/content", method = RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo,			
			@ModelAttribute("criteria") SearchCriteria cri,
			Model model) throws Exception {

		logger.info("method: get > /board/content");
		model.addAttribute("article", service.getArticle(boardNo, true));
//		model.addAttribute("page", page);
		
		return "board/content";
	}
	
	//게시물 수정 페이지 요청
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo,
			@ModelAttribute("criteria") SearchCriteria cri,
			Model model) throws Exception {
				
		logger.info("method: get > /board/modify");
		model.addAttribute("article", service.getArticle(boardNo, false));
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVO article,
			SearchCriteria cri,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.info("method: post > /board/modify");
		service.update(article);
		
		redirectAttributes.addAttribute("page", cri.getPage())
						  .addAttribute("countPerPage", cri.getCountPerPage())
						  .addAttribute("condition", cri.getCondition())
						  .addAttribute("keyword", cri.getKeyword())
						  .addFlashAttribute("message", "modSuccess");

		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo,
			SearchCriteria cri,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.info("method: post > /board/delete");
		service.delete(boardNo);
		
		redirectAttributes.addAttribute("page", cri.getPage());
		redirectAttributes.addAttribute("countPerPage", cri.getCountPerPage())
						  .addAttribute("condition", cri.getCondition())
						  .addAttribute("keyword", cri.getKeyword());
		redirectAttributes.addFlashAttribute("message", "delSuccess");

		return "redirect:/board/list";
	}

}
















