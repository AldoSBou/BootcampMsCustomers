package com.bootcamp.btmscustomers.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YankiTransactionDto {

    private String originNumber;

    private String destinationNumber;

    private Double amount;

    private LocalDateTime createAt;

    private String idDebitCardNumber;

}
