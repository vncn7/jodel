package com.repository;

import com.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Native query to find posts within a 10km radius using Haversine formula
    @Query(value = 
        "SELECT * FROM Tpost " +
        "WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * " +
        "cos(radians(longitude) - radians(:longitude)) + " +
        "sin(radians(:latitude)) * sin(radians(latitude)))) <= 10", nativeQuery = true)
        // The query above is a native query that uses the Haversine formula to calculate the distance between two points on the Earth's surface.
    List<Post> findPostsWithin10km(
        @Param("longitude") double longitude, 
        @Param("latitude") double latitude
    );
}