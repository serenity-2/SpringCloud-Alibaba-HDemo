package com.alibaba.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: QianCQ star
 * @Date: 2020/6/26 15:39
 * @Description: 返回结果包装实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code; //状态码
    private String  message; //请求结果信息
    private T       data; //返回的数据

    public CommonResult(Integer code, String message)
    {
        this(code,message,null);
    }
}
