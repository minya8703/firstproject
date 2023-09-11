package com.example.firstproject.ArticleService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.firstproject.entity.Article;

@SpringBootTest
class ArticleServiceTest {
	@Autowired
	ArticleService articleService;

	@Test
	void testIndex() {
		// 1.예상데이터
		Article a = new Article(1L, "가가가가", "1111");
		Article b = new Article(1L, "나나나나", "2222");
		Article c = new Article(1L, "다다다다", "3333");
		List<Article> expected = new ArrayList<Article>();
		expected.add(a);
		expected.add(b);
		expected.add(c);
		
		// 2. 실제 데이터
		List<Article> articles = articleService.index();
		// 3. 비교 및 검증
		assertEquals(expected.toString(), articles.toString());
	}

}
