package edu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.UserDao;
import edu.board.vo.UserVO;

@Service
public class UserServiceImple implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public int insertUser(UserVO vo) {
		int result = userDao.insert(vo);
		return result;
	}
	
}
