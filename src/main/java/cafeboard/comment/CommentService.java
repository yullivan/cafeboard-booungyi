package cafeboard.comment;

import cafeboard.post.Post;
import cafeboard.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    private final PostRepository postRepository;


    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    //TOdo create Comment
    public void create(CommentRequest request) {
        Post post = postRepository.findById(request.post()).orElseThrow();
        commentRepository.save(
                new Comment(
                        request.reply(),
                        post));
    }

    //todo postid 를 입력받으면 comment 중에 같은 postid 를가진 comment list 출력
    public CommentResponse getcommentlist(Long postid) {
        Comment comment = commentRepository.findBypostId(postid);
        return new CommentResponse(comment.getReply());
    }
}
