package com.superred.qiaok.esrestclient.controller;

import com.superred.qiaok.esrestclient.model.Book;
import com.superred.qiaok.esrestclient.model.Employee;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;


/**
 *
 *
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private RestClient client;

    /**
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/_bulk", method = RequestMethod.PUT)
    public ResponseEntity<String> batchAdd() throws IOException {
        Request request = new Request("POST", new StringBuilder("/bank/_bulk/").toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");
        String data = FileUtils.readFileToString(new File("d:/accounts.json"));
        System.out.println(data);
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(data));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/bulkApi", method = RequestMethod.PUT)
    public ResponseEntity<String> bulkApi() throws IOException {
        Request request = new Request("POST", new StringBuilder("/_bulk").toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");
        JSONObject param = new JSONObject();
        JSONObject index = new JSONObject();
        index.put("_index","test").put("_id","1");
        param.put("index",index);

        param.put("field1","value1");

        JSONObject delete = new JSONObject();
        delete.put("_index","test").put("_id","2");
        param.put("delete",delete);

        JSONObject create = new JSONObject();
        create.put("_index","test").put("_id","3");
        param.put("create",create);

        param.put("field1","value3");

        JSONObject update = new JSONObject();
        update.put("_index","test").put("_id","1");
        param.put("update",update);

        JSONObject doc = new JSONObject();
        doc.put("field2","value2");
        param.put("doc",doc);

        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
