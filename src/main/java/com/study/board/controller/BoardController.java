package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    //게시글 폼 화면
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    //버튼 -> 게시글 작성 처리
    @PostMapping("/board/writepro")
    public String boardWrotePro(Board board){
        boardService.write(board);
        return "";
    }
    //게시물 리스트
    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list" , boardService.boardList());
        return "boardlist";
    }

    //게시글 불러오기  localhost:8080/board/view?id=1
    @GetMapping("/board/view")
    public String boardView(Model model,  Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }
}
