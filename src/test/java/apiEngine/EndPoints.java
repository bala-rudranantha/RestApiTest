package apiEngine;

import apiEngine.models.Post;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static Response getPosts() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get("/posts");
        return response;
    }
    public static Response getPostById(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get("/posts/" + id);
        return response;
    }

    public static Response getPostsByUserId(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get("/posts?userId=" + id);
        return response;
    }

    public static Response addPost(int id, int userId, String title, String body) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        Post post = new Post(id, userId, title, body);
        request.header("Content-Type", "application/json");
        Response response = request.body(post).post("/posts");
        return response;
    }

    public static Response updatePost(Post post) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.body(post).put("/posts/" + post.getId());
        return response;
    }

    public static Response patchPost(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        String body = """
                { "title": "Test patched the title"
                }                
                """;
        Response response = request.body(body).patch("/posts/" + id);
        return response;
    }

    public static Response deletePost(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.delete("/posts/" + id);
        return response;
    }

    public static Response getPostCommentsById(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get("/posts/" + id + "/comments");
        return response;
    }

    public static Response getCommentsByPostId(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get("/comments?postId=" + id);
        return response;
    }

}
