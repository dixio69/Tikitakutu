package com.hehe.tikitakutu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game extends BaseEntity {
    private int gridSize;
    private int winCondition;
    private Boolean winner;
    private boolean isFinished = false;
}
