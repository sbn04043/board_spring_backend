package com.nc13.react_board.Controller;

import com.nc13.react_board.Service.BoardService;
import com.nc13.react_board.model.BoardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/board/")
public class BoardController {
    private final BoardService BOARD_SERVICE;

    public BoardController(BoardService boardService) {
        BOARD_SERVICE = boardService;
    }

    @GetMapping("showOne/{id}")
    public BoardDTO showOne(@PathVariable int id) {
        return BOARD_SERVICE.selectOne(id);
    }

    @GetMapping("showList/{pageNo}")
    public HashMap<String, Object> selectList(@PathVariable int pageNo) {
        HashMap<String, Object> resultMap = new HashMap<>();
        int maxPage = BOARD_SERVICE.selectMaxPage();
        if (maxPage < 1)
            maxPage = 1;

        int startPage, endPage;
        if (maxPage < 6) {
            startPage = 1;
            endPage = maxPage;
        } else if (pageNo < 4) {
            startPage = 1;
            endPage = 5;
        } else if (pageNo > maxPage - 3) {
            startPage = maxPage - 4;
            endPage = maxPage;
        } else {
            startPage = pageNo - 2;
            endPage = pageNo + 2;
        }

        resultMap.put("startPage", startPage);
        resultMap.put("endPage", endPage);
        resultMap.put("maxPage", maxPage);
        resultMap.put("curPage", pageNo);
        resultMap.put("boardList", BOARD_SERVICE.selectAll(pageNo));

        return resultMap;
    }

    @PostMapping("write")
    public HashMap<String, Object> write(@RequestBody BoardDTO board) {

        board.setWriterId(2);
        System.out.println(board);

        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            BOARD_SERVICE.insert(board);
            resultMap.put("result", "success");
            resultMap.put("resultId", board.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
        }

        return resultMap;
    }

    @PostMapping("update")
    public HashMap<String, Object> update(@RequestBody BoardDTO board) {
        HashMap<String, Object> resultMap = new HashMap<>();

        BOARD_SERVICE.update(board);

        resultMap.put("destId", board.getId());

        return resultMap;
    }

    @PostMapping("delete")
    public HashMap<String, Object> delete(@RequestBody int id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        System.out.println(id);

        return resultMap;
    }
}
