package com.hehe.tikitakutu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell extends BaseEntity {
    private int location_x;
    private int location_y;
    private Boolean value;
}
