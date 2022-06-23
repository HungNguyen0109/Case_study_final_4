package com.codegym.controller;

import com.codegym.model.entity.AddMoney;
import com.codegym.model.entity.DateDTO;
import com.codegym.model.entity.Wallet;
import com.codegym.service.addMoney.IAddMoneySV;
import com.codegym.service.user.IUserService;
import com.codegym.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/addMoney")
public class AddMoneyController {
    @Autowired
    private IAddMoneySV addMoneyService;

    @Autowired
    private IWalletSV walletService;

    @Autowired
    private IUserService userService;

    @GetMapping("/wallet")
    public ResponseEntity<List<Wallet>> showAllWallet() {
        List<Wallet> wallets = walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AddMoney>> addMoney() {
        List<AddMoney> addMoney = addMoneyService.findAll();
        return new ResponseEntity<>(addMoney, HttpStatus.OK);
    }

    @PostMapping("/{wallet_id}")
    public ResponseEntity<AddMoney> saveAddMoney(@RequestBody AddMoney addMoney, @PathVariable Long wallet_id) {
        Wallet wallet = walletService.findById(wallet_id).get();
        int walletMoney = wallet.getTotal();
        int balanceMoney = wallet.getBalance();
        int money = addMoney.getMoney();
        wallet.setTotal(walletMoney + money);
        wallet.setBalance(balanceMoney + money);
        AddMoney addMoney1 = new AddMoney(money, new Date(), wallet);
        return new ResponseEntity<>(addMoneyService.save(addMoney1), HttpStatus.CREATED);
    }

    @PostMapping("/addMoneyInTimeByIdWallet")
    public ResponseEntity<Iterable<AddMoney>> getAddMoneyInTimeByIdWallet (@RequestBody DateDTO date){
        Iterable<AddMoney> addMoneyInTimes = addMoneyService.getListAddMoneyInTimeByIdWallet(date.getDate1(), date.getDate2(), date.getWallet().getId());
        return new ResponseEntity<>(addMoneyInTimes, HttpStatus.OK);
    }
}

