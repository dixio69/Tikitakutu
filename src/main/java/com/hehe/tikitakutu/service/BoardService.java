package com.hehe.tikitakutu.service;

import com.hehe.tikitakutu.dto.Ai;
import com.hehe.tikitakutu.dto.Human;
import com.hehe.tikitakutu.dto.Player;
import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.repository.CellRepository;
import com.hehe.tikitakutu.repository.SettingRepository;
import com.hehe.tikitakutu.util.NumberUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardService {
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private CellRepository cellRepository;

    public List<Cell> getCellsBySessionId(String sessionId) {
        return cellRepository.findBySessionId(sessionId);
    }

    public Cell findBySessionIdLocation(String sessionId, int locationX, int locationY) {
        return cellRepository.findBySessionIdLocation(sessionId, locationX, locationY);
    }

    public void playerCheckCell(String buttonId, String sessionId) {
        String[] locStr = buttonId.replace("b", "").split("e");
        int x = Integer.parseInt(locStr[0]);
        int y = Integer.parseInt(locStr[1]);

        changeCellValue(sessionId, x, y, new Human());
    }

    private void changeCellValue(String sessionId, int x, int y, Player player) {
        Cell cell = findBySessionIdLocation(sessionId, x, y);
        if (cell.getValue() == null) {
            cell.setValue(player.getCellValue());
            cellRepository.save(cell);
        }
    }

    public void checkRandom(String sessionId){
        List<Cell> availableCell = getCellsBySessionId(sessionId).stream()
                .filter(d -> d.getValue() == null).collect(Collectors.toList());
        if (availableCell.size() > 0) {
            int rand = NumberUtil.getRandomInt(0, availableCell.size());
            Cell cell = availableCell.get(rand);
            changeCellValue(sessionId, cell.getLocation_x(), cell.getLocation_y(), new Ai());
        }
    }
}
