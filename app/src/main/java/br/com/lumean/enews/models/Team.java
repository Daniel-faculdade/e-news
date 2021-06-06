package br.com.lumean.enews.models;

public class Team {
    private String name;
    private String imageUrl;
    private String twitterUrl;

    private Team() {}

    public Team(String nome, String imageUrl, String twitterUrl) {
        this.name = nome;
        this.imageUrl = imageUrl;
        this.twitterUrl = twitterUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }
}
