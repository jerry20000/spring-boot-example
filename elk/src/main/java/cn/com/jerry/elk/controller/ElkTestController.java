package cn.com.jerry.elk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Jerry
 * @date ：Created in 2020/4/27 14:38
 */
@Slf4j
@RestController
@RequestMapping("/api/elk")
@Api(description = "elk相关接口")
@CrossOrigin(origins = "*")
public class ElkTestController {

    @ApiOperation(value = "query")
    @GetMapping("query")
    private Object queryAll() {
        log.info("elk相关接口22");
        log.debug("elk相关接口44 debug");
        log.error("elk相关接口55 error");
        return "success";
    }
}
