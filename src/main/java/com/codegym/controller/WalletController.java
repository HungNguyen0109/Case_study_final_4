package com.codegym.controller;


import com.codegym.model.dto.SumMoney;
import com.codegym.model.entity.*;
import com.codegym.model.entity.Wallet;
import com.codegym.service.Transaction.ITransactionSV;
import com.codegym.service.addMoney.IAddMoneySV;
import com.codegym.service.iconUser.IIconSV;
import com.codegym.service.inout.IInOutSV;
import com.codegym.service.moneyType.MoneyTypeSV;
import com.codegym.service.shareWallet.IShareWalletService;
import com.codegym.service.sumMoney.ISumMoneySV;
import com.codegym.service.user.IUserService;
import com.codegym.service.wallet.WalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletSV walletSV;

    @Autowired
    private ITransactionSV transactionSV;

    @Autowired
    Environment evn;

    @Autowired
    private MoneyTypeSV moneyTypeSV;

    @Autowired
    private IIconSV iconSV;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISumMoneySV sumMoneySV;

    @Autowired
    private IAddMoneySV addMoneySV;

    @Autowired
    private IInOutSV inOutSV;

    @Autowired
    private IShareWalletService shareWalletService;

    @GetMapping("/icon")
    public ResponseEntity<List<Icon>> findAllIcon(){
        List<Icon> icons = iconSV.findAll();
        return new ResponseEntity<>(icons, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Wallet> findWalletById(@PathVariable Long id){
//        Optional<Wallet> walletOptional = walletSV.findById(id);
//        if (!walletOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(walletOptional.get(), HttpStatus.OK);
//    }

    @GetMapping("/moneytype")
    public ResponseEntity<List<MoneyType>> fillAllType(){
        List<MoneyType> moneyTypes = moneyTypeSV.findAll();
        return new ResponseEntity<>(moneyTypes, HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Wallet>> findAll(){
//        List<Wallet> wallets = walletSV.findAll();
//        return new ResponseEntity<>(wallets, HttpStatus.OK);
//    }

    @GetMapping("/findWalletByName/{name}")
    ResponseEntity<?> findWalletByName(@PathVariable String name){
        Wallet wallet = walletSV.findWalletByName(name).get();
        return new  ResponseEntity<>(wallet,HttpStatus.OK);
    }


    //Lấy ra tất cả ví của người dùng
    @GetMapping("/getWalletByUserId/{id}")
    public ResponseEntity<List<Wallet>> findAllWalletByUserId(@PathVariable Long id){
        List<Wallet> wallets = walletSV.getWalletByUserId(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

//    @GetMapping("/findTotalIncome/{userId}")
//    public ResponseEntity<Integer> findTotalIncome(@PathVariable Long userId) {
//        return new ResponseEntity<>(inOutSV.getIncomeTotal(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/findTotalOutcome/{userId}")
//    public ResponseEntity<Integer> findTotalOutcome(@PathVariable Long userId) {
//        return new ResponseEntity<>(inOutSV.getOutcomeTotal(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/findTotalBalance/{userId}")
//    public ResponseEntity<Integer> findTotalBalance(@PathVariable Long userId) {
//        return new ResponseEntity<>(inOutSV.getBalanceTotal(userId), HttpStatus.OK);
//    }

    @GetMapping("/findTotalWallet/{userId}")
    public ResponseEntity<UserFinancial> findTotalWallet(@PathVariable Long userId) {
        Integer income = inOutSV.getIncomeTotal(userId);
        Integer outcome = inOutSV.getOutcomeTotal(userId);
        Integer balance = inOutSV.getBalanceTotal(userId);
        Integer wallet = inOutSV.getWalletTotal(userId);
        UserFinancial userFinancial = new UserFinancial(income,outcome,balance,wallet);
        return new ResponseEntity<>(userFinancial, HttpStatus.OK);
    }

    //Lấy ra chi tiết 1 ví của người dùng
    @GetMapping("/getWallet/{idUser}/{idWallet}")
    public ResponseEntity<Wallet> findWallet(@PathVariable Long idUser,
                                             @PathVariable Long idWallet) {
        boolean check = false;
        List<Long> list = shareWalletService.findWhoWasShared(idWallet);
        Wallet wallet = walletSV.findById(idWallet).get();
        list.add(wallet.getUser().getId());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == idUser) {
                check = true;
            }
        }
        if (!check) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(wallet, HttpStatus.OK);
        }
    }


    //Người dùng tạo ví
    @PostMapping("/createWallet/{idUser}")
    public ResponseEntity<Wallet> create(@ModelAttribute Wallet wallet, @PathVariable Long idUser) {
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getTotal(),wallet.getMoneyType(), wallet.getNote(), user );
        wallet1.setDate(new Date());
        wallet1.setBalance(wallet.getTotal());
        AddMoney addMoney = new AddMoney(wallet1.getTotal(), wallet1.getDate(), wallet1);
        addMoneySV.save(addMoney);
        if (wallet1.getNote() == null){
            wallet1.setNote("Không có ghi chú");
        }
        if (wallet1.getIcon() == null) {
            wallet1.setIcon(new Icon(1L, "https://static.moneylover.me/img/icon/icon.png"));
        }
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }

    @PostMapping("/createWallet1/{idUser}")
    public ResponseEntity<?> create1(@RequestBody Wallet wallet, @PathVariable Long idUser) {
        if (walletSV.existsByName(wallet.getName())){
            return new ResponseEntity<>("This name is already exist, try another one!",HttpStatus.OK);
        }
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getTotal(),wallet.getMoneyType(), wallet.getNote(), user );
        wallet1.setDate(new Date());
        wallet1.setBalance(wallet.getTotal());
        AddMoney addMoney = new AddMoney(wallet1.getTotal(), wallet1.getDate(), wallet1);
        addMoneySV.save(addMoney);
        if (wallet1.getNote() == null){
            wallet1.setNote("Không có ghi chú");
        }
        if (wallet1.getIcon() == null) {
            wallet1.setIcon(new Icon(1L, "https://static.moneylover.me/img/icon/icon.png"));
        }
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }


    //Người dùng xóa ví của chính mình, ko xóa ví người khác.
    @DeleteMapping("/{idUser}/{idWallet}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long idWallet,
                                               @PathVariable Long idUser) {
        Optional<Wallet> walletOptional = walletSV.findById(idWallet);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if ( walletOptional.get().getUser().getId() != idUser) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletSV.deleteWallet(idWallet);
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.NO_CONTENT);
    }


    //Người dùng sửa ví của mình
    @PutMapping("/editWallet/{idWallet}/{idUser}")
    public ResponseEntity<Wallet> editWallet(@RequestBody Wallet wallet, @PathVariable Long idWallet, @PathVariable Long idUser){
        Optional<Wallet> walletOptional = walletSV.findById(idWallet);
        if (!walletOptional.isPresent()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if ( walletOptional.get().getUser().getId() != idUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(idWallet, wallet.getName(), wallet.getDate(), wallet.getIcon(), wallet.getMoneyType(), wallet.getTotal(), wallet.getBalance(), wallet.getNote(), user);
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }

    @GetMapping("/sumMoney/{idUser}")
    public ResponseEntity<List<SumMoney>> getSumMoney(@PathVariable Long idUser) {
        List<SumMoney> sumMonies = sumMoneySV.getSumMoney(idUser);
        return new ResponseEntity<>(sumMonies, HttpStatus.OK);
    }

    @GetMapping("/inOut/{idWallet}")
    public ResponseEntity<InOut> getInOut(@PathVariable Long idWallet,
                                          @RequestParam int day,
                                          @RequestParam int month,
                                          @RequestParam int year) {
        Wallet wallet = walletSV.findById(idWallet).get();
         Integer inFlow = inOutSV.getInFlow(idWallet,day, month, year);
         Integer outFlow = inOutSV.getOutFlow(idWallet,day, month, year);
         if (inFlow == null){
             inFlow = 0;
         };
         if (outFlow == null){
             outFlow = 0;
         };
        InOut inOut = new InOut(day, month, year, inFlow, outFlow, wallet);
        return new ResponseEntity<>(inOut, HttpStatus.OK);
    }


    @GetMapping("/inOutMonth/{idUser}")
    public ResponseEntity<InOut> getMonthInOut(@PathVariable Long idUser,
                                          @RequestParam int month,
                                          @RequestParam int year) {
        User user = userService.findById(idUser).get();
        Integer inFlow = inOutSV.getMonthInFlow(idUser, month, year);
        Integer outFlow = inOutSV.getMonthOutFlow(idUser, month, year);
        if (inFlow == null){
            inFlow = 0;
        };
        if (outFlow == null){
            outFlow = 0;
        };
        InOut inOut = new InOut(month, year, inFlow, outFlow, user);
        return new ResponseEntity<>(inOut, HttpStatus.OK);
    }

    @GetMapping("/listAddMoney/{idUser}/{idWallet}")
    public ResponseEntity <List<AddMoney>> getAllAddMoney(@PathVariable Long idWallet,
                                                          @PathVariable Long idUser) {
        Wallet wallet = walletSV.findById(idWallet).get();
        if (wallet.getUser().getId() != idUser) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AddMoney> addMonies = addMoneySV.getAddMoneyByWallet(idWallet);
        return new ResponseEntity<>(addMonies, HttpStatus.OK);
    }

    @GetMapping("/listAddMoneybyUser/{idUser}")
    public ResponseEntity<Iterable<AddMoney>> getAllAddMoneyByUser(@PathVariable Long idUser) {
       Iterable<AddMoney> addMonies = addMoneySV.getAllAddMoneyByIdUser(idUser);
       return new ResponseEntity<>(addMonies, HttpStatus.OK);

    }



    @GetMapping("listByUserAdd/{user_id}")
    public ResponseEntity<Iterable<AddMoney>> addMoneyList( @PathVariable Long user_id ) {
        Iterable<AddMoney> addMoneys = addMoneySV.getListAddMoneyByUser(user_id);
        return new ResponseEntity<>(addMoneys, HttpStatus.OK);
    }

    @GetMapping("listByUserTrans/{user_id}")
    public ResponseEntity<Iterable<Transaction>> transList( @PathVariable Long user_id ) {
        Iterable<Transaction> transactions = transactionSV.getListTransactionsByUser(user_id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }










}
