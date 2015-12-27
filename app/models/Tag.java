package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by saeed on 12/22/15.
 */

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String tagName;
    private String tagDescription;

    @ManyToMany(mappedBy="tags", fetch = FetchType.LAZY)
    private Set<Post> posts;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
