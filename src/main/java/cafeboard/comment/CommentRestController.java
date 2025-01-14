package cafeboard.comment;

import org.springframework.web.bind.annotation.*;


@RestController
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/Comment")
    public void postcomment(@RequestBody CommentRequest request) {
        commentService.create(request);
    }

    @GetMapping("/Comment")
    public CommentResponse getcomment (@PathVariable Long id){
        return commentService.getcommentlist(id);
    }
}
