package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }
    //게시글 수정 폼
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    //게시글 수정 처리
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        //기존의 board 에 수정된 데이터를 받아와 덮어씌움
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp);
        return "redirect:/board/list";

    }

}
