package com.jodel;

import com.model.Voting;
import com.repository.VotingRepository;
import com.service.VotingService;
import com.repository.CommentRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VotingServiceTest {

    @Mock
    private VotingRepository votingRepository; 

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private VotingService votingService; 

    private Voting voting;

    @BeforeEach
    public void setUp() {
        voting = new Voting();
        voting.setAuthorId(1L);
        voting.setCommentId(1L);
        voting.setValue(1);
    }

    @Test
    public void testGetVotings() {
        Long commentId = 1L;
        when(votingRepository.findByCommentId(commentId)).thenReturn(List.of(voting));
    
        List<Voting> votings = votingService.getVotings(commentId);
    
        assertEquals(1, votings.size());
        assertEquals(voting, votings.get(0));
    
        verify(votingRepository).findByCommentId(commentId);
    }

    @Test
    public void testGetLikes() { 
        Long commentId = 1L;
        when(votingRepository.findByCommentIdAndValue(commentId, 1)).thenReturn(List.of(voting));
    
        List<Voting> likes = votingService.getLikes(commentId);
    
        assertEquals(1, likes.size());
        assertEquals(voting, likes.get(0));
    
        verify(votingRepository).findByCommentIdAndValue(commentId, 1);
    }

    @Test
    public void testGetDislikes() {
        Long commentId = 1L;
        when(votingRepository.findByCommentIdAndValue(commentId, -1)).thenReturn(List.of(voting));
    
        List<Voting> dislikes = votingService.getDislikes(commentId);
    
        assertEquals(1, dislikes.size());
        assertEquals(voting, dislikes.get(0));
    
        verify(votingRepository).findByCommentIdAndValue(commentId, -1);
    }

   @Test
    public void testUpvote() {
        // Prepare mock behavior
        when(votingRepository.findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId()))
            .thenReturn(Optional.of(voting));
        
        // Mock the save operation to return the updated voting
        when(votingRepository.save(any(Voting.class))).thenReturn(voting); 

        // Perform upvote
        Voting upvotedVoting = votingService.upvote(voting);

        // Verify the result
        assertNotNull(upvotedVoting);
        assertEquals(1, upvotedVoting.getValue());
        verify(votingRepository).findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId());
        verify(votingRepository).save(voting);
    }

    @Test
    public void testDownvote() {
        // Prepare mock behavior
        when(votingRepository.findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId()))
            .thenReturn(Optional.of(voting));
        
        // Mock the save operation to return the updated voting
        when(votingRepository.save(any(Voting.class))).thenReturn(voting); 

        // Perform downvote
        Voting downvotedVoting = votingService.downvote(voting);

        // Verify the result
        assertNotNull(downvotedVoting);
        assertEquals(-1, downvotedVoting.getValue());
        verify(votingRepository).findByCommentIdAndAuthorId(voting.getCommentId(), voting.getAuthorId());
        verify(votingRepository).save(voting);
    }
    
}
