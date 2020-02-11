package com.cos.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.ReturnCode;
import com.cos.blog.model.comment.dto.ReqDetailDto;
import com.cos.blog.model.comment.dto.RespDetailDto;
import com.cos.blog.model.user.User;
import com.cos.blog.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private HttpSession session;

	public List<RespDetailDto> 댓글목록보기(int postId) {
		return commentRepository.findByPostId(postId);
		// commentRepository

	}

	public RespDetailDto 댓글쓰기(ReqDetailDto dto) {
		int result = commentRepository.save(dto);

		if (result == 1) { // 댓글 작성 o
			// select
			return commentRepository.findById(dto.getId());
		} else { // 댓글 작성 x
			return null;
		}
	}

	public int 댓글삭제(int id) {

		// 댓글 작성자
		RespDetailDto comment = commentRepository.findById(id);

		// 현재 접속자
		User principal = (User) session.getAttribute("principal");

		if (comment.getUserId() == principal.getId()) {
			return commentRepository.delete(id);
		} else {
			return ReturnCode.권한없음;
		}
	}
}
