package com.nc13.react_board.Controller;

import com.nc13.react_board.Service.BoardService;
import com.nc13.react_board.model.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(BOARD_SERVICE.selectOne(id));
        return BOARD_SERVICE.selectOne(id);
    }
}
