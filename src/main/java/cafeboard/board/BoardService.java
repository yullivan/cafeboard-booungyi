package cafeboard.board;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;


    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    // HACK :
    //
    //Todo 게시판 생성
    public void create(BoardRequest request) {
        boardRepository.save(new Board(request.name()));
    }

    //Todo 게시글 목록 조회
    public List<BoardResponse> getallboard() {
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponse(
                        board.getName()
                )).toList();
    }

    //Todo 게시글 수정
    @Transactional
    public void updateboard(BoardRequest request, Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setName(request.name());
    }

    //Todo 게시판 삭제
    public void deletboard(Long id) {
        boardRepository.deleteById(id);
    }
}
