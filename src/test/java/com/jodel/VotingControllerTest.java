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

    @BeforeEach
    public void setUp() {

        voting = new Voting();
        voting.setAuthorId(1L);
        voting.setCommentId(1L);
        voting.setValue(1);
    }

    @Test
    public void testGetVotings(){
        Long commentId = 1L;
        when(votingService.getVotings(commentId)).thenReturn(List.of(voting));

        ResponseEntity<List<Voting>> response = votingController.getVotings(commentId);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(voting, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(votingService).getVotings(commentId);
    }

    @Test
    public void testGetLikes(){
        Long commentId = 1L;
        when(votingService.getLikes(commentId)).thenReturn(List.of(voting));

        ResponseEntity<List<Voting>> response = votingController.getLikes(commentId);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(voting, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(votingService).getLikes(commentId);
    }

    @Test public void testGetDislikes(){
        Long commentId = 1L;
        when(votingService.getDislikes(commentId)).thenReturn(List.of(voting));

        ResponseEntity<List<Voting>> response = votingController.getDislikes(commentId);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(voting, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(votingService).getDislikes(commentId);
    }

    @Test public void testUpvote(){
        when(votingService.upvote(voting)).thenReturn(voting);

        ResponseEntity<Voting> response = votingController.upvote(voting);

        assertEquals(voting, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(votingService).upvote(voting);
    }

    @Test public void testDownvote(){
        when(votingService.downvote(voting)).thenReturn(voting);

        ResponseEntity<Voting> response = votingController.downvote(voting);

        assertEquals(voting, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(votingService).downvote(voting);
    }
}
