package cafeboard.post;

import java.time.LocalDateTime;

public record PostResponse(
        Long board,
        String title,
        String content,
        LocalDateTime createdAt
) {
}
