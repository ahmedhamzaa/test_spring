package com.vermeg.bookstore.book_store_vermeg.rest;

import com.vermeg.bookstore.book_store_vermeg.model.BookDTO;
import com.vermeg.bookstore.book_store_vermeg.model.OrdersDTO;
import com.vermeg.bookstore.book_store_vermeg.repos.BookRepository;
import com.vermeg.bookstore.book_store_vermeg.service.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
    private final BookService bookService;


    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

  /*  @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }
      @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable final Integer id) {
        return ResponseEntity.ok(bookService.get(id));
    }
        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable final Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
        @PostMapping
    public ResponseEntity<Integer> createBook(@RequestBody @Valid final BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.create(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable final Integer id,
            @RequestBody @Valid final BookDTO bookDTO) {
        bookService.update(id, bookDTO);
        return ResponseEntity.ok().build();
    }
    */

    @GetMapping("/list")
    public String getAllBooks(Model model) {

        List<BookDTO> books = (List<BookDTO>) bookService.findAll();
        if (books.size() == 0)
            books = null;

        model.addAttribute("books", books);
        return "book/listBook";
    }

   @GetMapping("/list/{id}")
   public String getBook(@PathVariable final Integer id ,Model model) {

     BookDTO book = (BookDTO) bookService.get(id);
     model.addAttribute("book", book);
     return "book/updateBook";
   }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable final Integer id) {
        BookDTO book = (BookDTO) bookService.get(id);

        try
        {
            File f= new File(uploadDirectory + book.getImage());
            if(f.delete())
            {
                System.out.println(f.getName() + " deleted");
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

     bookService.delete(id);
        return "redirect:../list";
    }

    // code mouhib

    @GetMapping("add")
    public String showAddArticleForm(BookDTO Book, Model model) {
        model.addAttribute("book", new BookDTO());
        return "book/addBook";
    }

    @PostMapping("add")
    public String createBook(@Valid final BookDTO bookDTO, @RequestParam("files") MultipartFile[] files) {
        MultipartFile file = files[0];
        bookDTO.setImage(file.getOriginalFilename());

/// part upload
        StringBuilder fileName = new StringBuilder();

        Path fileNameAndPath = Paths.get(uploadDirectory ,file.getOriginalFilename());
        fileName.append(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
       // bookDTO.setImage(bookId+"_"+fileName.toString());
      //  bookService.update(bookDTO.getId(), bookDTO);
        bookService.create(bookDTO);

        return "redirect:list";
    }


    @GetMapping("edit/{id}")
    public String showBookFormToUpdate(@PathVariable("id")  Integer id, Model model) {
        BookDTO book = (BookDTO) bookService.get(id);
      model.addAttribute("Book", book);
     return "book/updateBook";

    }

    @PostMapping("edit/{id}")
    public String updateBook(@PathVariable("id") Integer id, @RequestBody @Valid final BookDTO bookDTO,
                             Model model,@RequestParam("files") MultipartFile[] files) {
        //  bookService.update(id, bookDTO);

        /// part upload
        StringBuilder fileName = new StringBuilder();
        MultipartFile file = files[0];
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        //fileName.append(article.getId()+"_"+ file.getOriginalFilename()); it will create in the database another path but with old version

        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookDTO.setImage(file.getOriginalFilename());
        model.addAttribute("books", bookService.findAll());
        return "book/listBook";
    }
    @GetMapping("show/{id}")
    public String showArticleDetails(@PathVariable("id") int id, Model model) {
        BookDTO book = bookService.get(id);
        model.addAttribute("book", book);

        return "book/showBook";
    }



}
