package com.hehe.tikitakutu.controller;

import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.service.BoardService;
import com.hehe.tikitakutu.service.GameControlService;
import com.hehe.tikitakutu.util.SessionLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/")
@Log4j2
public class ApiController {

    @Autowired
    private GameControlService gameControlService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/size")
    public Integer getSize(HttpSession httpSession) {
        return gameControlService.get(httpSession.getId()).getGridSize();
    }

    @GetMapping("/player/check/{button-id}")
    public void playerCheckCell(@PathVariable(value = "button-id") String buttonId, HttpSession session) {
        boardService.playerCheckCell(buttonId, session.getId());
    }

    @GetMapping("/ai/check")
    public void aiCheckCell(HttpSession session) {
        boardService.checkRandom(session.getId());
    }

    @GetMapping("/boxes")
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
        return stringBuilder.toString();
    }
}
