package com.thd.cartoon.dto.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
@Data
@EqualsAndHashCode
public class SearchRequest {
    protected Boolean voided;
    protected String keyword;
    protected Integer pageSize;
    protected Integer pageIndex;
}
