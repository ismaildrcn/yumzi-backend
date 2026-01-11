package com.ismaildrcn.model.embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Allergens {

    private Boolean gluten;

    private Boolean peanuts;

    private Boolean treeNuts;

    private Boolean dairy;

    private Boolean eggs;

    private Boolean soy;

    private Boolean fish;

    private Boolean shellfish;
}
