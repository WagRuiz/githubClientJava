package com.una.githubclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa el perfil de un usuario obtenido de la API pública de GitHub.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("login")      private String login;
    @JsonProperty("name")       private String name;
    @JsonProperty("bio")        private String bio;
    @JsonProperty("followers")  private int followers;
    @JsonProperty("following")  private int following;
    @JsonProperty("location")   private String location;
    @JsonProperty("blog")       private String blog;
    @JsonProperty("created_at") private String createdAt;
    @JsonProperty("avatar_url") private String avatarUrl;
    @JsonProperty("html_url")   private String htmlUrl;

    // -------------------------------------------------------

    // --- Getters y setters (usados por Jackson y JavaFX) ---

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public int getFollowers() { return followers; }
    public void setFollowers(int followers) { this.followers = followers; }

    public int getFollowing() { return following; }
    public void setFollowing(int following) { this.following = following; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getBlog() { return blog; }
    public void setBlog(String blog) { this.blog = blog; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String htmlUrl) { this.htmlUrl = htmlUrl; }

    // -------------------------------------------------------

    @Override
    public String toString() {
        return String.format(
            "User{name='%s', login='%s', followers=%d, following=%d, location='%s'}",
            name, login, followers, following, location
        );
    }
}
