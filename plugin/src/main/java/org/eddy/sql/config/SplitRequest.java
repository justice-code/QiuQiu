package org.eddy.sql.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by eddy on 2017/6/12.
 */
@Getter
@Setter
@AllArgsConstructor
public class SplitRequest {

    @NonNull
    private Object param;

    @NonNull
    private String ognl;
}
