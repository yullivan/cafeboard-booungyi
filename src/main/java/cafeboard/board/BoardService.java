package cafeboard.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {

    private final BoardRepository boardRepository;


    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    // HACK :
    //
    //Todo 게시판 생성
//    public void create(BoardRequest request) {
//        boardRepository.save(new Board(request.title()));
//    }
    public BoardResponseTest create(CreateBoardRequest request) {
        Board board = boardRepository.save(new Board(request.title()));
        return new BoardResponseTest(board.getId(), board.getTitle());
    }

    public List<BoardResponseTest> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponseTest(board.getId(), board.getTitle()))
                .toList();
    }
    //Todo 게시글 목록 조회
    public List<BoardResponse> getallboard() {
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponse(
                        board.getTitle()
                )).toList();
    }

    //Todo 게시글 수정
    @Transactional
    public void updateboard(BoardRequest request, Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setTitle(request.title());
    }

    //Todo 게시판 삭제
    public void deletboard(Long id) {
        boardRepository.deleteById(id);
    }
    //Todo 게시판 수정 테스트
    @Transactional
    public BoardResponseTest update(long boardId, CreateBoardRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없습니다 id: " + boardId));
        board.changeTitle(request.title());
        return new BoardResponseTest(board.getId(), board.getTitle());
    }
    //Todo 게시판 삭제 테스트
    public void deleteById(long boardId) {
        boardRepository.deleteById(boardId);
    }
}
