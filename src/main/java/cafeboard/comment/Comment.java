package cafeboard.comment;
import cafeboard.post.Post;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(nullable = false , name = "")
    Post post;

    private String reply;

    public Comment() {

    }

    public Comment(String reply, Post post) {
        this.reply = reply;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
