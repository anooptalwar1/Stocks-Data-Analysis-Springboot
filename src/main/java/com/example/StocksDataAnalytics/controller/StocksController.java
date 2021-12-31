package com.example.StocksDataAnalytics.controller;


import com.example.StocksDataAnalytics.exception.ResourceNotFoundException;
import com.example.StocksDataAnalytics.model.Stocks;
import com.example.StocksDataAnalytics.repository.StocksRepository;
import com.example.StocksDataAnalytics.service.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reporting")
public class StocksController {
    @Autowired
    private StocksRepository stocksRepository;

    @GetMapping("/stocks")
    public List<Stocks> getAllStocks() {
        return stocksRepository.findAll();
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Object> getStockById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Stocks stock = stocksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        return ResponseEntity.ok().body(stock);
    }

    @PostMapping("/stocks")
    public Stocks createStock(@AuthenticationPrincipal CustomUserPrincipal userDetails, @Valid @RequestBody Stocks stocks) {
        stocks.setUserId(userDetails.getId());
        return stocksRepository.save(stocks);
        }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stocks> updateStock(@AuthenticationPrincipal CustomUserPrincipal userDetails, @PathVariable(value = "id") Integer id,
                                            @Valid @RequestBody Stocks stockDetails) throws ResourceNotFoundException {
        Stocks stock = stocksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        if (userDetails.getId() == stock.getUserId()) {
            stock.setName(stockDetails.getName());
            stock.setCurrentPrice(stockDetails.getCurrentPrice());
            stock.setCurrencyId(stockDetails.getCurrencyId());
            stock.setActive(stockDetails.getActive());
            stock.setQuantity(stockDetails.getQuantity());

            final Stocks updatedStock = stocksRepository.save(stock);
            return ResponseEntity.ok(updatedStock);
        }else if (userDetails.getAuthorities().contains("admin")) {
            stock.setName(stockDetails.getName());
            stock.setCurrentPrice(stockDetails.getCurrentPrice());
            stock.setCurrencyId(stockDetails.getCurrencyId());
            stock.setActive(stockDetails.getActive());
            stock.setQuantity(stockDetails.getQuantity());

            final Stocks updatedStock = stocksRepository.save(stock);
            return ResponseEntity.ok(updatedStock);
        } else {
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.FALSE);
            return (ResponseEntity<Stocks>) response;
        }

    }

    @DeleteMapping("/stocks/{id}")
    public Map<String, Boolean> deleteStock(@AuthenticationPrincipal CustomUserPrincipal userDetails, @PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Stocks stock = stocksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        System.out.println("user_id : " + userDetails.getId());
        System.out.println("stock user_id : " + stock.getUserId());
        System.out.println("authorities : " + userDetails.getAuthorities().contains("admin"));
        String user = String.valueOf(userDetails.getAuthorities().stream().findFirst().get());
        if (userDetails.getId() == stock.getUserId()) {
            stocksRepository.delete(stock);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
        else if(user == "admin") {
            stocksRepository.delete(stock);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.FALSE);
            return response;
        }

        }
    }



