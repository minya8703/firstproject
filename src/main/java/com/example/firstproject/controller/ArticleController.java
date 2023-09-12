package com.example.firstproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentService commentService;

	@GetMapping("/articles/new")
	public String newArticleForm() {
		return "articles/new";
	}

	@PostMapping("/articles/create")
	public String createArticle(ArticleForm form) {
		log.info(form.toString());
//		System.out.println(form.toString());
		Article article = form.toEntity();

		log.info(article.toString());
		Article saved = articleRepository.save(article);
		log.info(saved.toString());
		return "redirect:/articles/" + saved.getId();
	}

	@GetMapping("/articles/{id}")
	public String show(@PathVariable Long id, Model model) {
		log.info("id = " + id);
		// 1. id를 조회하여 데이터가져오기
		Article articleEntiy = articleRepository.findById(id).orElse(null);
		List<CommentDto> commentsDtos = commentService.comments(id);
		// 2. 모델에 데이터 등록하
		// name라는 이름으로 value 객체 추가
		model.addAttribute("article", articleEntiy);
		model.addAttribute("commentsDtos", commentsDtos);
		// 3. 뷰 페이지 반환하기
		return "articles/show";
	}

	@GetMapping("/articles")
	public String index(Model model) {
		ArrayList<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		return "articles/index";
	}

	@GetMapping("/articles/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		// 1. id를 조회하여 수정 할 데이터가져오기
		Article articleEntiy = articleRepository.findById(id).orElse(null);
		model.addAttribute("article", articleEntiy);
		return "articles/edit";
	}

	@PostMapping("/articles/update")
	public String update(ArticleForm form) {

		// 1. DTO를 엔티티로 변환
		Article articleEntiy = form.toEntity();
		log.info(articleEntiy.toString());
		// 2. 엔티티을 DB에 저장
		// 2-1. DB에서 기존 데이터 가지고오기
		Article target = articleRepository.findById(articleEntiy.getId()).orElse(null);
		// 2-2. 기존 데이터 값을 갱신
		if (target != null) {
			articleRepository.save(articleEntiy);
		}
		// 3. 수정 결과 페이지 리다이렉트
		return "redirect:/articles/" + articleEntiy.getId();
	}

	@GetMapping("/articles/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		// 1. id를 조회하여 삭제 할 데이터가져오기
		Article target = articleRepository.findById(id).orElse(null);
		log.info(target.toString());
		if (target != null) {
			articleRepository.delete(target);
			rttr.addFlashAttribute("msg", "삭제 되었습니다.");
		}
		// 3. 수정 결과 페이지 리다이렉트
		return "redirect:/articles/";
	}
}
