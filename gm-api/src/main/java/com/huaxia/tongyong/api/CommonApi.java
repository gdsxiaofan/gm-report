package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.SelectOption;
import com.huaxia.tongyong.service.CommonService;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/common")
@Api(value = "CommonApi" ,description = "公共接口")
public class CommonApi {

    @Resource
    private CommonService commonService;

    @ApiOperation(value="根据不同类型获得下拉选框",notes = "根据不同类型获得下拉选框",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/selected/get")
    public JsonResult<List<SelectOption>> getSelectOptionForList(
            @RequestParam("optionType")int optionType
    ){

        List<SelectOption> selectOptionList = commonService.getSelectOptionByType(optionType);
        return new JsonResult<>(1,"success",selectOptionList);
    }
}
