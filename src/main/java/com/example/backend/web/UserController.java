package com.example.backend.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Address;
import com.example.backend.entities.Book;
import com.example.backend.entities.User;
import com.example.backend.repository.AddressRepository;
import com.example.backend.repository.BookRepository;
import com.example.backend.repository.UserRepository;

@RestController
public class UserController {

	  @Autowired private UserRepository userRepository;
	  @Autowired private AddressRepository addressRepository;
	  @Autowired private BookRepository bookRepository;
	  
	  // creer un compte
	  @RequestMapping(value = "/Users/register", method = RequestMethod.POST) 
	  public User saveUser(@RequestBody User u){  
			  userRepository.save(u);
			  return u;
	  }
	  
	  // se connecter
	  @RequestMapping(value = "/Users/login", method = RequestMethod.POST) 
	  public User Connecter(@RequestBody User u){  
		      User user2 = null;
			  User user = userRepository.findByusername(u.getUsername());
			  if (user.getPassword().equals(u.getPassword())) {
				  return user;
			  }
			  else {
				  return user2;
				 
			  }
	  }  
	  
	  // chercher par username
	  @RequestMapping(value = "/Users/{username}", method = RequestMethod.GET) 
	  public User findUser(@PathVariable String username){  
			  User u = userRepository.findByusername(username);
			  return u;
	  }
	  
	  
	  // supprimer un utilisateur
	  @RequestMapping(value = "/Users/{id}", method = RequestMethod.DELETE) 
	  public String deleteUser(@PathVariable String id){
		     //long num = Long.parseLong(str)
		     Optional<User> u = userRepository.findById(Long.parseLong(id));
		     if(u.isEmpty()) {
			    return "user not exist";
		     }
		     else {
		    	 userRepository.deleteById(Long.parseLong(id)); 
		    	 return "deleted";
		     }
	  }
	  
	  // modifier un utilisateur
	  @RequestMapping(value = "/Users/{id}", method = RequestMethod.PUT) 
	  public User findUser(@PathVariable String id, @RequestBody User u){  
           
            	  User u1 = userRepository.findById(Long.parseLong(id)).get();
            	  u.setUser_id(u1.getUser_id());
            	  userRepository.save(u);
            	  return u;        
	  }
	  
	  
	  @RequestMapping(value = "/Adresses", method = RequestMethod.GET) 
	  public List<Address> findUser(){  
			  List<Address> a = addressRepository.findAll();
			  return a;
	  }
	  
	  // reserver un livre
	  @RequestMapping(value = "/Books/{bookid}", method = RequestMethod.POST) 
	  public Book reserver(@PathVariable String bookid, @RequestBody User u){  
	        	  Book b1 = bookRepository.findById(Long.parseLong(bookid)).get();
	        	  b1.setUser(u.getUser_id());
	        	  bookRepository.save(b1);
	      	  return b1;
	  }
	  
	  // ensemble des livres reservés
	 @RequestMapping(value = "/Books/Reserved", method = RequestMethod.GET) 
	  public ArrayList<Book> reserved(@RequestBody User u){  
		   ArrayList<Book> books = new ArrayList<Book>();
		         Long userid = u.getUser_id();
	        	 books = (ArrayList<Book>) bookRepository.findByUser(userid);
	        //	 List<Book> users = userRepository.findAll();
	        	  //int i = 0;
	        	  //books.forEach(b -> b.getUser_id() != Long.parseLong(Userid) ? books.remove(b) System.out.println("hh") : System.out.println("nn") );
	             
	       return books;
	  }
	 
	 // annuler une reservation
	  @RequestMapping(value = "/Books/Cancel/{bookid}", method = RequestMethod.DELETE) 
	  public Book CancelReserverdBook(@PathVariable String bookid, @RequestBody User u){  
	        	  Book b1 = bookRepository.findById(Long.parseLong(bookid)).get();
	        	  b1.setUser(null);
	        	  bookRepository.save(b1);
	      	  return b1;
	  }
	 
	 
	 
}
	  

