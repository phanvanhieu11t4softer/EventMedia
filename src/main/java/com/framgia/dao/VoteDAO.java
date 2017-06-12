package com.framgia.dao;

import com.framgia.model.Vote;

public interface VoteDAO {
	public void create(Vote vote);

	public void deleteVote(Vote vote);

	public Vote findVoteToDelete(Integer idImage, Integer idUser);
}
