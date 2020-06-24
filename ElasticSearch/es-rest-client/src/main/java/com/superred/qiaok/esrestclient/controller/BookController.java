package com.superred.qiaok.esrestclient.controller;

import com.superred.qiaok.esrestclient.model.Book;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


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
@RequestMapping("/rest/book")
public class BookController {

    @Autowired
    private RestClient client;

//    // RequestOptions类保存应在同一应用程序中的多个请求之间共享的部分请求
//    private static final RequestOptions COMMON_OPTIONS;
//
//    static {
//        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        // 添加所有请求所需的任何标头。
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        // 自定义响应使用者
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
//        COMMON_OPTIONS = builder.build();
//    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public ResponseEntity<String> go() {
        return new ResponseEntity<>("go", HttpStatus.OK);
    }

    /**
     * 同步执行HTTP请求
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/es", method = RequestMethod.GET)
    public ResponseEntity<String> getEsInfo() throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200
        Request request = new Request("GET", "/");
//        // 设置其他一些参数比如美化json
//        request.addParameter("pretty", "true");
//        // 设置请求体
//        request.setEntity(new NStringEntity("{\"json\":\"text\"}", ContentType.APPLICATION_JSON));
//        // 还可以将其设置为String，默认为ContentType为application/json
//        request.setJsonEntity("{\"json\":\"text\"}");

        /*
        performRequest是同步的，将阻塞调用线程并在请求成功时返回Response，如果失败则抛出异常
        内部属性可以取出来通过下面的方法
         */
        Response response = client.performRequest(request);
//        // 获取请求行
//        RequestLine requestLine = response.getRequestLine();
//        // 获取host
//        HttpHost host = response.getHost();
//        // 获取状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        // 获取响应头
//        Header[] headers = response.getHeaders();
        // 获取响应体
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


    /**
     * 异步执行HTTP请求
     * @return
     */
    @RequestMapping(value = "/es/asyn", method = RequestMethod.GET)
    public ResponseEntity<String> asynchronous() {
        Request request = new Request(
                "GET",
                "/");
        client.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("异步执行HTTP请求并成功");
            }

            @Override
            public void onFailure(Exception exception) {
                System.out.println("异步执行HTTP请求并失败");
            }
        });
        return null;
    }

    /**
     * 并行异步执行HTTP请求
     */
    @RequestMapping(value = "/ps", method = RequestMethod.POST)
    public void parallAsyn(@RequestBody Book[] documents) {
        /*final CountDownLatch latch = new CountDownLatch(documents.length);
        for (int i = 0; i < documents.length; i++) {
            Request request = new Request("PUT", "/posts/doc/" + i);
            //let's assume that the documents are stored in an HttpEntity array
            request.setEntity(documents[i]);
            client.performRequestAsync(
                    request,
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {

                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {

                            latch.countDown();
                        }
                    }
            );
        }
        latch.await();*/
    }

    /**
     * 添加ES对象, Book的ID就是ES中存储的document的ID，所以最好不要为空，自定义生成的ID太浮夸
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Book book) throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
        // endpoint直接指定为index/type的形式
        Request request = new Request("POST", new StringBuilder("/book/book/").
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
     * 根据id获取ES对象
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getBookById(@PathVariable("id") String id) {
        Request request = new Request("GET", new StringBuilder("/book/book/").
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
            return new ResponseEntity<>("can not found the book by your id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * 根据id更新Book
     *
     * @param id
     * @param book
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook(@PathVariable("id") String id, @RequestBody Book book) throws IOException {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/book/").
                append(id).append("/_update").toString());
        request.addParameter("pretty", "true");

        // 将数据丢进去，这里一定要外包一层“doc”，否则内部不能识别
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doc", new JSONObject(book));
        request.setEntity(new NStringEntity(jsonObject.toString()));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
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


    @RequestMapping(value = "/_search/{type}", method = RequestMethod.GET)
    public ResponseEntity<String> getAll(@PathVariable("type") String type) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/").append(type).append("/_search").toString());
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
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search?q=name:").append(name).toString());
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
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition2", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition2(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject match = new JSONObject();
        match.put("name",name);
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
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition3", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition3(@RequestParam("name") String name,@RequestParam("id") int id) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject bool = new JSONObject();

        JSONObject must = new JSONObject();
        JSONObject match = new JSONObject();
        match.put("name",name);
        must.put("match",match);
        bool.put("must", must);

        JSONObject filter = new JSONObject();
        JSONObject range = new JSONObject();
        JSONObject age = new JSONObject();
        age.put("gt",id);
        range.put("id",age);
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
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition4", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition4(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("name",name);

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
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition5", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition5(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("name",name);

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
     *
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition6", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition6(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("name",name);

        query.put("match_phrase", match);
        param.put("query",query);

        JSONObject highlight = new JSONObject();

        JSONObject fields = new JSONObject();
        JSONObject n = new JSONObject();
        fields.put("name",n);

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
     * 分析
     * 终于到了最后一个业务需求：支持管理者对员工目录做分析。
     * Elasticsearch 有一个功能叫聚合（aggregations），允许我们基于数据生成一些精细的分析结果。
     * 聚合与 SQL 中的 GROUP BY 类似但更强大。
     *
     * 举个例子，挖掘出员工中最受欢迎的兴趣爱好：
     *
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchByCondition7", method = RequestMethod.GET)
    public ResponseEntity<String> searchByCondition7(@RequestParam("name") String name) throws IOException {
        Request request = new Request("GET", new StringBuilder("/book/book/_search").toString());
        request.addParameter("pretty", "true");

        JSONObject param = new JSONObject();
        JSONObject query = new JSONObject();

        JSONObject match = new JSONObject();
        match.put("name",name);

        query.put("match_phrase", match);
        param.put("query",query);

        JSONObject highlight = new JSONObject();

        JSONObject fields = new JSONObject();
        JSONObject n = new JSONObject();
        fields.put("name",n);

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


}
