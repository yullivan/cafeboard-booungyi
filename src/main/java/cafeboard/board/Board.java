package cafeboard.board;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "board")
//    private List<Post> posts;

    public Board() {
    }

    public Board(String name) {
        this.name = name;
//        this.posts = posts;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
