package cafeboard.comment;

public record CommentRequest (
        String reply,
        Long post
)
{}
