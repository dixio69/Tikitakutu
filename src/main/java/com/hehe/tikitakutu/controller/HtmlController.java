package com.hehe.tikitakutu.controller;

import com.hehe.tikitakutu.dto.GameSetting;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.service.GameControlService;
import com.hehe.tikitakutu.util.SessionLogger;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
public class HtmlController {
    @Autowired
    private GameControlService gameControlService;

    @GetMapping("/")
    public String getGameSetting(Model model, HttpSession session) {
        Game game = gameControlService.get(session.getId());
        game = game == null ? new Game() : game;
        model.addAttribute("data", GameSetting.builder()
                .winning(game.getWinCondition())
                .size(game.getGridSize())
                .build());
        return "game-setting";
    }

    @PostMapping("/")
    public String processSetting(GameSetting gameSetting) {
        return "redirect:/play?s=" + gameSetting.getSize() + "&wc=" + gameSetting.getWinning();
    }

    @GetMapping("/play")
    public String getBoard(@RequestParam(value = "s") String size, @RequestParam(value = "wc") String winCondition, HttpSession session) {
        SessionLogger.logSession(session);
        if ((size != null && !StringUtils.isNumeric(size)) ||
                (winCondition != null && !StringUtils.isNumeric(winCondition))) {
            return getErrorPath();
        }

        Integer gridSize = null;
        Integer winConditionInt = null;
        try {
            gridSize = Integer.parseInt(size);
            winConditionInt = Integer.parseInt(winCondition);
            if (gridSize > 10 || gridSize < 3)
                return getErrorPath();
            if (winConditionInt > gridSize)
                return getErrorPath();
        } catch (NumberFormatException e) {
            return getErrorPath();
        }
        gridSize = gridSize == null ? 3 : gridSize;

        Game game = new Game();
        game.setGridSize(gridSize);
        game.setWinCondition(winConditionInt);
        game.setSessionId(session.getId());
        gameControlService.play(game);
        return "game";
    }

    private static String getErrorPath() {
        return "error";
    }
}
