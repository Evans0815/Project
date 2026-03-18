package com.evans.project.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SortablePageParam extends PageParam {

    private List<SortingField> sortingFields;

}
