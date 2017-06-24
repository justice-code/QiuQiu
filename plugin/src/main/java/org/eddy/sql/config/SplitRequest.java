package org.eddy.sql.config;

import lombok.*;
import org.apache.ibatis.annotations.Param;

/**
 * Created by eddy on 2017/6/12.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SplitRequest {

    @NonNull
    private String mapper;

    @NonNull
    private String[] ognl;

    private Object[] param;
}
