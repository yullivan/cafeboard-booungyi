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

    @PostMapping("/board")
    public void createBoard(@RequestBody BoardRequest request) {
        boardService.create(request);
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
}
