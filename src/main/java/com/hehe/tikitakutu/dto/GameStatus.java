package com.hehe.tikitakutu.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameStatus {
    private Boolean winner;
    private boolean isFinished;
}
