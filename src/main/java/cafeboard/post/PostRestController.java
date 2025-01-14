package cafeboard.post;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostRestController {
    PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    //Todo 게시글 생성
    @PostMapping("/post")
    public void createPost(@Valid @RequestBody CreatePostRequest  request) {
        postService.create(request);
    }

    //Todo 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponse getbypostid(@PathVariable Long id) {
        return postService.getbypostId(id);
    }

    //Todo 게시글 전체 조회
    @GetMapping("/post")
    public List<PostResponse> getallpost() {
        return postService.getallbpost();
    }

    //Todo 게시글 수정
    @PutMapping("/post/{id}")
    public void updatepostbyid(@Valid @RequestBody PostRequest request, @PathVariable Long id) {
        postService.updatebyid(request, id);
    }

    @DeleteMapping("post/{id}")
    public void deltebypostId(@PathVariable Long id) {
        postService.deletbypostid(id);
    }

    @PostMapping("/posts")
    public PostDetailResponse create(@RequestBody CreatePostRequest request) {
        return postService.create(request);
    }
}
