package cafeboard.board;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {
    BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponseTest create(@RequestBody CreateBoardRequest request) {
        return boardService.create(request);
    }
    @GetMapping("/boards")
    public List<BoardResponseTest> findAll() {
        return boardService.findAll();
    }
//
//    @PostMapping("/board")
//    public void createBoard(@RequestBody BoardRequest request) {
//        boardService.create(request);
//    }

    @PutMapping("/boards/{boardId}")
    public BoardResponseTest update(@PathVariable long boardId, @RequestBody CreateBoardRequest request) {
        return boardService.update(boardId, request);
    }
    //모든 게시판 목록 조회
    @GetMapping("/board")
    public List<BoardResponse> findallboard() {
        return boardService.getallboard();
    }

    //게시판 수정
    @PutMapping("/board/{id}")
    public void updateBoard(@Valid @RequestBody BoardRequest request, @PathVariable Long id) {
        boardService.updateboard(request, id);
    }

    //게시판 삭제
    @DeleteMapping("/board/{id}")
    public void deletBoard(@PathVariable Long id) {
        boardService.deletboard(id);
    }
    //Todo 게시판 삭제 테스트
    @DeleteMapping("/boards/{boardId}")
    public void deleteById(@PathVariable long boardId) {
        boardService.deleteById(boardId);
    }
}
