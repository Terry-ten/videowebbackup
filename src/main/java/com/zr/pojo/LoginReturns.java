package com.zr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/11 21:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReturns {
    private String token;
    private Object object;
}
