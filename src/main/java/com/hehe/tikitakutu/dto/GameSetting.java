package com.hehe.tikitakutu.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameSetting {
    private Integer size;
    private Integer winning;
}
