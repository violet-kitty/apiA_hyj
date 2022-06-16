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
		//insert(어떤 sql인지(namespace를 포함한 쿼리 id),)
		//vo로 표현할 수 없는 여러건의 데이터는 hashmap으로 묶어서 하나로 넘겨야함
		//마이바티스는 데이터를 하나밖에 넘기지 못함
		int result = sqlSession.insert("edu.board.mapper.UserMapper.insert", vo);
		return result;
	}
}
