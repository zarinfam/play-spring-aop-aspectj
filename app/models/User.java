package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by saeed on 12/22/15.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String testFiled;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private Set<Post> posts;

    public User() {
    }

    public User(String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getTestFiled() {
        return testFiled;
    }

    public void setTestFiled(String testFiled) {
        this.testFiled = testFiled;
    }
}
