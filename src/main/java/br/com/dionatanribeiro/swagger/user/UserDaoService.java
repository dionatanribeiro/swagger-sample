package br.com.dionatanribeiro.swagger.user;

import br.com.dionatanribeiro.swagger.post.Post;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static Integer usersCount = 3;

    static {
        users.add(new User(1, "Carlos", new Date()));
        users.add(new User(2, "Rafael", new Date()));
        users.add(new User(3, "Diego", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return getUserById(id);
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (id == user.getId()) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<Post> findAllPostsByUser(int id) {
        return Optional.ofNullable(getUserById(id))
                .map(User::getPosts).orElse(null);
    }

    public Post savePost(int id, Post post) {
        User user = getUserById(id);
        if (user != null) {
            if (post.getId() == null) {
                int size = user.getPosts().size();
                post.setId(++size);
            }
            user.getPosts().add(post);
            return post;
        }
        return null;
    }

    public Post findOnePostByUser(int id, int postId) {
        User user = getUserById(id);
        if (user != null) {
            return user.getPosts().stream()
                    .filter(p -> postId == p.getId())
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private User getUserById(int id) {
        return users.stream()
                .filter(u -> id == u.getId())
                .findFirst().orElse(null);
    }

}
