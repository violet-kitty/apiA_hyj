package edu.board.dao;

import org.springframework.stereotype.Repository;

//객체를 ioc가 가지고 있게 하기.
//이 dao는 이 시스템이 아니라 다른 외부(DB)에 접근함
//외부에 접근해 연결하는 객체들은 repository라고 함
//@Component 하위 어노테이션 중 같은 어노테이션 쓴 (ex)@service,@controller,@repository 등) 객체들을 한 그룹으로 묶고
//관련 작업이 요청되면 관련된 그룹을 뒤짐
@Repository
public class BoardDao {
	
}
