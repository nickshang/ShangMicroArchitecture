package com.shang.upms.controller;

import com.shang.upms.dao.entity.Book;
import com.shang.upms.dao.entity.Reader;
import com.shang.upms.dao.repository.ReadingListRepository;
import com.shang.upms.AmazonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器
 * Created by Think on 2017/6/9.
 */
@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonProperties;

    @Autowired
    public ReadingListController(
            ReadingListRepository readingListRepository,
            AmazonProperties amazonProperties) {
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
    }

    //  Reader ->  ReaderHandlerMethodArgumentResolver  自定义解析器实现请求参数绑定方法参数
    @RequestMapping(method = RequestMethod.GET, value = {"/readingList"})
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";
    }


    @GetMapping(value = "/test")
    @ResponseBody
    public String test(Book book, Model model){
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}