package com.repository;

import com.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByLongitudeBetweenAndLatitudeBetween(Double lonMin, Double lonMax, Double latMin, Double latMax);
}
