package com.example.backend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Book;
import com.example.backend.entities.User;
import com.example.backend.repository.BookRepository;

@RestController
public class BookController {
	
 @Autowired 
 private BookRepository bookRepository;
 
 // NB: toutes ces fonctionnalités concernent seulement l'admin de l'application
 
 // ajouter un livre
 @RequestMapping(value = "/Books/Add", method = RequestMethod.POST) 
 public Book saveBook (@RequestBody Book b){  
		  bookRepository.save(b);
		  return b;
 }
 
 // supprimer un livre
 @RequestMapping(value = "/Books/{id}", method = RequestMethod.DELETE) 
 public String deleteBook(@PathVariable String id){
	     Optional<Book> b = bookRepository.findById(Long.parseLong(id));
	     if(b.isEmpty()) {
		    return "book not exist";
	     }
	     else {
	    	 bookRepository.deleteById(Long.parseLong(id)); 
	    	 return "deleted";
	     }
 
}
 
 // mettre à jour un livre
 
 @RequestMapping(value = "/Books/{id}", method = RequestMethod.PUT) 
 public Book findUser(@PathVariable String id, @RequestBody Book b){  
      
       	  Book b1 = bookRepository.findById(Long.parseLong(id)).get();
       	  b.setId(b1.getId());
       	  bookRepository.save(b);
       	  return b;        
 } 
 
 // tous les livres 
 @RequestMapping(value = "/Books", method = RequestMethod.GET) 
 public List<Book> findBooks(){  
            List<Book> books = bookRepository.findAll();
            return books;
 } 
 
}
