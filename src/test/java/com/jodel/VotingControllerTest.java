package com.jodel;

import com.controller.VotingController;
import com.model.Voting;
import com.service.VotingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VotingControllerTest {

    @Mock
    private VotingService votingService;

    @InjectMocks
    private VotingController votingController;

    private Voting voting;

    // Setup the voting object before each test
    @BeforeEach
    public void setUp() {

        voting = new Voting();
        voting.setAuthorId(1L);
        voting.setCommentId(1L);
        voting.setValue(1);
    }

    // Test the getVotings method
    @Test
    public void testGetVotings(){
        Long commentId = 1L;
        // Setup the mock to return a list with one element
        when(votingService.getVotings(commentId)).thenReturn(List.of(voting));
        // Call the getVotings method  
        ResponseEntity<List<Voting>> response = votingController.getVotings(commentId);

        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(voting, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(votingService).getVotings(commentId); // Ensure the service method was called
    }

    // Test the getLikes method
    @Test
    public void testGetLikes(){
        Long commentId = 1L;
        // Setup the mock to return a list with one element
        when(votingService.getLikes(commentId)).thenReturn(List.of(voting));

        // Call the getLikes method
        ResponseEntity<List<Voting>> response = votingController.getLikes(commentId);

        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(voting, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(votingService).getLikes(commentId); // Ensure the service method was called
    }

    // Test the getDislikes method
    @Test public void testGetDislikes(){
        Long commentId = 1L;
        // Setup the mock to return a list with one element
        when(votingService.getDislikes(commentId)).thenReturn(List.of(voting));

        // Call the getDislikes method
        ResponseEntity<List<Voting>> response = votingController.getDislikes(commentId);

        // Check the response
        assertEquals(1, response.getBody().size()); // Ensure the list has one element
        assertEquals(voting, response.getBody().get(0)); // Check the first element in the list
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(votingService).getDislikes(commentId); // Ensure the service method was called
    }

    // Test the upvote method
    @Test public void testUpvote(){
        // Setup the mock to return the voting
        when(votingService.upvote(voting)).thenReturn(voting);

        // Call the upvote method
        ResponseEntity<Voting> response = votingController.upvote(voting);

        // Check the response
        assertEquals(voting, response.getBody()); // Ensure the voting is returned
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(votingService).upvote(voting); // Ensure the service method was called
    }

    // Test the downvote method
    @Test public void testDownvote(){
        // Setup the mock to return the voting
        when(votingService.downvote(voting)).thenReturn(voting);

        // Call the downvote method
        ResponseEntity<Voting> response = votingController.downvote(voting);

        // Check the response
        assertEquals(voting, response.getBody()); // Ensure the voting is returned
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        verify(votingService).downvote(voting); // Ensure the service method was called
    }
}
