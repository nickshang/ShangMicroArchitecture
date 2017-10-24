package com.shang.springboot.readinglist.controller;

import com.shang.springboot.readinglist.AmazonProperties;
import com.shang.springboot.readinglist.entity.Book;
import com.shang.springboot.readinglist.entity.Reader;
import com.shang.springboot.readinglist.exceptionhandle.BookNotFoundException;
import com.shang.springboot.readinglist.repository.ReadingListRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.shang.springboot.readinglist.exceptionhandle.Error;

import java.util.List;

/**
 * RESTful API
 * Created by Think on 2017/10/23.
 */
@RestController
@RequestMapping("/book")
@Api(value = "/book", description = "book相关操作")
public class ReadingListControllerRest {

    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonProperties;

    @Autowired
    public ReadingListControllerRest(
            ReadingListRepository readingListRepository,
            AmazonProperties amazonProperties) {
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
    }

    @ApiOperation(value="获取用户下BOOK信息", notes="获取用户下BOOK信息",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "reader", value = "Reader", required = true, dataType = "Reader"),
            @ApiImplicitParam(paramType = "body", name = "model", value = "Spring MVC Model",  dataType = "Model")
    })
    @ApiResponses({
                     @ApiResponse(code=400,message="请求参数没填好"),
                     @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
                 })
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Book> readersBooks(@ModelAttribute Reader reader, Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return readingList;
    }

    @ApiOperation(value="保存BOOK", notes="保存BOOK")
    @ApiImplicitParam(paramType="path", name = "id", value = "详细实体BOOK", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@PathVariable Long id){
        if(id == -1)  throw  new BookNotFoundException(id);                             // 对应异常处理

        Book  book =   new Book();
        book.setId(id);

        return book;
    }

    /**
     * 异常处理
     * @param bookNotFoundException
     * @return
     */
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error bookNotFound(BookNotFoundException bookNotFoundException){
        long id = bookNotFoundException.getId();
        return new Error(id, "Book [" + id +"] not found");
    }

}
