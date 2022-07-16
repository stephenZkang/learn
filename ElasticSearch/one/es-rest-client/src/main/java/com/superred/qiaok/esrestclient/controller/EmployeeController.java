package com.superred.qiaok.esrestclient.controller;

import com.superred.qiaok.esrestclient.model.Book;
import com.superred.qiaok.esrestclient.model.Employee;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 *
 * 第一个业务需求是存储员工数据。 这将会以 员工文档 的形式存储：一个文档代表一个员工。
 * 存储数据到 Elasticsearch 的行为叫做 索引 ，但在索引一个文档之前，需要确定将文档存储在哪里。
 *
 * 一个 Elasticsearch 集群可以 包含多个 索引 ，相应的每个索引可以包含多个 类型 。
 * 这些不同的类型存储着多个 文档 ，每个文档又有 多个 属性 。
 *
 */
@RestController
@RequestMapping("/megacorp/employee")
public class EmployeeController {

    @Autowired
    private RestClient client;

    /**
     * 添加ES对象, Book的ID就是ES中存储的document的ID，
     * 所以最好不要为空，自定义生成的ID太浮夸
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> add(@RequestBody Employee employee,@PathVariable("id") String id) throws IOException {
        Request request = new Request("POST", new StringBuilder("/megacorp/employee/").
                append(id).toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");

        JSONObject jsonObject = new JSONObject(employee);
        System.out.println(jsonObject.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(jsonObject.toString()));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * 根据id获取ES对象
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getById(@PathVariable("id") String id) {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/").
                append(id).toString());
        // 添加json返回优化
        request.addParameter("pretty", "true");
        Response response = null;
        String responseBody = null;
        try {
            // 执行HHTP请求
            response = client.performRequest(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            return new ResponseEntity<>("can not found the employee by your id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/_search", method = RequestMethod.GET)
    public ResponseEntity<String> getAll() throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");
        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * Query-string
     * 条件查询
     * @param last_name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition(@RequestParam("last_name") String last_name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search?q=last_name:").append(last_name).toString());
        request.addParameter("pretty", "true");

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }



    /**
     * Query-string 搜索通过命令非常方便地进行临时性的即席搜索 ，但它有自身的局限性（参见 轻量 搜索 ）。
     * Elasticsearch 提供一个丰富灵活的查询语言叫做 查询表达式 ， 它支持构建更加复杂和健壮的查询。
     *
     * 领域特定语言 （DSL）， 使用 JSON 构造了一个请求。我们可以像这样重写之前的查询所有名为 Smith 的搜索 ：
     * 条件查询
     * @param last_name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition2", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition2(@RequestParam("last_name") String last_name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject match = new JSONObject();
        match.put("last_name",last_name);
        query.put("match", match);
        param.put("query",query);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * 领域特定语言 （DSL）， 使用 JSON 构造了一个请求。我们可以像这样重写之前的查询所有名为 Smith 的搜索 ：
     * Filter
     * 条件查询
     * @param last_name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition3", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition3(@RequestParam("last_name") String last_name
            ,@RequestParam("age") int age) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject bool = new JSONObject();

        JSONObject must = new JSONObject();
        JSONObject match = new JSONObject();
        match.put("last_name",last_name);
        must.put("match",match);
        bool.put("must", must);

        JSONObject filter = new JSONObject();
        JSONObject range = new JSONObject();
        JSONObject ageObj = new JSONObject();
        ageObj.put("gt",age);
        range.put("age",ageObj);
        filter.put("range",range);
        bool.put("filter", filter);

        query.put("bool", bool);
        param.put("query",query);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


    /**
     *
     * 全文搜索
     * 截止目前的搜索相对都很简单：单个姓名，通过年龄过滤。
     * 现在尝试下稍微高级点儿的全文搜索——一项 传统数据库确实很难搞定的任务。
     *
     *  相关性得分
     *
     *      Elasticsearch 默认按照相关性得分排序，即每个文档跟查询的匹配程度。
     *      第一个最高得分的结果很明显：John Smith 的 about 属性清楚地写着 “rock climbing” 。
     *
     *      但为什么 Jane Smith 也作为结果返回了呢？原因是她的 about 属性里提到了 “rock” 。
     *      因为只有 “rock” 而没有 “climbing” ，所以她的相关性得分低于 John 的。
     *
     *      这是一个很好的案例，阐明了 Elasticsearch 如何 在 全文属性上搜索并返回相关性最强的结果。
     *      Elasticsearch中的 相关性 概念非常重要，也是完全区别于传统关系型数据库的一个概念，数据库中的一条记录要么匹配要么不匹配。
     *
     * @param about
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition4", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition4(@RequestParam("about") String about) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("about",about);

        query.put("match", match);
        param.put("query",query);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     *
     * 短语搜索
     * 找出一个属性中的独立单词是没有问题的，但有时候想要精确匹配一系列单词或者_短语_ 。
     * 比如， 我们想执行这样一个查询，仅匹配同时包含 “rock” 和 “climbing” ，并且 二者以短语 “rock climbing” 的形式紧挨着的雇员记录。
     *
     * 为此对 match 查询稍作调整，使用一个叫做 match_phrase 的查询：
     *
     * @param about
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByPhrase", method = RequestMethod.GET)
    public ResponseEntity<String> searchByPhrase(@RequestParam("about") String about) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("about",about);

        query.put("match_phrase", match);
        param.put("query",query);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     *
     * 高亮搜索
     * 许多应用都倾向于在每个搜索结果中 高亮 部分文本片段，以便让用户知道为何该文档符合查询条件。
     * 在 Elasticsearch 中检索出高亮片段也很容易。
     *
     * 再次执行前面的查询，并增加一个新的 highlight 参数：
     * https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-highlighting.html
     *
     * @param about
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByHighLight", method = RequestMethod.GET)
    public ResponseEntity<String> searchByHighLight(@RequestParam("about") String about) throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("about",about);

        query.put("match_phrase", match);
        param.put("query",query);

        JSONObject highlight = new JSONObject();

        JSONObject fields = new JSONObject();
        JSONObject n = new JSONObject();
        fields.put("about",n);

        highlight.put("fields", fields);
        param.put("highlight",highlight);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     *
     * 解决 Fielddata is disabled on text fields by default.
     * 解决
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/_mapping", method = RequestMethod.PUT)
    public ResponseEntity<String> _mapping() throws IOException {
        Request request = new Request("PUT", new StringBuilder("/megacorp/_mapping/employee/").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject properties = new JSONObject();

        JSONObject interests = new JSONObject();
        interests.put("type","text");
        interests.put("fielddata",true);
        properties.put("interests", interests);

        param.put("properties",properties);
        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     *
     * 分析
     * 终于到了最后一个业务需求：支持管理者对员工目录做分析。
     * Elasticsearch 有一个功能叫聚合（aggregations），允许我们基于数据生成一些精细的分析结果。
     * 聚合与 SQL 中的 GROUP BY 类似但更强大。
     *
     * 举个例子，挖掘出员工中最受欢迎的兴趣爱好：
     * 出现异常：Fielddata is disabled on text fields by default.
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByAggs", method = RequestMethod.GET)
    public ResponseEntity<String> searchByAggs() throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());

        JSONObject param = new JSONObject();
        JSONObject aggs = new JSONObject();

        JSONObject all_interests = new JSONObject();

        JSONObject terms = new JSONObject();
        terms.put("field","interests");
        all_interests.put("terms",terms);

        aggs.put("all_interests", all_interests);
        param.put("aggs",aggs);

        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


    /**
     *
     * 分析
     * 终于到了最后一个业务需求：支持管理者对员工目录做分析。
     * Elasticsearch 有一个功能叫聚合（aggregations），允许我们基于数据生成一些精细的分析结果。
     * 聚合与 SQL 中的 GROUP BY 类似但更强大。
     *
     * 举个例子，挖掘出员工中最受欢迎的兴趣爱好：
     * 出现异常：Fielddata is disabled on text fields by default.
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByInterestsAndAge", method = RequestMethod.GET)
    public ResponseEntity<String> searchByInterestsAndAge() throws IOException {
        Request request = new Request("GET", new StringBuilder("/megacorp/employee/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject aggs = new JSONObject();

        JSONObject all_interests = new JSONObject();

        JSONObject terms = new JSONObject();
        terms.put("field","interests");

        JSONObject aggs_age = new JSONObject();
        JSONObject avg_age = new JSONObject();
        JSONObject avg = new JSONObject();
        avg.put("field","age");
        avg_age.put("avg",avg);

        aggs_age.put("avg_age",avg_age);

        all_interests.put("terms",terms);
        all_interests.put("aggs",aggs_age);

        aggs.put("all_interests", all_interests);
        param.put("aggs",aggs);

        System.out.println(param.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(param.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * 集群健康
     * status 字段指示着当前集群在总体上是否工作正常。它的三种颜色含义如下：
     *
     * green
     *      所有的主分片和副本分片都正常运行。
     * yellow
     *      所有的主分片都正常运行，但不是所有的副本分片都正常运行。
     * red
     *      有主分片没能正常运行。
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> health() throws IOException {
        Request request = new Request("GET", new StringBuilder("/_cluster/health").toString());
        request.addParameter("pretty", "true");

        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());
        /**
         * {
         *   "cluster_name" : "elasticsearch",
         *   "status" : "green",
         *   "timed_out" : false,
         *   "number_of_nodes" : 3,
         *   "number_of_data_nodes" : 3,
         *   "active_primary_shards" : 15,
         *   "active_shards" : 30,
         *   "relocating_shards" : 0,
         *   "initializing_shards" : 0,
         *   "unassigned_shards" : 0,
         *   "delayed_unassigned_shards" : 0,
         *   "number_of_pending_tasks" : 0,
         *   "number_of_in_flight_fetch" : 0,
         *   "task_max_waiting_in_queue_millis" : 0,
         *   "active_shards_percent_as_number" : 100.0
         * }
         */

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }



    /**
     * 添加ES对象, Book的ID就是ES中存储的document的ID，所以最好不要为空，自定义生成的ID太浮夸
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    public ResponseEntity<String> addObjAndType(@RequestBody Book book,@PathVariable("type") String type) throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
        // endpoint直接指定为index/type的形式
        Request request = new Request("POST", new StringBuilder("/book/").append(type).append("/").
                append(book.getId()).toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");

        JSONObject jsonObject = new JSONObject(book);
        System.out.println(jsonObject.toString());
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        request.setEntity(new NStringEntity(jsonObject.toString()));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }




    /**
     * 使用脚本更新Book
     * @param id
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/update2/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook2(@PathVariable("id") String id, @RequestParam("name") String name) throws IOException {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/book/").
                append(id).append("/_update").toString());
        request.addParameter("pretty", "true");

        JSONObject jsonObject = new JSONObject();
        // 创建脚本语言，如果是字符变量，必须加单引号
        StringBuilder op1 = new StringBuilder("ctx._source.name=").append("'" + name + "'");
        jsonObject.put("script", op1);

        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) throws IOException {
        Request request = new Request("DELETE", new StringBuilder("/book/book/").append(id).toString());
        request.addParameter("pretty", "true");
        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }












}
