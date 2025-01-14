package cafeboard.post;

import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;

    @Autowired
    BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

//    public void create(PostRequest request) {
//        Board board = boardRepository.findById(request.board()).orElseThrow();
//        postRepository.save(
//                new Post(
//                        request.title(),
//                        request.content(),
//                        board
//                ));
//    }
//Todo 테스트코드
    public PostDetailResponse create(CreatePostRequest request) {
        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시판 id: " + request.boardId()));
        Post post = postRepository.save(new Post(request.title(), request.content(), board));
        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt());
    }
    public PostResponse getbypostId(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return new PostResponse(
                post.getBoard().getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt());
    }

    public List<PostResponse> getallbpost() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponse(
                        post.getBoard().getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt()
                )).toList();
    }

    @Transactional
    public void updatebyid(@Valid PostRequest request, Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        Board board = boardRepository.findById(request.board()).orElseThrow();
        post.setBoard(board);
        post.setTitle(request.title());
        post.setContent(request.content());
        post.setUpdatedAt(LocalDateTime.now());
    }

    public void deletbypostid(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(post.getId());
    }
}
