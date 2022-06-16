package edu.board.service;

import edu.board.vo.UserVO;

public interface UserService {
	//보통 서비스는 하는 일 관련해서 이름을 지음
	int insertUser(UserVO vo);
}
