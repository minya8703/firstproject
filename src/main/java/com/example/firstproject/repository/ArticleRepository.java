package com.example.firstproject.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.firstproject.entity.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Override
	ArrayList<Article> findAll();

}
