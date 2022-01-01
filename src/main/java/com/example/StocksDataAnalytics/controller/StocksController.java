package com.example.StocksDataAnalytics.controller;


import com.example.StocksDataAnalytics.exception.ResourceNotFoundException;
import com.example.StocksDataAnalytics.model.Stocks;
import com.example.StocksDataAnalytics.repository.StocksRepository;
import com.example.StocksDataAnalytics.service.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reporting")
public class StocksController {
    @Autowired
    private StocksRepository stocksRepository;

    @GetMapping("/stocks")
    public Page<Stocks> getAllStocks(Pageable pageable) {
        return stocksRepository.findAll(pageable);
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
        System.out.println("user_id : " + userDetails.getId());
        System.out.println("stock user_id : " + stock.getUserId());
        if (userDetails.getId() == stock.getUserId()) {
            stock.setName(stockDetails.getName());
            stock.setCurrentPrice(stockDetails.getCurrentPrice());
            stock.setCurrencyId(stockDetails.getCurrencyId());
            stock.setActive(stockDetails.getActive());
            stock.setQuantity(stockDetails.getQuantity());

            final Stocks updatedStock = stocksRepository.save(stock);
            return ResponseEntity.ok(updatedStock);
        }else if (userDetails.getRoles().contains("admin")) {
            stock.setName(stockDetails.getName());
            stock.setCurrentPrice(stockDetails.getCurrentPrice());
            stock.setCurrencyId(stockDetails.getCurrencyId());
            stock.setActive(stockDetails.getActive());
            stock.setQuantity(stockDetails.getQuantity());

            final Stocks updatedStock = stocksRepository.save(stock);
            return ResponseEntity.ok(updatedStock);
        } else {
            throw new ResourceNotFoundException("Update not allowed for this stock by user :" + userDetails.getId());
        }

    }

    @DeleteMapping("/stocks/{id}")
    public Map<String, Boolean> deleteStock(@AuthenticationPrincipal CustomUserPrincipal userDetails, @PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Stocks stock = stocksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        System.out.println("user_id : " + userDetails.getId());
        System.out.println("stock user_id : " + stock.getUserId());
        String user = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        if (userDetails.getId() == stock.getUserId()) {
            stocksRepository.delete(stock);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
        else if(userDetails.getRoles().contains("admin")) {
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



