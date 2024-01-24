package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    setJsonContentType(response);
    writeResponse(response, service.all());
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    setJsonContentType(response);
    writeResponse(response, service.getById(id));
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    setJsonContentType(response);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    writeResponse(response, service.save(post));
  }

  public void removeById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }

  private void setJsonContentType(HttpServletResponse response) {
    response.setContentType(APPLICATION_JSON);
  }

  private void writeResponse(HttpServletResponse response, Object data) throws IOException {
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }
}


