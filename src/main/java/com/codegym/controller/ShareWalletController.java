package com.codegym.controller;

import com.codegym.model.entity.Notification;
import com.codegym.model.entity.ShareWallet;
import com.codegym.model.entity.User;
import com.codegym.model.entity.Wallet;
import com.codegym.service.notification.NotificationService;
import com.codegym.service.shareWallet.IShareWalletService;
import com.codegym.service.user.UserService;
import com.codegym.service.wallet.WalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/share")
@CrossOrigin("*")
public class ShareWalletController {
    @Autowired
    private IShareWalletService shareWalletService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletSV walletSV;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Wallet>> findALlSharedWallet(@PathVariable Long userId) {
        List<Wallet> wallets = new ArrayList<>();
        List<Long> list = shareWalletService.findListWalletShare(userId);
        for (int i = 0 ; i < list.size(); i++) {
            wallets.add(walletSV.findById(list.get(i)).get());
        }
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @PostMapping("create/{walletId}")
    public ResponseEntity<ShareWallet> createShareWallet(@PathVariable Long walletId, @RequestParam(name = "username") String username) {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                User user1= userService.findByUserName(username);
                Wallet wallet = walletSV.findById(walletId).get();
                if (wallet.getUser().getId() == user1.getId()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ShareWallet shareWallet = new ShareWallet(user1, wallet);
                List<ShareWallet> shareWallets = shareWalletService.findAll();
                for (ShareWallet shareWallet1 : shareWallets) {
                    if (shareWallet1.getWallet().getId() == shareWallet.getWallet().getId() && shareWallet1.getUser().getId() == shareWallet.getUser().getId()) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
                Notification n = new Notification(
                        wallet.getUser().getUsername() + " đã chia sẻ ví "+ wallet.getName()+ " với bạn",
                        wallet.getUser(),
                        user1
                );
                notificationService.save(n);
                shareWalletService.save(shareWallet);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return null;
    }

    @DeleteMapping("/delete/{shareId}")
    public ResponseEntity<ShareWallet> delete(@PathVariable Long shareId) {
        this.shareWalletService.removeById(shareId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
