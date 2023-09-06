package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {
	private ArticleRepository articleRepository;
	@GetMapping("/articles/new")
	public String newArticleForm() {
		return "articles/new";
	}
	
	@PostMapping("/articles/create")
	public String createArticle(ArticleForm form) {
		log.info(form.toString());
		System.out.println(form.toString());
		Article article =form.toEntity();
		
		log.info(article.toString());
		Article saved = articleRepository.save(article);
		return "";
	}
	
	@GetMapping("/articles/{id}")
	public String show(@PathVariable Long id) {
		return "";
	}

}