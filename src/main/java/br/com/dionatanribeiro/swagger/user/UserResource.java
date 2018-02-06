package br.com.dionatanribeiro.swagger.user;

import br.com.dionatanribeiro.swagger.exception.PostNotFoundException;
import br.com.dionatanribeiro.swagger.post.Post;
import br.com.dionatanribeiro.swagger.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int id) {
        List<Post> posts = service.findAllPostsByUser(id);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("user id-" + id);
        }

        return posts;
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public Post retrievePostByUser(@PathVariable int id, @PathVariable("post_id") int postId) {
        Post post = service.findOnePostByUser(id, postId);

        if (post == null) {
            throw new PostNotFoundException("user id-" + id + " post id-" + postId);
        }

        return post;
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post) {
        Post postSaved = service.savePost(id, post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{post_id}")
                .buildAndExpand(postSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }




}
