package com.codegym.controller;


import com.codegym.model.dto.SumMoney;
import com.codegym.model.entity.*;
import com.codegym.model.entity.Wallet;
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

    //L???y ra t???t c??? v?? c???a ng?????i d??ng
    @GetMapping("/getWalletByUserId/{id}")
    public ResponseEntity<List<Wallet>> findAllWalletByUserId(@PathVariable Long id){
        List<Wallet> wallets = walletSV.getWalletByUserId(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    //L???y ra chi ti???t 1 v?? c???a ng?????i d??ng
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


    //Ng?????i d??ng t???o v??
    @PostMapping("/createWallet/{idUser}")
    public ResponseEntity<Wallet> create(@ModelAttribute Wallet wallet, @PathVariable Long idUser) {
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getTotal(),wallet.getMoneyType(), wallet.getNote(), user );
        wallet1.setDate(new Date());
        wallet1.setBalance(wallet.getTotal());
        AddMoney addMoney = new AddMoney(wallet1.getTotal(), wallet1.getDate(), wallet1);
        addMoneySV.save(addMoney);
        if (wallet1.getNote() == null){
            wallet1.setNote("Kh??ng c?? ghi ch??");
        }
        if (wallet1.getIcon() == null) {
            wallet1.setIcon(new Icon(1L, "https://static.moneylover.me/img/icon/icon.png"));
        }
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }


    //Ng?????i d??ng x??a v?? c???a ch??nh m??nh, ko x??a v?? ng?????i kh??c.
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


    //Ng?????i d??ng s???a v?? c???a m??nh
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
                                          @RequestParam int month,
                                          @RequestParam int year) {
        Wallet wallet = walletSV.findById(idWallet).get();
         Integer inFlow = inOutSV.getInFlow(idWallet, month, year);
         Integer outFlow = inOutSV.getOutFlow(idWallet, month, year);
         if (inFlow == null){
             inFlow = 0;
         };
         if (outFlow == null){
             outFlow = 0;
         };
        InOut inOut = new InOut(month, year, inFlow, outFlow, wallet);
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









}
