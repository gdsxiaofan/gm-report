package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.FaultParam;
import com.huaxia.tongyong.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 故障日报相关接口
 * @author: gongdaoshun
 * @Date: 2017/12/10 22:35
 * @className: FaultController
 */
@RestController
@RequestMapping("/v1/fault")
@Slf4j
@Api(value = "FaultController", description = "故障日报相关的api")
public class FaultController {

    /**
     * 新增日报记录
     * @param faultParam
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addFault(@ApiParam @RequestBody FaultParam faultParam){
        return null;
    }


}
