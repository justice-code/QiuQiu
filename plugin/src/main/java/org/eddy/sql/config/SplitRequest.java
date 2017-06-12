package org.eddy.sql.config;

import lombok.*;

/**
 * Created by eddy on 2017/6/12.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SplitRequest {

    @NonNull
    private String ognl;
}
