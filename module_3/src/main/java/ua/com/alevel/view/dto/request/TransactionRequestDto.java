package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class TransactionRequestDto extends RequestDto{

    @Getter
    @Setter
    private BigDecimal transactionSum;

    @Getter
    @Setter
    private Long accountId;
}