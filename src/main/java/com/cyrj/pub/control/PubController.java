package com.cyrj.pub.control;

import com.cyrj.common.control.BaseController;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.db.DynamicDataSource;
import com.cyrj.common.util.IgnoreSecurity;
import com.cyrj.common.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/pub")
@Api(description = "公共接口")
public class PubController extends BaseController {

    @ApiOperation(value = "清除数据源")
    @IgnoreSecurity(val = true)
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public Response clear(HttpServletRequest request,
                         @ApiParam(value = "切换数据库", required = true) @RequestParam(required = true, value = "dbName") String dbName) {
        DataSourceHolder.setDataSource(dbName);
        DynamicDataSource.clearDataSource(dbName);
        Response response = new Response();
        return response.success("成功");
    }

    @ApiOperation(value = "在线检测")
    @IgnoreSecurity(val = true)
    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public Response online(HttpServletRequest request) {
        Response response = new Response();
        return response.success("成功");
    }
}
