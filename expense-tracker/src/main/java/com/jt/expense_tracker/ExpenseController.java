package com.jt.expense_tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final JdbcTemplate jdbcTemplate;
    private static final String EXPENSES_TABLE="expenses";

    // public ExpenseController(JdbcTemplate jdbcTemplate){
    //     this.jdbcTemplate=jdbcTemplate;
    // }


    @RequestMapping(value="/expenses", method= RequestMethod.GET)

    public List<Expense> getExpenses(){
        String query="SELECT * FROM %s".formatted( EXPENSES_TABLE);
        List<Expense> expenses=new ArrayList<>();
        // jdbcTemplate.query(query, (resultSet) -> {
        //  System.out.println("id is" + resultSet.getInt("id"));
        //   System.out.println("title is" + resultSet.getString("title"));
        //    System.out.println("category is" + resultSet.getString("category"));

        // int id = resultSet.getInt("id");
        //   String title = resultSet.getString("title");
        //     var category= resultSet.getString("category");
        //       var price = resultSet.getDouble("price");
        //         var date = resultSet.getDate("date").toLocalDate();


        //  var expense =new Expense(id, title, query, price, date);

        
        //  expenses.add(expense);


        //  });


        // List<Expense> expenses=jdbcTemplate.query(query, new BeanPropertyRowMapper<Expense>(Expense.class));
       
        // // return expenses;

          return jdbcTemplate.query(query, new BeanPropertyRowMapper<Expense>(Expense.class));


       
      

    }
    // @RequestMapping(value = "/expenses/{id}",method = RequestMethod.GET)
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable int id){
        // System.out.println("id is "+id);

        String sql="select * from %s where id=?".formatted(EXPENSES_TABLE);
      Expense expense=  jdbcTemplate.queryForObject
      (sql, new BeanPropertyRowMapper<>(Expense.class),id);
         return expense;
    }

    // @RequestMapping(value = "/expenses",method = RequestMethod.POST)
    @PostMapping("/expenses/{id}")
    public Expense createExpense(@RequestBody Expense expense){
    var sql="insert into %s (title,category,price,date) values(?,?,?,?)".formatted(EXPENSES_TABLE);
    jdbcTemplate.update(sql, expense.getTitle(),expense.getCategory(),expense.getPrice(),expense.getDate());
        return expense;
    }
    //  @RequestMapping(value = "/expenses{id}",method = RequestMethod.DELETE)
    //   public Expense getExpenseById(@PathVariable int id){
    //      var sql="delete * from %s where id=?".formatted(EXPENSES_TABLE);
    //       Expense expense=  jdbcTemplate.delete
    // {

    @DeleteMapping("/expenses/{id}")
    public void deleteExpenses(@PathVariable int id){
        String sql ="DELETE FROM %s WHERE id=?".formatted(EXPENSES_TABLE);
        jdbcTemplate.update(sql,id);
    }
    @PutMapping("/expenses")   
     public Expense updateExpense(@RequestBody Expense expense){

    var sql="update %s set title=? ,category=?, price=?, date=? where id=?".formatted(EXPENSES_TABLE);
     jdbcTemplate.update(sql, expense.getTitle(), expense.getCategory(), expense.getPrice(),expense.getDate(), expense.getId());
     Expense updatedExpense= getExpenseById(expense.getId());
     return updatedExpense;
    }
    
}
