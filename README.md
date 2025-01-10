# 네이버 카페 게시판 #
## API설계 ##

- API 설계대로 API 테스트를 먼저 작성


+ 데이터 모델링
  + 게시판(Board)
  + 게시글(Post)
  + 댓글(Comment
  
| 게시판 / 게시글 / 댓글 CRUD

  + 게시판 ("/board")
    -
    - 생성(Post)
      - board_id,name
    - 목록 조회(Get)
      - findAll()
    - 수정(Put)
      - @Trang,set
    - 삭제(Delet)
      - DeletById
   
  + 게시글 ("/Post")
    -
    + 생성(Post)
    + 목록 조회(댓글 개수 포함)(Get)
    + 상세 조회(댓글 목록 조회 포함)(Get)
    + 수정(Put)
    + 삭제(Delet)
    
  + 댓글
    - 생성(Post)
