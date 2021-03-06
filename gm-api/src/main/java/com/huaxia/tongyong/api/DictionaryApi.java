package com.huaxia.tongyong.api;

import com.huaxia.tongyong.model.DictionaryInfo;
import com.huaxia.tongyong.service.DictionaryBiz;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/19 21:28
 * @className: DictionaryApi
 */
@RestController
@RequestMapping("/v1/dictionary")
@Slf4j
@Api(value = "DictionaryApi" ,description = "字典相关接口")
public class DictionaryApi {

    @Autowired
    private DictionaryBiz dictionaryBiz;

    /**
     * 根据字典类型获取对应的字典数据
     * @param type
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ApiOperation("根据字典类型获取对应的字典集合")
    public JsonResult<List<DictionaryInfo>> getDictionaryInfoByType(
            @RequestParam("type")Integer type
    ){
        JsonResult<List<DictionaryInfo>> jsonResult = new JsonResult<>();
        List<DictionaryInfo> list = dictionaryBiz.getDictionaryInfoList(type);
        jsonResult.setData(list);
        if(CollectionUtils.isEmpty(list)){
            jsonResult.setCode(0);
            jsonResult.setMessage("no_data");
        }
        return jsonResult;
    }
}
