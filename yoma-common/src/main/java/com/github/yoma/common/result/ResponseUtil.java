package com.github.yoma.common.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (业务实现)
 *
 * @author yingw
 * @date 2019/12/11 11:13.
 */
public class ResponseUtil {


    public static <T> DetailResponse<T> detailSuccess(T detail) {

        DetailResponse<T> DetailResponse = new DetailResponse<T>();
        DetailResponse.setCode(ResponseEnum.SUCCESS.getCode());
        DetailResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
        DetailResponse.setData(detail);
        return DetailResponse;
    }

    /**
     * 分页列表响应
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> PageResponse<T> pageSuccess(PageInfo<T> pageInfo) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setCode(ResponseEnum.SUCCESS.getCode());
        pageResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
        pageResponse.setData(pageInfo);
        return pageResponse;
    }
    public static <T> ListResponse<T> listSuccess(List<T> list) {
        ListResponse<T> listResponse = new ListResponse<>();
        listResponse.setCode(ResponseEnum.SUCCESS.getCode());
        listResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
        listResponse.setData(list);
        return listResponse;
    }

    public static CommonResponse success() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(ResponseEnum.SUCCESS.getCode());
        commonResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
        return commonResponse;
    }


    public static CommonResponse error(Integer code, String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMsg(msg);
        return commonResponse;
    }
}
