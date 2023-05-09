package com.zr.utils;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/17 9:53
 */
public class PermissionUtil {
    public static boolean isAccess(Integer id, List<Integer> ids){
        for (Integer integer : ids) {
            if(integer.equals(id)){
                return true;
            }
        }
        return false;
    }

}
