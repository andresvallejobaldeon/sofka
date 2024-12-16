package com.av.sofka.accounting.dao.client;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.rest.model.UpdateAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/account")
public interface AccountClient {

    @GetMapping("")
    ResponseEntity<List<Account>> getAccounts();

    @GetMapping("/{accountId}")
    ResponseEntity<Account> getAccount(@PathVariable String accountId);

    @PostMapping("")
    ResponseEntity<Account> saveAccount(@RequestBody Account account);

    @PatchMapping("/{accountId}")
    ResponseEntity<Account> updateAccount(@PathVariable String accountId, @RequestBody UpdateAccountRequest accountRequest);

}
