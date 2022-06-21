package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Autowired private ArticleRepository articleRepository;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공___존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패___존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void create_성공__title과__content만_있는_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }


    @Test
    void create_실패___id가_포함된_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title_content가_있는_dto_입력() {
        // 준비
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);

        // 예상
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title만_있는_dto_입력() {
        // 준비
        Long id = 1L;
        String title = "가나다라";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, "1111");

        // 예상
        Article expected = new Article(id, title, "1111");

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패___존재하는_않는_id의_dto_입력() {
        // 준비
        Long id = -1L;
        String title = "가나다라";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_실패___id만_있는_dto_입력() {
        // 준비
        Long id = 10L;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공___존재하는_id_입력() {
        // 준비
        Long id = 1L;

        // 예상
        Article expected = articleRepository.findById(id).orElse(null);

        // 실제
        Article article = articleRepository.findById(1L).orElse(null);

        // 비교
        assertEquals(expected.toString(), article.toString());


    }

    @Test
    @Transactional
    void delete_성공___존재지_않는_id_입력() {
        // 준비
        Long id = -1L;

        // 예상
        Article expected = null;

        // 실제
        Article article = articleRepository.findById(id).orElse(null);

        // 비교
        assertEquals(expected, article);
    }
}






















