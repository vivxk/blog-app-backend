package com.backend.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Comment;
import com.backend.blog.entities.Post;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payloads.CommentDto;
import com.backend.blog.repositories.CommentRepo;
import com.backend.blog.repositories.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);
		return this.modelmapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(com);

	}

}
