package com.hehe.tikitakutu.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cell extends BaseEntity {
    private int location_x;
    private int location_y;
    private Boolean value;
}
