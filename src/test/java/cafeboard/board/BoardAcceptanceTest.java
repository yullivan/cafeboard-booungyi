package cafeboard.board;

import cafeboard.utils.DatabaseCleanup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }

    @DisplayName("게시판을 생성한다.")
    @Test
    void createBoard() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBoardRequest("공지사항"))
                .when()
                .post("/boards")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

    }

    @DisplayName("게시판 목록을 조회한다.")
    @Test
    void findBoards() {
        // 게시판 목록 조회
        RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
    @DisplayName("게시판을 수정한다.")
    @Test
    void updateBoard() {
        // given
        // 게시판 생성
        final String 변경_전_제목 = "공지사항";
        final String 변경_후_제목 = "공지";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new CreateBoardRequest(변경_전_제목))
                .when()
                .post("/boards")
                .then()
                .statusCode(200);

        // when
        // 게시판 title 수정
        BoardResponseTest 수정된_게시판 = RestAssured
                .given().log().all()
                .pathParam("boardId", 1L)
                .contentType(ContentType.JSON)
                .body(new CreateBoardRequest(변경_후_제목))
                .when()
                .put("/boards/{boardId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BoardResponseTest.class);

        // then
        // 게시판 목록 조회
        List<BoardResponseTest> boards = RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", BoardResponseTest.class);

        assertThat(boards).allMatch(board -> !board.title().equals(변경_전_제목));
        assertThat(boards).anyMatch(board -> board.title().equals(변경_후_제목));
    }

    @DisplayName("게시판을 삭제한다.")
    @Test
    void deleteBoard() {
        // given
        // 게시판 생성 1
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new CreateBoardRequest("공지사항"))
                .when()
                .post("/boards")
                .then()
                .statusCode(200);

        // 게시판 생성 2
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new CreateBoardRequest("자유게시판"))
                .when()
                .post("/boards")
                .then()
                .statusCode(200);

        // when
        // 게시판 삭제
        RestAssured
                .given().log().all()
                .pathParam("boardId",1L)
                .when()
                .delete("/boards/{boardId}")
                .then().log().all()
                .statusCode(200);

        // then
        // 게시판 목록 조회
        List<BoardResponseTest> boards = RestAssured
                .given().log().all()
                .when()
                .get("/boards")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", BoardResponseTest.class);

        assertThat(boards).allMatch(board -> !board.title().equals("공지사항"));
    }
}