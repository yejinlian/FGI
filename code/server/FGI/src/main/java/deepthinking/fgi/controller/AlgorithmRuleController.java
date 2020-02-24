package deepthinking.fgi.controller;

import com.alibaba.fastjson.JSONArray;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 11:20
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operatorMaintenance")
@Api(value = "04 算法规则编辑和导入导出操作API", description = "算法规则操作页面等相关接口")
public class AlgorithmRuleController {

    @Resource
    TableRoleService tableRoleService;


    @GetMapping("/readAlgorithmRuleFromFile")
    @ApiOperation(value = "04-01 导入算法规则", notes = "导入算法规则", httpMethod = "GET")
    @ApiImplicitParam(name = "filename", value = "文件路径", dataType = "string", paramType = "query", required = true)
    public Boolean readAlgorithmRuleFromFile(String filename){
        return tableRoleService.batchInsert(filename);
    }

    @GetMapping("/saveAlgorithmRule2File")
    @ApiOperation(value = "04-02 导出算法规则", notes = "导出算法规则", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "规则编号", dataType = "integer", paramType = "query", required = true)
    public void saveAlgorithmRule2File(Integer id, HttpServletResponse response){
        String filename = UUID.randomUUID().toString();
        String path = FileUtils.getConTextPath() + "/WEB-INF/" + filename;
        try {
            FileUtils.writeFile(path, JSONArray.toJSON(tableRoleService.selectByPrimaryKey(id)).toString().getBytes());
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("算法规则导出.txt", "UTF-8"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
        } finally {
            try {
                File f = new File(path);
                f.delete();
            } catch (Exception e) {
            }
        }
    }
}
