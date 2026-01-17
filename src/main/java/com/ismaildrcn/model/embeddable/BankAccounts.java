package com.ismaildrcn.model.embeddable;

import java.util.List;

import com.ismaildrcn.model.enums.BankAccountType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccounts {
    private List<BankAccountInfo> accounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankAccountInfo {

        @Enumerated(EnumType.STRING)
        private BankAccountType accountType;

        private String bankName;

        private String iban;

        private String accountHolderName;

        private String accountNumber;

        private String branchCode;

        private String swiftCode;
    }

}
