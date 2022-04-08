package com.hehe.tikitakutu.controller;

import com.hehe.tikitakutu.entity.GameSetting;
import com.hehe.tikitakutu.service.GameControlService;
import com.hehe.tikitakutu.util.SessionLogger;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
public class HtmlController {
    @Autowired
    private GameControlService gameControlService;

    @GetMapping("/{size}")
    public String getBoard(@PathVariable(value = "size", required = false) String size, HttpSession session) {
        SessionLogger.logSession(session);
        if (size != null && !StringUtils.isNumeric(size)) {
            return getErrorPath();
        }

        Integer gridSize = null;
        try {
            gridSize = Integer.parseInt(size);
            if (gridSize > 10 || gridSize < 3)
                return getErrorPath();
        } catch (NumberFormatException e) {
            return getErrorPath();
        }
        gridSize = gridSize == null ? 3 : gridSize;

        GameSetting gameSetting = new GameSetting();
        gameSetting.setGridSize(gridSize);
        gameSetting.setSessionId(session.getId());
        gameControlService.play(gameSetting);
        return "board";
    }

    private static String getErrorPath() {
        return "error";
    }
}
