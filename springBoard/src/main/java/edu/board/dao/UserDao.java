package edu.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.UserVO;

@Repository
public class UserDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insert(UserVO vo) {
		//insert(� sql����(namespace�� ������ ���� id),)
		//vo�� ǥ���� �� ���� �������� �����ʹ� hashmap���� ��� �ϳ��� �Ѱܾ���
		//���̹�Ƽ���� �����͸� �ϳ��ۿ� �ѱ��� ����
		int result = sqlSession.insert("edu.board.mapper.UserMapper.insert", vo);
		return result;
	}
}
