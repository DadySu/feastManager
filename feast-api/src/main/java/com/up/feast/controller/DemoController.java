package com.up.feast.controller;

import base.BaseResponse;
import com.up.feast.annotation.TestAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description DemoController
 * @author liub
 * @date 2018-11-28 13:53
 */
@RestController
@Api(tags = "后台管理系统app")
@Slf4j
public class DemoController {

    @ApiOperation(value="获取用户列表",notes = "用于admin系统")
    @ApiImplicitParams({@ApiImplicitParam(name="userId",required = true,dataType = "int",paramType = "query"),
                        @ApiImplicitParam(name="roleId",required = true,dataType = "int",paramType = "query")})
    @PostMapping("/queryUserList")
    public String demo(@RequestBody String requestBody){
        log.info("requestBody is {}",requestBody);

        BaseResponse response = BaseResponse.newInstanceSuccess("成功");
        return response.toString();
    }

    @GetMapping("/test")
    @TestAnnotation(address = "shenyang", condition = TestAnnotation.age.kid)
    public String test() {
        log.info("info log");
        log.debug("debug log");
        return "test";
    }
}
