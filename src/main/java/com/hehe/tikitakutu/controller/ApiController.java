package com.hehe.tikitakutu.controller;

import com.hehe.tikitakutu.dto.GameStatus;
import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.service.BoardService;
import com.hehe.tikitakutu.service.GameControlService;
import com.hehe.tikitakutu.util.SessionLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/")
@Log4j2
public class ApiController {

    @Autowired
    private GameControlService gameControlService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/player/check/{button-id}")
    public void playerCheckCell(@PathVariable(value = "button-id") String buttonId, HttpSession session) {
        boardService.playerCheckCell(buttonId, session.getId());
    }

    @GetMapping("/ai/check")
    public void aiCheckCell(HttpSession session) {
        boardService.checkRandom(session.getId());
    }

    @GetMapping("/status")
    public @ResponseBody GameStatus getGameStatus(HttpSession session) {
        Game game = gameControlService.get(session.getId());
        return GameStatus.builder()
                .isFinished(game.isFinished())
                .winner(game.getWinner())
                .build();
    }

    @GetMapping("/grid")
    public String getBoxesHtml(HttpSession session) {
        SessionLogger.logSession(session);
        Integer gridSize = gameControlService.get(session.getId()).getGridSize();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        Cell cell;
        String buttonId;
        for (int x = 1; x <= gridSize; x++) {
            stringBuilder.append("<tr>");
            for (int y = 1; y <= gridSize; y++) {
                buttonId = "b" + x + "e" + y;
                cell = boardService.findBySessionIdLocation(session.getId(), x, y);

                stringBuilder.append("<td><a id=\"" + buttonId + "\"");
                if (cell.getValue() == null) {
                    stringBuilder.append("onclick=\"cellClicked('" + buttonId + "')\" ");
                    stringBuilder.append("class=\"btn\">&nbsp;&nbsp;&nbsp;");
                } else {
                    stringBuilder.append(cell.getValue() != null ? "disabled=\"disabled\"" : "");
                    if (cell.getValue())
                        stringBuilder.append("class=\"btn btn-danger\">X");
                    else if (!cell.getValue())
                        stringBuilder.append("class=\"btn btn-success\">O");
                }

                stringBuilder.append("</a></td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        boardService.calculateWin(session.getId());
        return stringBuilder.toString();
    }
}
