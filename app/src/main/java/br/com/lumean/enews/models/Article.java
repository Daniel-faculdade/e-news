package br.com.lumean.enews.models;

public class Article {
    private String title;
    private String caption;
    private String content;
    private String imageUrl;
    private String publishDate;

    private Article() {}

    public Article(String title, String caption, String content, String imageUrl, String publishDate) {
        this.title = title;
        this.caption = caption;
        this.content = content;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
