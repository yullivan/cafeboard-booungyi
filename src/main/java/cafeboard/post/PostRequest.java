package cafeboard.post;

import cafeboard.board.Board;

import java.time.LocalDateTime;

public record PostRequest(
        Long board,
        String title,
        String content
) {
}
