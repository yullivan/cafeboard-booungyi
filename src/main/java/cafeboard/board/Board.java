package cafeboard.board;

import cafeboard.post.Post;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;

    public Board() {
    }

    public Board(String title, List<Post> posts) {
        this.title = title;
        this.posts = posts;
    }

    public Board(String title) {
        this.title = title;
//        this.posts = posts;
    }


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
