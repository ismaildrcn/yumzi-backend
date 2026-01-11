package com.ismaildrcn.model.embeddable;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccounts {

    private Map<String, BankAccountsBuilder> accounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankAccountsBuilder {
        private String bankName;

        private String iban;

        private String accountHolderName;

        private String accountNumber;

        private String branchCode;

        private String swiftCode;
    }

}
