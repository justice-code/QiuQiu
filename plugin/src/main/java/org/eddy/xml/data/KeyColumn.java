package org.eddy.xml.data;

import lombok.*;

/**
 * Created by eddy on 2017/5/2.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KeyColumn {

    /**
     * 客户端查询sql分库分表传入的表名
     */
    @NonNull
    private String table;

    /**
     * 客户端查询sql分库分表传入的列名
     */
    @NonNull
    private String column;

    /**
     * 客户端查询sql分库分表传入的列取值类型
     */
    @NonNull
    private String javaType;
}
