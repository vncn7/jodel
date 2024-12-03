package com.repository;

import com.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByLongitudeBetweenAndLatitudeBetween(Double lonMin, Double lonMax, Double latMin, Double latMax);
}
