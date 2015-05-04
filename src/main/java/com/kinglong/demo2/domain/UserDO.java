package com.kinglong.demo2.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by sony on 2015/4/29.
 */
@Component
@Scope
@Data
public class UserDO {
    private String name;
    private String sex;
}
