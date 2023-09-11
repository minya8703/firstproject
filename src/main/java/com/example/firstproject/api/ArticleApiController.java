package com.example.firstproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.articleService.ArticleService;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleService articleService;

	// Get
	@GetMapping("/api/articles")
	public List<Article> index() {
		return articleService.index();
	}

	// Get
	@GetMapping("/api/articles/{id}")
	public Article show(@PathVariable Long id) {
		return articleService.show(id);
	}

	// Post
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
		Article created = articleService.create(dto);
		return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	// Patch
	@PatchMapping("/api/articles/{id}")
	public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
		Article updated = articleService.update(id, dto);
		return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	// Delete
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Article> delete(@PathVariable Long id) {
		Article deleted = articleService.delete(id);
		return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).body(deleted)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PostMapping("/api/rransaction-test")
	public ResponseEntity<List<Article>>transactionTest(@RequestBody List<ArticleForm> dtos){
		List<Article> createdList = articleService.createArticles(dtos);
		return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
