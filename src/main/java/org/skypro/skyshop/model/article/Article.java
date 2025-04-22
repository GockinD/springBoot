package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    String articleTitle;
    String textArticle;
    private final UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    public Article(String articleTitle, String textArticle) {
        this.articleTitle = articleTitle;
        this.textArticle = textArticle;
        this.id = UUID.randomUUID();
    }


    @Override
    public String toString() {
        return "Название статьи: " + articleTitle + '\'' +
                "Текст статьи: " + textArticle;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return toString();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleTitle);
    }
}
