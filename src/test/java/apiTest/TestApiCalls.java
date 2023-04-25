package apiTest;

import org.testng.*;
import org.testng.annotations.*;

import apiEngine.models.Comments;
import apiEngine.models.Post;
import apiEngine.EndPoints;
import io.restassured.response.Response;

public class TestApiCalls {
    private static Response response;

    @Test(description = "Retrieve all post and validate number of records")
    @Parameters("noOfRecords")
    public void  getAllPosts(int noOfRecords) {
        response = EndPoints.getPosts();
        Post[]  posts = response.getBody().as(Post[].class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(posts.length, noOfRecords);
    }

    @Test(description = "Retrieve a post by id")
    @Parameters({"id", "ExpectedStatusCode"})
    public void  getPostById(int id, int ExpectedStatusCode) {
        response = EndPoints.getPostById(id);
        Post  post = response.getBody().as(Post.class);
        Assert.assertEquals(response.getStatusCode(), ExpectedStatusCode);
        if (response.getStatusCode() == 200) {                              //validate only if at least one post found
            Assert.assertEquals(post.getId(), id);
        }

    }

    @Test(description = "Retrieve post by user id")
    @Parameters({"id", "noOfRecords"})
    public void  getPostsByUserId(int id, int noOfRecords) {
        response = EndPoints.getPostsByUserId(id);
        Post[] posts = response.getBody().as(Post[].class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(posts.length, noOfRecords);
    }

    @Test(description = "Add post")
    @Parameters({"id", "userId", "title", "body"})
    public void  addPost(int id, int userId, String title, String body) {
        response = EndPoints.addPost(id, userId, title,body);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(description = "Update post")
    @Parameters({"id", "title", "body"})
    public void  updatePost(int id, String title, String body) {
        response = EndPoints.getPostsByUserId(id);
        Post[] posts = response.getBody().as(Post[].class);
        posts[0].setTitle(title);
        posts[0].setBody(body);
        response = EndPoints.updatePost(posts[0]);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Patch post")
    @Parameters({"id"})
    public void  patchPost(int id) {
        response = EndPoints.patchPost(id);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Delete post")
    @Parameters({"id"})
    public void  deletePost(int id) {
        response = EndPoints.deletePost(id);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Retrieve post comments by user id")
    @Parameters({"id", "numberOfComments"})
    public void getPostCommentsById(int id, int numberOfComments) {
        response = EndPoints.getPostCommentsById(id);
        Comments[] comments = response.getBody().as(Comments[].class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(comments.length, numberOfComments);
    }

    @Test(description = "Retrieve comments by postId")
    @Parameters({"postId", "numberOfComments"})
    public void  getCommentsByPostId(int id, int numberOfComments) {
        response = EndPoints.getCommentsByPostId(id);
        Comments[] comments = response.getBody().as(Comments[].class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(comments.length, numberOfComments);
    }
}
