package com.nc13.react_board.Service;

import com.nc13.react_board.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {
    private final SqlSession SESSION;
    private final String NAMESPACE = "mapper.BoardMapper";
    private final int PAGE_SIZE = 20;

    @Autowired
    public BoardService(SqlSession session) {
        SESSION = session;
    }

    public BoardDTO selectOne(int id) {
        return SESSION.selectOne(NAMESPACE + ".selectOne", id);
    }

    public List<BoardDTO> selectAll(int pageNo) {
        HashMap<String, Integer> paramMap = new HashMap<>();
        paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);
        paramMap.put("size", PAGE_SIZE);
        return SESSION.selectList(NAMESPACE + ".selectList", paramMap);
    }

    public int selectMaxPage() {
        int maxPage = SESSION.selectOne(NAMESPACE + ".selectMaxPage");
        return maxPage % PAGE_SIZE == 0 ? maxPage / PAGE_SIZE : maxPage / PAGE_SIZE + 1;
    }

    public void insert(BoardDTO board) {
        SESSION.insert(NAMESPACE + ".insert", board);
    }

    public void update(BoardDTO board) {
        SESSION.update(NAMESPACE + ".update", board);
    }

    public void delete(int id) {
        SESSION.delete(NAMESPACE + ".delete", id);
    }
}
