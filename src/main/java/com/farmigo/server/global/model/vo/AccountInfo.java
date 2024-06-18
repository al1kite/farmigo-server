package com.farmigo.server.global.model.vo;

import com.farmigo.server.global.annotation.EnumValue;
import com.farmigo.server.global.constant.Bank;
import com.farmigo.server.global.constant.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    @Field(MongoField.ACCOUNT_NUMBER)
    private String accountNumber;
    @EnumValue(enumClass = Bank.class, ignoreCase = true)
    private Bank bank;
}
